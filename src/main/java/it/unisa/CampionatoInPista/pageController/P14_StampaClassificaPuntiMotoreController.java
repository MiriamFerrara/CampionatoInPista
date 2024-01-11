package it.unisa.CampionatoInPista.pageController;
import it.unisa.CampionatoInPista.database.DatabaseConnection;
import it.unisa.CampionatoInPista.domain.Componente;
import it.unisa.CampionatoInPista.domain.Partecipa;
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
public class P14_StampaClassificaPuntiMotoreController {
        @Autowired
        private DatabaseConnection databaseConnection;

        /**OPERAZIONE 14.Stampa delle classifiche finali di punti per tipo di motore.*/
        @GetMapping("/14StampaClassificaPuntiMotore")
        public String getStampaClassificaPuntiMotore(Model model) {

            List<Componente> datiComponenteAspirato = new ArrayList<>();
            List<Partecipa> datiPartecipaAspirato = new ArrayList<>();
            List<Componente> datiComponenteTurbo = new ArrayList<>();
            List<Partecipa> datiPartecipaTurbo = new ArrayList<>();

            try {
                /*Recupera il tipo di motore (Aspirato),
            la targa della vettura e la somma totale dei punti conseguiti attraverso la partecipazione alle gare.
            I risultati sono raggruppati per tipo di motore e targa della vettura e ordinati in modo descendente in base ai PuntiTotali,
            creando così la classifica dei motori aspirati.*/
                PreparedStatement preparedStatementAspirato = databaseConnection.getConnection().prepareStatement(
                        "SELECT c.TipoMotore, c.TargaVettura, SUM(p.Punti) AS PuntiTotali " +
                                "FROM partecipa p " +
                                "JOIN vettura v ON p.targa_Vettura = v.Targa " +
                                "JOIN componente c ON p.targa_Vettura = c.TargaVettura " +
                                "WHERE c.TipoComponente = 'Motore' AND c.TipoMotore = 'Aspirato' " +
                                "GROUP BY c.TipoMotore, c.TargaVettura " +
                                "ORDER BY PuntiTotali DESC;");

                ResultSet resultSetAspirato = preparedStatementAspirato.executeQuery();

                while (resultSetAspirato.next()) {
                    Componente componente = new Componente();
                    componente.setTipoMotore(resultSetAspirato.getString("TipoMotore"));
                    componente.setTargaVettura(resultSetAspirato.getString("TargaVettura"));
                    datiComponenteAspirato.add(componente);

                    Partecipa partecipa = new Partecipa();
                    partecipa.setPunti(resultSetAspirato.getInt("PuntiTotali"));
                    datiPartecipaAspirato.add(partecipa);
                }

                model.addAttribute("componenteAspirato", datiComponenteAspirato);
                model.addAttribute("partecipaAspirato", datiPartecipaAspirato);

                resultSetAspirato.close();
                preparedStatementAspirato.close();

                /*Recupera il tipo di motore (Turbo),
            la targa della vettura e la somma totale dei punti conseguiti attraverso la partecipazione alle gare.
            I risultati sono raggruppati per tipo di motore e targa della vettura e ordinati in modo discendente
            in base ai PuntiTotali, creando così la classifica dei motori turbo.*/
                PreparedStatement preparedStatementTurbo = databaseConnection.getConnection().prepareStatement(
                        "SELECT c.TipoMotore, c.TargaVettura, SUM(p.Punti) AS PuntiTotali " +
                                "FROM partecipa p " +
                                "JOIN vettura v ON p.targa_Vettura = v.Targa " +
                                "JOIN componente c ON p.targa_Vettura = c.TargaVettura " +
                                "WHERE c.TipoComponente = 'Motore' AND c.TipoMotore = 'Turbo' " +
                                "GROUP BY c.TipoMotore, c.TargaVettura " +
                                "ORDER BY PuntiTotali DESC;");

                ResultSet resultSetTurbo = preparedStatementTurbo.executeQuery();


                while (resultSetTurbo.next()) {
                    Componente componente = new Componente();
                    componente.setTipoMotore(resultSetTurbo.getString("TipoMotore"));
                    componente.setTargaVettura(resultSetTurbo.getString("TargaVettura"));
                    datiComponenteTurbo.add(componente);

                    Partecipa partecipa = new Partecipa();
                    partecipa.setPunti(resultSetTurbo.getInt("PuntiTotali"));
                    datiPartecipaTurbo.add(partecipa);
                }

                model.addAttribute("componenteTurbo", datiComponenteTurbo);
                model.addAttribute("partecipaTurbo", datiPartecipaTurbo);

                resultSetTurbo.close();
                preparedStatementTurbo.close();

            } catch (SQLException e) {
                e.printStackTrace();
                return "errore";
            }
            return "14StampaClassificaPuntiMotore";
        }
}
