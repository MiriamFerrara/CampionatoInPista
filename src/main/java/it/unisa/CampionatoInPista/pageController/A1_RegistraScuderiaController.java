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

    @GetMapping("/1RegistraScuderia")
    public String getRegistraScuderia(Model model) {
        List<String> targhe = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT Targa FROM vettura");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                targhe.add(resultSet.getString("Targa"));
            }
            model.addAttribute("targa", targhe);
           /* for(int i=0; i< targhe.size(); i++) {
                System.out.println("Lista Vetture: " + targhe.get(i));
            }*/
            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }

        return "1RegistraScuderia";
    }
    @PostMapping("/1RegistraScuderia")
    public String registraScuderia(Scuderia scuderia, final @RequestParam("targa") String targa) {

        try{
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "INSERT INTO scuderia (Nome, Sede, Paese, NumFinanziamenti, TargaVettura) VALUES (?, ?, ?, ?, ?)");
            preparedStatement.setString(1, scuderia.getNome());
            preparedStatement.setString(2, scuderia.getSede());
            preparedStatement.setString(3, scuderia.getPaese());
            preparedStatement.setInt(4, scuderia.getNumFinanziamenti());
            preparedStatement.setString(5, targa);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "successo";
    }
}