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

    @GetMapping("/5IscrizioneGara")
    public String getIscrizioneGara(Model model) {
        List<String> targhe = new ArrayList<>();
        List<Integer> gare = new ArrayList<>();

        try {
            PreparedStatement selezionaVStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT Targa FROM vettura;");
            ResultSet resultSet = selezionaVStatement.executeQuery();
            while (resultSet.next()) {
                targhe.add(resultSet.getString("Targa"));
            }
            model.addAttribute("targa", targhe);
            resultSet.close();
            selezionaVStatement.close();

            PreparedStatement selezionaGStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT ID_Gara FROM gara");
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
            PreparedStatement verificaStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM partecipa p " +
                            "WHERE p.id_Gara = ? AND p.targa_Vettura = ?;");
            verificaStatement.setInt(1, IDGara);
            verificaStatement.setString(2, targa);
            ResultSet resultSet = verificaStatement.executeQuery();
            if(resultSet.next()){
                resultSet.close();
                verificaStatement.close();
                return "erroreOP5";
            }else {

                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                        "INSERT INTO partecipa (id_Gara, targa_Vettura) VALUES (?, ?);");
                preparedStatement.setInt(1, IDGara);
                preparedStatement.setString(2, targa);

                preparedStatement.executeUpdate();
                preparedStatement.close();

                PreparedStatement updateStatement = databaseConnection.getConnection().prepareStatement(
                        "UPDATE vettura SET NumGara = NumGara+1  WHERE Targa = ?");
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
