package it.unisa.CampionatoInPista.pageController;

import it.unisa.CampionatoInPista.database.DatabaseConnection;
import it.unisa.CampionatoInPista.domain.Pilota;
import it.unisa.CampionatoInPista.domain.Scuderia;
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
public class M11_VisualizzaGentlemanDriverController {
    @Autowired
    private DatabaseConnection databaseConnection;

    @GetMapping("/11VisualizzaGentlemanDriver")
    public String getVisualizzaGentlemanDriver(Model model) {
        List<Pilota> datiPilota = new ArrayList<>();
        List<Scuderia> datiScuderia = new ArrayList<>();
        List<Double> percentuale = new ArrayList<>();

        try {
            PreparedStatement contaStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT COUNT(*) AS NumeroTotalePiloti, " +
                            "SUM(CASE WHEN FinanziatoreGD = 'SI' THEN 1 ELSE 0 END) AS NumeroFinanziatoriSI " +
                            "FROM pilota;");

            ResultSet resultSet1 = contaStatement.executeQuery();
            int numeroPiloti = 0;
            int numeroFinanziatoriSI = 0;
            if (resultSet1.next()) {
                numeroPiloti = resultSet1.getInt("NumeroTotalePiloti");
                numeroFinanziatoriSI = resultSet1.getInt("NumeroFinanziatoriSI");
            }
            resultSet1.close();
            contaStatement.close();


            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT s.Nome AS Nome_Scuderia, s.NumFinanziamenti, p.Nome AS Nome_Pilota, p.TipoPilota, p.FinanziatoreGD " +
                            "FROM scuderia s " +
                            "LEFT JOIN finanziare f ON s.Nome = f.NomeScuderia " +
                            "LEFT JOIN pilota p ON f.id_Pilota = p.ID " +
                            "WHERE p.FinanziatoreGD = 'SI';");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Scuderia scuderia = new Scuderia();
                scuderia.setNome(resultSet.getString("Nome_Scuderia"));
                scuderia.setNumFinanziamenti(resultSet.getInt("NumFinanziamenti"));
                datiScuderia.add(scuderia);

                Pilota pilota = new Pilota();
                pilota.setNome(resultSet.getString("Nome_Pilota"));
                pilota.setTipoPilota(resultSet.getString("TipoPilota"));
                pilota.setFinanziatoreGD("SI".equals(resultSet.getString("FinanziatoreGD")));
                datiPilota.add(pilota);

                double percentualeGentlemanDriver = (double) numeroFinanziatoriSI / numeroPiloti * 100;

                percentuale.add(percentualeGentlemanDriver);
            }

            model.addAttribute("pilota", datiPilota);
            model.addAttribute("scuderia", datiScuderia);
            model.addAttribute("percentuale", percentuale);
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "11VisualizzaGentlemanDriver";

    }

}
