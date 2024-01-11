package it.unisa.CampionatoInPista.pageController;

import it.unisa.CampionatoInPista.database.DatabaseConnection;
import it.unisa.CampionatoInPista.domain.Pilota;
import it.unisa.CampionatoInPista.domain.Vettura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/CampionatoInPista")
public class C3_RegistraPilotaController {
    @Autowired
    private DatabaseConnection databaseConnection;

    /** OPERAZIONE 3. Aggiunta di un nuovo pilota ad un equipaggio.*/

    // Ottiene i dati delle vetture e dei piloti per renderli disponibili nella pagina di aggiunta del pilota
    @GetMapping("/3AggiungiPilota")
    public String getRegistraPilota(Model model) {
        List<Vettura> datiVettura = new ArrayList<>();
        List<Pilota> datiPilota = new ArrayList<>();
        List<String> targhe = new ArrayList<>();
        List<String> piloti = new ArrayList<>();

        try {
            // Carica dati della vettura
            PreparedStatement vetturaStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM vettura;");

            ResultSet resultSetV = vetturaStatement.executeQuery();
            while (resultSetV.next()) {
                Vettura vettura = new Vettura();
                vettura.setTarga(resultSetV.getString("Targa"));
                vettura.setNumGara(resultSetV.getInt("NumGara"));
                vettura.setModello(resultSetV.getString("Modello"));
                datiVettura.add(vettura);
            }

            model.addAttribute("datiVettura", datiVettura);

            resultSetV.close();
            vetturaStatement.close();

            // Carica dati del pilota
            PreparedStatement pilotaStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM pilota;");
            ResultSet resultSetP = pilotaStatement.executeQuery();
            while (resultSetP.next()) {
                Pilota pilota = new Pilota();
                        pilota.setID(resultSetP.getInt("ID"));
                        pilota.setNome(resultSetP.getString("Nome"));
                        pilota.setCognome(resultSetP.getString("Cognome"));
                        pilota.setDataNascita(resultSetP.getDate("DataNascita"));
                        pilota.setNazionalita(resultSetP.getString("Nazionalita"));
                        pilota.setTipoPilota(resultSetP.getString("TipoPilota"));
                        pilota.setLicenzePossedute(resultSetP.getInt("LicenzePossedute"));
                        pilota.setDataLicenza(resultSetP.getDate("DataLicenza"));
                        pilota.setFinanziatoreGD(resultSetP.getString("FinanziatoreGD"));
                datiPilota.add(pilota);
            }
            model.addAttribute("datiPilota", datiPilota);
            resultSetP.close();
            pilotaStatement.close();

            // Carica le targhe delle vetture
            PreparedStatement selezionaVStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT Targa FROM vettura;");
            ResultSet resultSet = selezionaVStatement.executeQuery();
            while (resultSet.next()) {
                targhe.add(resultSet.getString("Targa"));
            }
            model.addAttribute("targa", targhe);
            resultSet.close();
            selezionaVStatement.close();

            PreparedStatement selezionaPStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT ID FROM Pilota;");
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

    // Registra l'associazione del pilota con la vettura selezionata
    @PostMapping("/3AggiungiPilota")
    public String RegistraPilota(final @RequestParam("targa") String targa,
                                  final @RequestParam("IDPilota") String IDPilota) {

        try{
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                        "INSERT INTO guidare (targa_Vettura, id_Pilota) VALUES (?, ?);");
                preparedStatement.setString(1, targa);
                preparedStatement.setString(2, IDPilota);
                preparedStatement.executeUpdate();
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                return "errore";
            }
            return "successo";
        }
}

