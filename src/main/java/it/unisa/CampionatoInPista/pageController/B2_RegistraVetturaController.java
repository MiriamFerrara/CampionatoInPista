package it.unisa.CampionatoInPista.pageController;

import it.unisa.CampionatoInPista.database.DatabaseConnection;
import it.unisa.CampionatoInPista.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.sql.Types.NULL;


@Controller
@RequestMapping("/CampionatoInPista")
public class B2_RegistraVetturaController {
    @Autowired
    private DatabaseConnection databaseConnection;

    /** OPERAZIONE 2. Inserimento dei dati di un’autovettura, compresi i componenti di cui è composta.*/
    @GetMapping("/2RegistraVettura")
    public String getRegistraVettura(Model model) {
        List<String> nomeCostruttori = new ArrayList<>();

        try {
            // Query per ottenere i nomi dei costruttori e aggiungerli al modello
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT nome FROM costruttore;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                nomeCostruttori.add(resultSet.getString("Nome"));
            }
            model.addAttribute("nomeCostruttore", nomeCostruttori);
            resultSet.close();
            preparedStatement.close();


        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }

        return "2RegistraVettura";
    }

    @PostMapping("/2RegistraVettura")
    public String registraVettura(@ModelAttribute Vettura vettura,
                                  @ModelAttribute Componente componente,
                                  @ModelAttribute Costruttore costruttore,
                                  @RequestParam("nomeCostruttore") String nome,
                                  @RequestParam("tipoComponente") String tipoComponente,
                                  @RequestParam("costruttoreType") String costruttoreType,
                                  @RequestParam("nomeNuovoCostruttore") String nomeNuovoCostruttore,
                                  @RequestParam("ragioneSociale") String ragioneSociale,
                                  @RequestParam("sedeFabbrica") String sedeFabbrica
                                  ) {

        try {
            // Query per inserire i dati della vettura nel database
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "INSERT INTO vettura (Targa, NumGara, Modello) VALUES (?, ?, ?);");
            preparedStatement.setString(1, vettura.getTarga());
            preparedStatement.setInt(2, vettura.getNumGara());
            preparedStatement.setString(3, vettura.getModello());

            preparedStatement.executeUpdate();
            preparedStatement.close();

            // Query per inserire i dati del componente nel database
                    PreparedStatement componenteStatement = databaseConnection.getConnection().prepareStatement(
                            "INSERT INTO componente (DataInstallazione, Costo, TipoComponente, TipoMateriale, Peso, NumMarce, Cilindrata, TipoMotore, NumCilindri, NomeCostruttore, TargaVettura) " +
                                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");

                    componenteStatement.setDate(1, componente.getDataInstallazione());
                    componenteStatement.setFloat(2, componente.getCosto());
                    componenteStatement.setString(3, tipoComponente);

                    //Telaio: 4 TipoMateriale, 5 Peso
                    switch (tipoComponente) {
                        case "Telaio":
                            componenteStatement.setString(4, componente.getTipoMateriale());
                            componenteStatement.setFloat(5, componente.getPeso());
                            componenteStatement.setNull(6, NULL);
                            componenteStatement.setNull(7, NULL);
                            componenteStatement.setNull(8, NULL);
                            componenteStatement.setNull(9, NULL);
                            break;
                        //Cambio: 6 NumMarce
                        case "Cambio":
                            componenteStatement.setNull(4, NULL);
                            componenteStatement.setNull(5, NULL);
                            componenteStatement.setString(6, componente.getNumMarce());
                            componenteStatement.setNull(7, NULL);
                            componenteStatement.setNull(8, NULL);
                            componenteStatement.setNull(9, NULL);
                            break;

                        //Motore: 7 Cilindrata, 8 TipoMotore, 9 NumCilindri
                        case "Motore":
                            componenteStatement.setNull(4, NULL);
                            componenteStatement.setNull(5, NULL);
                            componenteStatement.setNull(6, NULL);
                            componenteStatement.setString(7, componente.getCilindrata());
                            componenteStatement.setString(8, componente.getTipoMotore());
                            componenteStatement.setInt(9, componente.getNumCilindri());
                            break;
                        default:
                            //System.out.println("Tipo di componente non supportato: " + tipoComponente);
                            break;
                    }
                    componenteStatement.setString(10, nome);
                    componenteStatement.setString(11, vettura.getTarga());

                    componenteStatement.executeUpdate();
                    componenteStatement.close();

            if (costruttoreType.equals("existing")) {
                // Recupera i dettagli del costruttore esistente usando il nome
                PreparedStatement recuperaCostruttoreStatement = databaseConnection.getConnection().prepareStatement(
                        "SELECT Nome, RagioneSociale, SedeFabbrica FROM costruttore WHERE Nome = ?;");
                recuperaCostruttoreStatement.setString(1, nome);
                ResultSet costruttoreResultSet = recuperaCostruttoreStatement.executeQuery();

                if (costruttoreResultSet.next()) {
                    costruttore.setNome(costruttoreResultSet.getString("Nome"));
                    costruttore.setRagioneSociale(costruttoreResultSet.getString("RagioneSociale"));
                    costruttore.setSedeFabbrica(costruttoreResultSet.getString("SedeFabbrica"));

                    PreparedStatement updateNumComponentiStatement = databaseConnection.getConnection().prepareStatement(
                            "UPDATE costruttore SET NumComponenti = NumComponenti + 1 WHERE Nome = ?;");
                    updateNumComponentiStatement.setString(1, nome);
                    updateNumComponentiStatement.executeUpdate();
                    updateNumComponentiStatement.close();
                    costruttoreResultSet.close();
                    recuperaCostruttoreStatement.close();
                }

            } else if (costruttoreType.equals("new")) {
                // Query per inserire i dati di un nuovo costruttore
                PreparedStatement nuovoCostruttoreStatement = databaseConnection.getConnection().prepareStatement(
                        "INSERT INTO costruttore (Nome, RagioneSociale, SedeFabbrica, NumComponenti) VALUES (?, ?, ?, ?);");

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