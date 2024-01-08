package it.unisa.CampionatoInPista.pageController;


import it.unisa.CampionatoInPista.database.DatabaseConnection;
import it.unisa.CampionatoInPista.domain.Gara;
import it.unisa.CampionatoInPista.domain.Partecipa;
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
public class I9_StampaAnnuaScuderieController {
    @Autowired
    private DatabaseConnection databaseConnection;

    @GetMapping ("/9StampaAnnuaScuderie")
    public String getStampaAnnuaScuderie(Model model) {
        List<String> anni = new ArrayList<>();

        try {
            PreparedStatement preparedStatement1 = databaseConnection.getConnection().prepareStatement(
                    "SELECT DISTINCT YEAR(DataEvento) FROM gara;");
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            while (resultSet1.next()) {
                anni.add(resultSet1.getString("YEAR(DataEvento)"));
            }
            model.addAttribute("anno", anni);

            resultSet1.close();
            preparedStatement1.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "9StampaAnnuaScuderie";
    }


    @PostMapping ("/9StampaAnnuaScuderie")
    public String StampaAnnuaScuderie(Model model, final @RequestParam("data") String anno) {

        List<Scuderia> datiScuderia = new ArrayList<>();
        List<Gara> datiGara = new ArrayList<>();
        List<Partecipa> datiPartecipa = new ArrayList<>();


        try {
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT DISTINCT p.*, s.Nome, s.Sede, s.Paese, s.NumFinanziamenti, " +
                            "g.NomeG, g.DataEvento, g.DurataOre, g.TipoGara, g.id_Circuito " +
                            "FROM scuderia s " +
                            "JOIN vettura v ON s.TargaVettura = v.Targa " +
                            "JOIN partecipa p ON v.Targa = p.targa_Vettura " +
                            "JOIN gara g ON p.id_Gara = g.ID_Gara " +
                            "WHERE YEAR(g.DataEvento) = ?;"
            );

            preparedStatement.setString(1, anno);
            //System.out.println("Anno: " + anno);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Scuderia scuderia = new Scuderia();
                scuderia.setNome(resultSet.getString("Nome"));
                scuderia.setSede(resultSet.getString("Sede"));
                scuderia.setPaese(resultSet.getString("Paese"));
                scuderia.setNumFinanziamenti(resultSet.getInt("NumFinanziamenti"));
                datiScuderia.add(scuderia);


                Partecipa partecipa = new Partecipa();
                partecipa.setIdGara(resultSet.getInt("id_Gara"));
                partecipa.setTargaVettura(resultSet.getString("targa_Vettura"));
                partecipa.setMotivoRitiro(resultSet.getString("MotivoRitiro"));
                partecipa.setPunti(resultSet.getInt("Punti"));
                datiPartecipa.add(partecipa);

                Gara gara = new Gara();
                gara.setNome(resultSet.getString("NomeG"));
                gara.setDataEvento(resultSet.getDate("DataEvento"));
                gara.setDurataOre(resultSet.getTime("DurataOre"));
                gara.setTipoGara(resultSet.getString("TipoGara"));
                gara.setIdCircuito(resultSet.getInt("id_Circuito"));
                datiGara.add(gara);

            }
            model.addAttribute("scuderieAnnuali", datiScuderia);
            model.addAttribute("partecipa", datiPartecipa);
            model.addAttribute("gareAnnuali", datiGara);

            resultSet.close();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "9StampaAnnuaScuderie";
    }
}
