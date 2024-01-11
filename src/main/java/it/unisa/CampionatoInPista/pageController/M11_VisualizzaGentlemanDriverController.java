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

    /** OPERAZIONE 11. Per ciascuna scuderia, visualizzare la percentuale di gentleman driver di cui si compone l’equipaggio.*/
    @GetMapping("/11VisualizzaGentlemanDriver")
    public String getVisualizzaGentlemanDriver(Model model) {
        List<Scuderia> datiScuderia = new ArrayList<>();
        List<Integer> numPiloti = new ArrayList<>();
        List<Integer> numGD = new ArrayList<>();
        List<Double> percentuale = new ArrayList<>();

        try {
        /*Calcola il numero totale di piloti per ogni scuderia
          e conta quante di queste persone sono classificate come "gentleman driver" (piloti non professionisti)
          basandosi sul campo 'FinanziatoreGD' uguale a 'SI' nella tabella dei piloti.
          Il risultato viene raggruppato per il nome della scuderia.*/
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                "SELECT s.Nome AS NomeScuderia, COUNT(DISTINCT g.id_Pilota) AS NumeroTotalePiloti, " +
                        "SUM(CASE WHEN p.FinanziatoreGD = 'SI' THEN 1 ELSE 0 END) AS NumeroFinanziatoriSI " +
                        "FROM scuderia s " +
                        "LEFT JOIN guidare g ON s.TargaVettura = g.targa_vettura " +
                        "LEFT JOIN pilota p ON g.id_Pilota = p.ID " +
                        "GROUP BY s.Nome;");

        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            Scuderia scuderia = new Scuderia();
            scuderia.setNome(resultSet.getString("NomeScuderia"));
            datiScuderia.add(scuderia);

            int numeroPiloti = resultSet.getInt("NumeroTotalePiloti");
            int numeroFinanziatoriSI = resultSet.getInt("NumeroFinanziatoriSI");

            double percentualeGentlemanDriver =  ((double) numeroFinanziatoriSI / numeroPiloti) * 100;

            numPiloti.add(numeroPiloti);
            numGD.add(numeroFinanziatoriSI);
            percentuale.add(percentualeGentlemanDriver);
        }

        model.addAttribute("scuderia", datiScuderia);
        model.addAttribute("totalePiloti", numPiloti);
        model.addAttribute("numFinanziatoriSI", numGD);
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
