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
public class CRegistraPilotaController {
    @Autowired
    private DatabaseConnection databaseConnection;

    @GetMapping("/3AggiungiPilota")
    public String getRegistraVettura(Model model) {
        List<String[]> datiVettura = new ArrayList<>();
        List<String[]> datiPilota = new ArrayList<>();
        List<String> targhe = new ArrayList<>();
        List<String> piloti = new ArrayList<>();

        try {
            // Carica dati della vettura
            PreparedStatement vetturaStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM vettura");
            ResultSet resultSetV = vetturaStatement.executeQuery();
            while (resultSetV.next()) {
                datiVettura.add(new String[]{
                        resultSetV.getString("Targa"),
                        String.valueOf(resultSetV.getInt("NumGara")),
                        resultSetV.getString("Modello")
                });
            }
            model.addAttribute("datiVettura", datiVettura);

            resultSetV.close();
            vetturaStatement.close();

            // Carica dati del pilota
            PreparedStatement pilotaStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM pilota");
            ResultSet resultSetP = pilotaStatement.executeQuery();
            while (resultSetP.next()) {
                datiPilota.add(new String[]{
                        resultSetP.getString("ID"),
                        resultSetP.getString("Nome"),
                        resultSetP.getString("Cognome"),
                        String.valueOf(resultSetP.getDate("DataNascita")),
                        resultSetP.getString("Nazionalita"),
                        resultSetP.getString("TipoPilota"),
                        resultSetP.getString("LicenzePossedute"),
                        String.valueOf(resultSetP.getDate("DataLicenza")),
                        resultSetP.getString("FinanziatoreGD")
                });
            }
            model.addAttribute("datiPilota", datiPilota);
            resultSetP.close();
            pilotaStatement.close();


            PreparedStatement selezionaVStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT Targa FROM vettura");
            ResultSet resultSet = selezionaVStatement.executeQuery();
            while (resultSet.next()) {
                targhe.add(resultSet.getString("Targa"));
            }
            model.addAttribute("targa", targhe);
            resultSet.close();
            selezionaVStatement.close();

            PreparedStatement selezionaPStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT ID FROM Pilota");
            ResultSet resultSet1 = selezionaPStatement.executeQuery();
            while (resultSet1.next()) {
                piloti.add(resultSet1.getString("ID"));
            }
            model.addAttribute("IDPilota", piloti);
            resultSet1.close();
            selezionaPStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }

        return "3AggiungiPilota";
    }
    @PostMapping("/3AggiungiPilota")
    public String RegistraVettura(final @RequestParam("targa") String targa,
                                  final @RequestParam("IDPilota") String IDPilota,
                                  final @RequestParam("numComponenti") Integer componenti) {

        try{
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "INSERT INTO guidare (targa_Vettura, id_Pilota, NumComponentiPiloti) VALUES (?, ?, ?);");
            preparedStatement.setString(1, targa);
            preparedStatement.setString(2, IDPilota);
            preparedStatement.setInt(3, componenti);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "successo";
    }
}

