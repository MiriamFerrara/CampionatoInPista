package it.unisa.CampionatoInPista.pageController;


import it.unisa.CampionatoInPista.database.DatabaseConnection;
import it.unisa.CampionatoInPista.domain.*;
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
public class L10_VisualizzaPilotiController {
    @Autowired
    private DatabaseConnection databaseConnection;

    @GetMapping("/10VisualizzaPiloti")
    public String getVisualizzaPiloti(Model model) {
        List<Pilota> datiPilota = new ArrayList<>();
        List<Partecipa> datiPartecipa = new ArrayList<>();
        List<Circuito> datiCircuito = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT p.ID, p.Nome, p.Cognome, p.Nazionalita,  " +
                            "c.ID_Circuito, c.PaeseResidenza, c.NomeC, pa.targa_vettura, SUM(pa.Punti) AS PuntiTotali " +
                            "FROM pilota p  " +
                            "INNER JOIN guidare g ON p.ID = g.id_Pilota " +
                            "INNER JOIN partecipa pa ON g.targa_vettura = pa.targa_vettura " +
                            "INNER JOIN gara ga ON pa.id_Gara = ga.ID_Gara " +
                            "INNER JOIN circuito c ON ga.id_Circuito = c.ID_Circuito  " +
                            "WHERE p.Nazionalita = c.PaeseResidenza  " +
                            "GROUP BY p.ID, p.Nome, p.Cognome, p.Nazionalita, c.ID_Circuito, c.PaeseResidenza, c.NomeC, pa.targa_vettura " +
                            "HAVING SUM(pa.Punti) <> 0 AND SUM(pa.Punti) IS NOT NULL;");

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                //ID, Nome, Cognome, DataNascita, Nazionalità, TipoPilota, #LicenzePossedute, DataLicenza, FinanziatoreGD
                Pilota pilota = new Pilota();
                pilota.setID(resultSet.getInt("ID"));
                pilota.setNome(resultSet.getString("Nome"));
                pilota.setCognome(resultSet.getString("Cognome"));
                pilota.setNazionalita(resultSet.getString("Nazionalita"));
                datiPilota.add(pilota);

                Circuito circuito = new Circuito();
                //ID, NomeC, PaeseResidenza, Lunghezza, #Curve
                circuito.setID_Circuito(resultSet.getInt("ID_Circuito"));
                circuito.setNome(resultSet.getString("NomeC"));
                circuito.setPaeseResidenza(resultSet.getString("PaeseResidenza"));
                datiCircuito.add(circuito);

                Partecipa partecipa = new Partecipa();
                partecipa.setTargaVettura(resultSet.getString("targa_Vettura"));
                partecipa.setPunti(resultSet.getInt("PuntiTotali"));
                datiPartecipa.add(partecipa);
            }
            model.addAttribute("pilota", datiPilota);
            model.addAttribute("circuito", datiCircuito);
            model.addAttribute("partecipa", datiPartecipa);
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
            return "10VisualizzaPiloti";

        }
}

