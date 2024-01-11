package it.unisa.CampionatoInPista.pageController;


import it.unisa.CampionatoInPista.database.DatabaseConnection;
import it.unisa.CampionatoInPista.domain.Scuderia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Controller
@RequestMapping("/CampionatoInPista")
public class A1_RegistraScuderiaController {
    @Autowired
    private DatabaseConnection databaseConnection;

    /** OPERAZIONE 1. Registrazione di una scuderia. */

    /**
     * Ottiene un elenco di targhe di vetture dal database e li aggiunge al modello per renderli disponibili nella pagina 1RegistraScuderia.html.
     * Visualizza la lista delle targhe disponibili nella tabella vettura.
     */
    @GetMapping("/1RegistraScuderia")
    public String getRegistraScuderia(Model model) {

        List<String> targhe = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT Targa FROM vettura;");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                targhe.add(resultSet.getString("Targa"));
            }
            model.addAttribute("targa", targhe);

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }

        return "1RegistraScuderia";
    }

    /**
     * Registra una nuova scuderia nel database utilizzando i dati ricevuti dal form.
     * Successivamente, recupera tutti i dati della scuderia appena inserita usando il suo nome e li memorizza in una lista di oggetti Scuderia.
     */
    @PostMapping("/1RegistraScuderia")
    public String registraScuderia(Model model, Scuderia scuderia, final @RequestParam("targa") String targa) {

        try{
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "INSERT INTO scuderia (Nome, Sede, Paese, NumFinanziamenti, TargaVettura) VALUES (?, ?, ?, ?, ?);");
            preparedStatement.setString(1, scuderia.getNome());
            preparedStatement.setString(2, scuderia.getSede());
            preparedStatement.setString(3, scuderia.getPaese());
            preparedStatement.setInt(4, scuderia.getNumFinanziamenti());
            preparedStatement.setString(5, targa);
            preparedStatement.executeUpdate();
            preparedStatement.close();

            // Recupera tutti i dati della scuderia appena inserita
            PreparedStatement recuperaScuderiaStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM scuderia WHERE Nome = ?");
            recuperaScuderiaStatement.setString(1, scuderia.getNome());
            ResultSet resultSet = recuperaScuderiaStatement.executeQuery();

            // Lista per mantenere i dati della scuderia appena inserita
            List<Scuderia> scuderieInserite = new ArrayList<>();
            while (resultSet.next()) {
                Scuderia scuderiaInserita = new Scuderia();
                scuderiaInserita.setNome(resultSet.getString("Nome"));
                scuderiaInserita.setSede(resultSet.getString("Sede"));
                scuderiaInserita.setPaese(resultSet.getString("Paese"));
                scuderiaInserita.setNumFinanziamenti(resultSet.getInt("NumFinanziamenti"));
                scuderiaInserita.setTargaVettura(resultSet.getString("TargaVettura"));
                scuderieInserite.add(scuderiaInserita);
            }

            // Chiudi il result set e lo statement
            resultSet.close();
            recuperaScuderiaStatement.close();

            // Aggiungi i dati della scuderia inserita al modello
            model.addAttribute("scuderieInserite", scuderieInserite);

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "successo";
    }
}