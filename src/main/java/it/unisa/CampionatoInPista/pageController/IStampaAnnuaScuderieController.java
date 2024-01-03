package it.unisa.CampionatoInPista.pageController;


import it.unisa.CampionatoInPista.database.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/CampionatoInPista")
public class IStampaAnnuaScuderieController {
    @Autowired
    private DatabaseConnection databaseConnection;

    @GetMapping("/9StampaAnnuaScuderie")
    public String getStampaAnnuaScuderie(Model model, final @RequestParam("DataEvento") Date year) {

        List<Date> anni = new ArrayList<>();

        try {
            PreparedStatement preparedStatement1 = databaseConnection.getConnection().prepareStatement(
                    "SELECT DISTINCT YEAR(DataEvento) FROM gara;");
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                anni.add(resultSet1.getDate("DataEvento"));
            }
            model.addAttribute("DataEvento", anni);

            resultSet1.close();
            preparedStatement1.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "9StampaAnnuaScuderie";
    }

    @PostMapping ("/9StampaAnnuaScuderie")
    public String StampaAnnuaScuderie(Model model, final @RequestParam("DataEvento") Date year) {
        List<String> datiScuderia = new ArrayList<>();
        try {

            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT DISTINCT s.Nome\n" +
                            "FROM scuderia s\n" +
                            "JOIN vettura v ON s.TargaVettura = v.Targa\n" +
                            "JOIN partecipa p ON v.Targa = p.targa_Vettura\n" +
                            "JOIN gara g ON p.id_Gara = g.ID_Gara\n" +
                            "WHERE YEAR(g.DataEvento) = ?;"
            );

            preparedStatement.setDate(1, year);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                datiScuderia.add(resultSet.getString("Nome"));
            }
            model.addAttribute("scuderieAnnuali", datiScuderia);


            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "9StampaAnnuaScuderie";
    }
}
