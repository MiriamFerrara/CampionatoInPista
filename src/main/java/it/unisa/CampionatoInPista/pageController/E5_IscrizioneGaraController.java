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
public class E5_IscrizioneGaraController {
    @Autowired
    private DatabaseConnection databaseConnection;

    /** OPERAZIONE 5. Iscrizione di una vettura ad una gara.*/
    @GetMapping("/5IscrizioneGara")
    public String getIscrizioneGara(Model model) {
        List<String> targhe = new ArrayList<>();
        List<Integer> gare = new ArrayList<>();

        try {
            // Ottieni le targhe delle vetture dalla tabella Vettura
            PreparedStatement selezionaVStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT Targa FROM vettura;");
            ResultSet resultSet = selezionaVStatement.executeQuery();
            while (resultSet.next()) {
                targhe.add(resultSet.getString("Targa"));
            }
            model.addAttribute("targa", targhe);
            resultSet.close();
            selezionaVStatement.close();

            // Ottieni gli ID delle gare dalla tabella Gara
            PreparedStatement selezionaGStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT ID_Gara FROM gara;");
            ResultSet resultSetG = selezionaGStatement.executeQuery();
            while (resultSetG.next()) {
                gare.add(resultSetG.getInt("ID_Gara"));
            }
            model.addAttribute("IDGara", gare);
            resultSetG.close();
            selezionaGStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }

        return "5IscrizioneGara";
    }

    @PostMapping("/5IscrizioneGara")
    public String IscrizioneGara(final @RequestParam("IDGara") Integer IDGara,
                                  final @RequestParam("targa") String targa) {

        try{
            // Verifica se la vettura è già iscritta a una specifica gara
            PreparedStatement verificaStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM partecipa p WHERE p.id_Gara = ? AND p.targa_Vettura = ?;");
            verificaStatement.setInt(1, IDGara);
            verificaStatement.setString(2, targa);
            ResultSet resultSet = verificaStatement.executeQuery();

            // Se la vettura è già iscritta, restituisci erroreOP5
            if(resultSet.next()){
                resultSet.close();
                verificaStatement.close();
                return "erroreOP5";
            }else {
                // Se la vettura non è già iscritta, inserisci la vettura nella tabella Partecipa
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                        "INSERT INTO partecipa (id_Gara, targa_Vettura) VALUES (?, ?);");
                preparedStatement.setInt(1, IDGara);
                preparedStatement.setString(2, targa);

                preparedStatement.executeUpdate();
                preparedStatement.close();

                // Aggiorna il numero di gare a cui è iscritta la vettura nella tabella Vettura
                PreparedStatement updateStatement = databaseConnection.getConnection().prepareStatement(
                        "UPDATE vettura SET NumGara = NumGara+1  WHERE Targa = ?;");
                updateStatement.setString(1, targa);
                updateStatement.executeUpdate();
                updateStatement.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "successo";
    }
}
