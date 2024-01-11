package it.unisa.CampionatoInPista.pageController;

import it.unisa.CampionatoInPista.database.DatabaseConnection;
import it.unisa.CampionatoInPista.domain.Partecipa;
import it.unisa.CampionatoInPista.domain.Vettura;
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
public class O13_StampaClassificaPuntiController {

        @Autowired
        private DatabaseConnection databaseConnection;
        /**OPERAZIONE 13. Stampa della classifica finale dei punti conseguiti da tutte le vetture.*/
        @GetMapping("/13StampaClassificaPunti")
        public String getStampaClassificaPunti(Model model) {
            List<Vettura> datiVettura = new ArrayList<>();
            List<Partecipa> datiPartecipa = new ArrayList<>();

            try {
                /* Recupera la targa di ogni vettura insieme alla somma totale dei punti conseguiti da quella vettura attraverso la partecipazione alle gare.
                    Il risultato è ordinato in modo descendente in base ai PuntiTotali, creando così la classifica finale.*/
                PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                        "SELECT DISTINCT v.Targa, SUM(p.Punti) AS PuntiTotali " +
                                "FROM vettura v " +
                                "LEFT JOIN partecipa p ON v.Targa = p.targa_Vettura " +
                                "GROUP BY v.Targa " +
                                "ORDER BY PuntiTotali DESC;");

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Vettura vettura = new Vettura();
                    vettura.setTarga(resultSet.getString("Targa"));
                    datiVettura.add(vettura);

                    Partecipa partecipa = new Partecipa();
                    partecipa.setPunti(resultSet.getInt("PuntiTotali"));
                    datiPartecipa.add(partecipa);

                }

                model.addAttribute("vettura", datiVettura);
                model.addAttribute("partecipa", datiPartecipa);
                resultSet.close();
                preparedStatement.close();

            } catch (SQLException e) {
                e.printStackTrace();
                return "errore";
            }

            return "13StampaClassificaPunti";
        }
}
