package it.unisa.CampionatoInPista.pageController;

import it.unisa.CampionatoInPista.database.DatabaseConnection;
import it.unisa.CampionatoInPista.domain.Gara;
import it.unisa.CampionatoInPista.domain.Partecipa;
import it.unisa.CampionatoInPista.domain.Scuderia;
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
public class Q15_ReportPuntiMinutiController {
        @Autowired
        private DatabaseConnection databaseConnection;

        /**OPERAZIONE 15.Stampare un report che elenchi ciascuna scuderia sulla base del rapporto punti/minuti di gara, mediando tra le macchine appartenenti a ciascuna scuderia. */
        @GetMapping("/15ReportPuntiMinuti")
        public String getReportPuntiMinuti(Model model) {

            List<Scuderia> datiScuderia = new ArrayList<>();
            List<Gara> datiGara = new ArrayList<>();
            List<Partecipa> datiPartecipa = new ArrayList<>();
            List<Double> durata = new ArrayList<>();
            List<Double> datirapportoPuntiMinuto = new ArrayList<>();

            try {
                /*Recupera il nome della scuderia (Nome_Scuderia),
                somma totale dei punti conseguiti dalla scuderia (Punti_Totali),
                la durata totale delle gare partecipate dalla scuderia convertita in minuti (Durata_Gare_Minuti),
                 e infine il rapporto tra i punti totali e la durata totale delle gare,
               espresso in punti al minuto (Rapporto_Punti_Minuto).*/
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                        "SELECT s.Nome AS Nome_Scuderia, " +
                                "SUM(p.Punti) AS Punti_Totali, " +
                                "SUM(TIME_TO_SEC(g.DurataOre))/60 AS Durata_Gare_Minuti, " +
                                "SUM(p.Punti) / (SUM(TIME_TO_SEC(g.DurataOre))/60) AS Rapporto_Punti_Minuto " +
                                "FROM scuderia s " +
                                "INNER JOIN vettura v ON s.TargaVettura = v.Targa " +
                                "LEFT JOIN partecipa pa ON v.Targa = pa.targa_Vettura " +
                                "LEFT JOIN gara g ON pa.id_Gara = g.ID_Gara " +
                                "LEFT JOIN partecipa p ON p.id_Gara = g.ID_Gara AND p.targa_Vettura = v.Targa " +
                                "GROUP BY s.Nome " +
                                "ORDER BY Rapporto_Punti_Minuto DESC;");

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Scuderia scuderia = new Scuderia();
                    scuderia.setNome(resultSet.getString("Nome_Scuderia"));
                    datiScuderia.add(scuderia);

                    Partecipa partecipa = new Partecipa();
                    partecipa.setPunti(resultSet.getInt("Punti_Totali"));
                    datiPartecipa.add(partecipa);

                    Gara gara = new Gara();
                    durata.add(resultSet.getDouble("Durata_Gare_Minuti"));
                    datiGara.add(gara);

                    datirapportoPuntiMinuto.add(resultSet.getDouble("Rapporto_Punti_Minuto"));
                }

                model.addAttribute("scuderia", datiScuderia);
                model.addAttribute("partecipa", datiPartecipa);
                model.addAttribute("gara", datiGara);
                model.addAttribute("DurataGareMinuti", durata);
                model.addAttribute("rapportoPuntiMinuto", datirapportoPuntiMinuto);
                resultSet.close();
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                return "errore";
            }
            return "15ReportPuntiMinuti";
        }
}
