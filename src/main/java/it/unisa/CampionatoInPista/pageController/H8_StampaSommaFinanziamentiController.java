package it.unisa.CampionatoInPista.pageController;

import it.unisa.CampionatoInPista.database.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/CampionatoInPista")
public class H8_StampaSommaFinanziamentiController {


        @Autowired
        private DatabaseConnection databaseConnection;

        @GetMapping("/8StampaSommaFinanziamenti")
        public String getStampaSommaFinanziamenti(Model model) {
            List<String> datiScuderia  = new ArrayList<>();
            List<Double> datiFinanziamento = new ArrayList<>();
            try {
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                        "SELECT s.Nome, SUM(f.QuantitaDenaro) AS TotaleFinanziamenti " +
                                "FROM scuderia s " +
                                "LEFT JOIN finanziare f ON s.Nome = f.NomeScuderia " +
                                "GROUP BY s.Nome;");

                ResultSet risultati = preparedStatement.executeQuery();

                while (risultati.next()) {
                    datiScuderia.add(risultati.getString("Nome"));
                    datiFinanziamento.add(risultati.getDouble("TotaleFinanziamenti"));
                }
                model.addAttribute("scuderia", datiScuderia);
                model.addAttribute("finanziamento", datiFinanziamento);
                risultati.close();
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                return "errore";
            }
            return "8StampaSommaFinanziamenti";
        }
    }

