package it.unisa.CampionatoInPista.pageController;

import it.unisa.CampionatoInPista.database.DatabaseConnection;
import it.unisa.CampionatoInPista.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/CampionatoInPista")
public class N12_StampaCostruttoriController {

    @Autowired
    private DatabaseConnection databaseConnection;

    @GetMapping("/12StampaCostruttori")
    public String getStampaCostruttori(Model model) {

        List<String> mesi = new ArrayList<>();

        try {
            PreparedStatement preparedStatement1 = databaseConnection.getConnection().prepareStatement(
                    "SELECT DISTINCT MONTH(DataInstallazione) FROM componente;");
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                mesi.add(resultSet1.getString("MONTH(DataInstallazione)"));
            }
            model.addAttribute("mese", mesi);

            resultSet1.close();
            preparedStatement1.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "12StampaCostruttori";
    }


    @PostMapping("/12StampaCostruttori")
    public String StampaCostruttori(Model model, final @RequestParam("mesi") String mese) {

        List<Costruttore> datiCostruttore = new ArrayList<>();
        List<Componente> datiComponente = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT c.*, " +
                            "(cm.DataInstallazione) AS DataMensileInstallazione " +
                            "FROM costruttore c " +
                            "LEFT JOIN componente cm ON c.Nome = cm.NomeCostruttore " +
                            "WHERE MONTH(cm.DataInstallazione) = ? " +
                            "GROUP BY c.Nome, DataMensileInstallazione; "
            );

            preparedStatement.setString(1, mese);
            //System.out.println("Mese: " + mese);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Costruttore costruttore = new Costruttore();
                costruttore.setNome(resultSet.getString("Nome"));
                costruttore.setRagioneSociale(resultSet.getString("RagioneSociale"));
                costruttore.setSedeFabbrica(resultSet.getString("SedeFabbrica"));
                costruttore.setNumComponenti(resultSet.getInt("NumComponenti"));
                datiCostruttore.add(costruttore);

                Componente componente = new Componente();
                componente.setDataInstallazione(resultSet.getDate("DataMensileInstallazione"));
                datiComponente.add(componente);
            }
            model.addAttribute("costruttore", datiCostruttore);
            model.addAttribute("componente", datiComponente);
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "12StampaCostruttori";
    }
}
