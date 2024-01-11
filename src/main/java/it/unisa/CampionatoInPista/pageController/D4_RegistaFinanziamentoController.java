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
public class D4_RegistaFinanziamentoController {
    @Autowired
    private DatabaseConnection databaseConnection;

    /** OPERAZIONE 4. Registrazione di un finanziamento per una scuderia.*/
    @GetMapping("/4RegistraFinanzimento")
    public String getRegistraFinanziamento(Model model) {
        List<String> scuderie = new ArrayList<>();
        List<String> piloti = new ArrayList<>();

        try {
            // Selezione degli ID dei piloti dalla tabella Pilota
            PreparedStatement selezionaPStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT ID FROM Pilota;");
            ResultSet resultSet1 = selezionaPStatement.executeQuery();
            while (resultSet1.next()) {
                piloti.add(resultSet1.getString("ID"));
            }
            model.addAttribute("IDPilota", piloti);
            resultSet1.close();
            selezionaPStatement.close();

            // Selezione dei nomi delle scuderie dalla tabella scuderia
            PreparedStatement selezionaSStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT Nome FROM scuderia;");
            ResultSet resultSet = selezionaSStatement.executeQuery();
            while (resultSet.next()) {
                scuderie.add(resultSet.getString("Nome"));
            }
            model.addAttribute("NomeScuderia", scuderie);
            resultSet.close();
            selezionaSStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }

        return "4RegistraFinanzimento";
    }


    @PostMapping("/4RegistraFinanzimento")
    public String RegistraFinanziamento(final @RequestParam("IDPilota") String IDPilota,
                                  final @RequestParam("NomeScuderia") String nome,
                                  final @RequestParam("quantita") Float quantita,
                                  final @RequestParam("data") Date data) {

        try{
            // Inserimento dei dettagli del finanziamento nella tabella Finanziare
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "INSERT INTO Finanziare (QuantitaDenaro, Data, id_Pilota, NomeScuderia) VALUES (?, ?, ?, ?);");
            preparedStatement.setFloat(1, quantita);
            preparedStatement.setDate(2, data);
            preparedStatement.setString(3,IDPilota);
            preparedStatement.setString(4, nome);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            // Aggiornamento del numero di finanziamenti per la scuderia selezionata
            PreparedStatement updateStatement = databaseConnection.getConnection().prepareStatement(
                    "UPDATE scuderia SET NumFinanziamenti = NumFinanziamenti + 1 WHERE Nome = ?;");
            updateStatement.setString(1, nome);
            updateStatement.executeUpdate();
            updateStatement.close();

            // Aggiornamento dello stato finanziatoreGD a "SI" per il pilota selezionato
            PreparedStatement updateFinanziatoreStatement = databaseConnection.getConnection().prepareStatement(
                    "UPDATE pilota SET FinanziatoreGD = 'SI' WHERE ID = ?;");
            updateFinanziatoreStatement.setString(1, IDPilota);
            updateFinanziatoreStatement.executeUpdate();
            updateFinanziatoreStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "successo";
    }
}