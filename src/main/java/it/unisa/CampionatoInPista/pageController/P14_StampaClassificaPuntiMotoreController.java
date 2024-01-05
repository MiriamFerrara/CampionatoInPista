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

        @GetMapping("/14StampaClassificaPuntiMotore")
        public String getStampaClassificaPuntiMotore(Model model) {

            List<Componente> datiComponenteAspirato = new ArrayList<>();
            List<Partecipa> datiPartecipaAspirato = new ArrayList<>();
            List<Componente> datiComponenteTurbo = new ArrayList<>();
            List<Partecipa> datiPartecipaTurbo = new ArrayList<>();

            try {
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
