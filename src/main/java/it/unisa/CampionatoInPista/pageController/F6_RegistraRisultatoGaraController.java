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
public class F6_RegistraRisultatoGaraController {
    @Autowired
    private DatabaseConnection databaseConnection;

    /**OPERAZIONE 6. Registrazione del risultato conseguito da ciascuna vettura iscritta ad una gara.*/
    @GetMapping("/6RegistraRisultatoGara")
    public String getRegistraRisultatoGara(Model model) {
        List<String> targhe = new ArrayList<>();
        List<String> gare = new ArrayList<>();

        try {
            // Ottiene le targhe delle vetture
            PreparedStatement selezionaVStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT Targa FROM vettura");
            ResultSet resultSet = selezionaVStatement.executeQuery();
            while (resultSet.next()) {
                targhe.add(resultSet.getString("Targa"));
            }
            model.addAttribute("targa", targhe);
            resultSet.close();
            selezionaVStatement.close();

            // Ottiene gli ID delle gare
            PreparedStatement selezionaGStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT ID_Gara FROM gara");
            ResultSet resultSetG = selezionaGStatement.executeQuery();
            while (resultSetG.next()) {
                gare.add(resultSetG.getString("ID_Gara"));
            }
            model.addAttribute("IDGara", gare);
            resultSetG.close();
            selezionaGStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }

        return "6RegistraRisultatoGara";
    }

    // Registra il risultato della gara per una vettura specifica
    @PostMapping("/6RegistraRisultatoGara")
    public String RegistraRisultatoGara(final @RequestParam("IDGara") String IDGara,
                                        final @RequestParam("targa") String targa,
                                        final @RequestParam("MotivoRitiro") String MotivoRitiro,
                                        final @RequestParam("Punti") Integer Punti) {

        try{
            // Aggiorna la tabella partecipa con il motivo del ritiro e i punti ottenuti
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "UPDATE partecipa " +
                            "SET MotivoRitiro = ?, Punti = ? " +
                            "WHERE id_Gara = ? AND targa_Vettura = ?;");

            if ("Scegli".equals(MotivoRitiro)) {
                preparedStatement.setNull(1, Types.VARCHAR); // Imposta MotivoRitiro a NULL
            } else {
                preparedStatement.setString(1, MotivoRitiro);
            }
            preparedStatement.setInt(2, Punti);
            preparedStatement.setString(3, IDGara);
            preparedStatement.setString(4, targa);
            preparedStatement.executeUpdate();
            preparedStatement.close();

        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "successo";
    }
}

