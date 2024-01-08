package it.unisa.CampionatoInPista.pageController;

import it.unisa.CampionatoInPista.database.DatabaseConnection;
import it.unisa.CampionatoInPista.domain.Componente;
import it.unisa.CampionatoInPista.domain.Costruttore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;

@Controller
@RequestMapping("/CampionatoInPista")
public class G7_VerificaComponenteController {
    @Autowired
    private DatabaseConnection databaseConnection;

    @GetMapping("/7VerificaComponente")
    public String getVerificaComponente(Model model) {
        List<String> targhe = new ArrayList<>();
        try {
            PreparedStatement selezionaVStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT TargaVettura FROM componente;");
            ResultSet resultSet = selezionaVStatement.executeQuery();
            while (resultSet.next()) {
                targhe.add(resultSet.getString("TargaVettura"));
            }
            model.addAttribute("targhe", targhe);
            //System.out.println("targa " +targhe);
            resultSet.close();
            selezionaVStatement.close();


            } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "7VerificaComponente";
    }


    @PostMapping("/7VerificaComponente")
    public String VerificaComponente(Model model, final @RequestParam("targhe") String targa) {
        List<Componente> daticomponente = new ArrayList<>();
        List<String> nomeCostruttori = new ArrayList<>();
        List<String> tipoComponente = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM componente " +
                            "WHERE TargaVettura = ? ;");

            preparedStatement.setString(1, targa);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Componente componente = new Componente();
                componente.setCodice(resultSet.getInt("Codice"));
                componente.setDataInstallazione(resultSet.getDate("DataInstallazione"));
                componente.setCosto(resultSet.getFloat("Costo"));
                componente.setTipoComponente(resultSet.getString("TipoComponente"));
                componente.setTipoMateriale(resultSet.getString("TipoMateriale"));
                componente.setPeso(resultSet.getFloat("Peso"));
                componente.setNumMarce(resultSet.getString("NumMarce"));
                componente.setCilindrata(resultSet.getString("Cilindrata"));
                componente.setTipoMotore(resultSet.getString("TipoMotore"));
                componente.setNumCilindri(resultSet.getInt("NumCilindri"));

                componente.setTargaVettura(resultSet.getString("TargaVettura"));
                componente.setNomeCostruttore(resultSet.getString("NomeCostruttore"));
                daticomponente.add(componente);
            }
            model.addAttribute("daticomponente", daticomponente);
            resultSet.close();
            preparedStatement.close();

            PreparedStatement tipiStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT DISTINCT TipoComponente FROM componente " +
                            "WHERE TipoComponente NOT IN ( " +
                            "SELECT TipoComponente " +
                            "FROM componente " +
                            "WHERE TargaVettura = ?);");
            tipiStatement.setString(1, targa);
            ResultSet resultSetTipi = tipiStatement.executeQuery();
            while (resultSetTipi.next()) {
                tipoComponente.add(resultSetTipi.getString("TipoComponente"));
            }


            PreparedStatement preparedStatement1 = databaseConnection.getConnection().prepareStatement(
                    "SELECT nome FROM costruttore");
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                nomeCostruttori.add(resultSet1.getString("Nome"));
            }
            model.addAttribute("TipoComponente", tipoComponente);
            model.addAttribute("targhe", targa);
            model.addAttribute("nomeCostruttore", nomeCostruttori);
            resultSet1.close();
            preparedStatement1.close();


        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "7VerificaComponente";
    }


    @PostMapping("/7VerificaComponenteInserire")
    public String InserisciComponente( @ModelAttribute Componente componenteM,
                                       @ModelAttribute Costruttore costruttore,
                                       @RequestParam("targhe") String targa,
                                     @RequestParam("nomeCostruttore") String nome,
                                     @RequestParam("tipoComponente") String tipoComponente,
                                     @RequestParam("costruttoreType") String costruttoreType,
                                     @RequestParam("nomeNuovoCostruttore") String nomeNuovoCostruttore,
                                     @RequestParam("ragioneSociale") String ragioneSociale,
                                     @RequestParam("sedeFabbrica") String sedeFabbrica) {


            try{
                PreparedStatement componenteStatement = databaseConnection.getConnection().prepareStatement(
                    "INSERT INTO componente (DataInstallazione, Costo, TipoComponente, TipoMateriale, Peso, " +
                            "NumMarce, Cilindrata, TipoMotore, NumCilindri, NomeCostruttore, TargaVettura) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            componenteStatement.setDate(1, componenteM.getDataInstallazione());
            componenteStatement.setFloat(2, componenteM.getCosto());
            componenteStatement.setString(3, tipoComponente);

            //Telaio: 4 TipoMateriale, 5 Peso
            switch (tipoComponente) {
                case "Telaio":
                    componenteStatement.setString(4, componenteM.getTipoMateriale());
                    componenteStatement.setFloat(5, componenteM.getPeso());
                    componenteStatement.setNull(6, NULL);
                    componenteStatement.setNull(7, NULL);
                    componenteStatement.setNull(8, NULL);
                    componenteStatement.setNull(9, NULL);
                    break;
                //Cambio: 6 NumMarce
                case "Cambio":
                    componenteStatement.setNull(4, NULL);
                    componenteStatement.setNull(5, NULL);
                    componenteStatement.setString(6, componenteM.getNumMarce());
                    componenteStatement.setNull(7, NULL);
                    componenteStatement.setNull(8, NULL);
                    componenteStatement.setNull(9, NULL);
                    break;

                //Motore: 7 Cilindrata, 8 TipoMotore, 9 NumCilindri
                case "Motore":
                    componenteStatement.setNull(4, NULL);
                    componenteStatement.setNull(5, NULL);
                    componenteStatement.setNull(6, NULL);
                    componenteStatement.setString(7, componenteM.getCilindrata());
                    componenteStatement.setString(8, componenteM.getTipoMotore());
                    componenteStatement.setInt(9, componenteM.getNumCilindri());
                    break;
                default:
                    //System.out.println("Tipo di componente non supportato: " + tipoComponente);
                    break;
            }
            componenteStatement.setString(10, nome);
            //System.out.println("Targhe "+ targa);
            componenteStatement.setString(11, targa);

            componenteStatement.executeUpdate();
            componenteStatement.close();

            if (costruttoreType.equals("existing")) {
                // Recupera i dettagli del costruttore esistente usando il nome
                PreparedStatement recuperaCostruttoreStatement = databaseConnection.getConnection().prepareStatement(
                        "SELECT Nome, RagioneSociale, SedeFabbrica FROM costruttore WHERE Nome = ?");
                recuperaCostruttoreStatement.setString(1, nome);
                ResultSet costruttoreResultSet = recuperaCostruttoreStatement.executeQuery();

                PreparedStatement updateNumComponentiStatement = databaseConnection.getConnection().prepareStatement(
                        "UPDATE costruttore SET NumComponenti = NumComponenti + 1 WHERE Nome = ?");
                updateNumComponentiStatement.setString(1, nome);
                updateNumComponentiStatement.executeUpdate();
                updateNumComponentiStatement.close();

                if (costruttoreResultSet.next()) {
                    costruttore.setNome(costruttoreResultSet.getString("Nome"));
                    costruttore.setRagioneSociale(costruttoreResultSet.getString("RagioneSociale"));
                    costruttore.setSedeFabbrica(costruttoreResultSet.getString("SedeFabbrica"));
                    costruttoreResultSet.close();
                    recuperaCostruttoreStatement.close();
                }

            } else if (costruttoreType.equals("new")) {
                PreparedStatement nuovoCostruttoreStatement = databaseConnection.getConnection().prepareStatement(
                        "INSERT INTO costruttore (Nome, RagioneSociale, SedeFabbrica, NumComponenti) VALUES (?, ?, ?, ?)");

                nuovoCostruttoreStatement.setString(1, nomeNuovoCostruttore);
                nuovoCostruttoreStatement.setString(2, ragioneSociale);
                nuovoCostruttoreStatement.setString(3, sedeFabbrica);
                nuovoCostruttoreStatement.setInt(4, costruttore.getNumComponenti());
                nuovoCostruttoreStatement.executeUpdate();
                nuovoCostruttoreStatement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "successo";
    }
}