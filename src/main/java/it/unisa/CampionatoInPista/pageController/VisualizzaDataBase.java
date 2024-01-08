package it.unisa.CampionatoInPista.pageController;

import it.unisa.CampionatoInPista.database.DatabaseConnection;
import it.unisa.CampionatoInPista.domain.*;
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
public class VisualizzaDataBase {

    @Autowired
    private DatabaseConnection databaseConnection;

    @GetMapping("/VisualizzaDatabase")
    public String getDatbase(Model model) {

        try {
            PreparedStatement circuitoStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM circuito");

            ResultSet circuitoResultSet = circuitoStatement.executeQuery();
            List<Circuito> datiCircuito = new ArrayList<>();
            while (circuitoResultSet.next()) {
                Circuito circuito = new Circuito();
                circuito.setID_Circuito(circuitoResultSet.getInt("ID_Circuito"));
                circuito.setNome(circuitoResultSet.getString("NomeC"));
                circuito.setPaeseResidenza(circuitoResultSet.getString("PaeseResidenza"));
                circuito.setLunghezza(circuitoResultSet.getInt("Lunghezza"));
                circuito.setNumero_Curve(circuitoResultSet.getInt("NumCurve"));
                datiCircuito.add(circuito);
            }

            circuitoResultSet.close();
            circuitoStatement.close();

            model.addAttribute("datiCircuito", datiCircuito);



            PreparedStatement garaStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM gara");

            ResultSet garaResultSet = garaStatement.executeQuery();
            List<Gara> datiGara = new ArrayList<>();
            while (garaResultSet.next()) {
                Gara gara = new Gara();
                gara.setId(garaResultSet.getInt("ID_Gara"));
                gara.setNome(garaResultSet.getString("NomeG"));
                gara.setDataEvento(garaResultSet.getDate("DataEvento"));
                gara.setDurataOre(garaResultSet.getTime("DurataOre"));
                gara.setTipoGara(garaResultSet.getString("TipoGara"));
                gara.setIdCircuito(garaResultSet.getInt("id_Circuito"));
                datiGara.add(gara);
            }

            garaResultSet.close();
            garaStatement.close();

            model.addAttribute("datiGara", datiGara);



            PreparedStatement vetturaStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM vettura");
            ResultSet vetturaResultSet = vetturaStatement.executeQuery();

            List<Vettura> dativetture = new ArrayList<>();
            while (vetturaResultSet.next()) {
                Vettura vettura = new Vettura();
                vettura.setTarga(vetturaResultSet.getString("Targa"));
                vettura.setNumGara(vetturaResultSet.getInt("NumGara"));
                vettura.setModello(vetturaResultSet.getString("Modello"));
                dativetture.add(vettura);
            }
            vetturaResultSet.close();
            vetturaStatement.close();
            model.addAttribute("vetture", dativetture);



            PreparedStatement costruttoreStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM costruttore");
            ResultSet costruttoreResultSet = costruttoreStatement.executeQuery();

            List<Costruttore> daticostruttori = new ArrayList<>();
            while (costruttoreResultSet.next()) {
                Costruttore costruttore = new Costruttore();
                costruttore.setNome(costruttoreResultSet.getString("Nome"));
                costruttore.setRagioneSociale(costruttoreResultSet.getString("RagioneSociale"));
                costruttore.setSedeFabbrica(costruttoreResultSet.getString("SedeFabbrica"));
                costruttore.setNumComponenti(costruttoreResultSet.getInt("NumComponenti"));
                daticostruttori.add(costruttore);
            }
            costruttoreResultSet.close();
            costruttoreStatement.close();

            model.addAttribute("costruttori", daticostruttori);


            PreparedStatement componenteStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM componente");
            ResultSet componenteResultSet = componenteStatement.executeQuery();

            List<Componente> daticomponenti = new ArrayList<>();
            while (componenteResultSet.next()) {
                Componente componente = new Componente();
                componente.setCodice(componenteResultSet.getInt("Codice"));
                componente.setDataInstallazione(componenteResultSet.getDate("DataInstallazione"));
                componente.setCosto(componenteResultSet.getFloat("Costo"));
                componente.setTipoComponente(componenteResultSet.getString("TipoComponente"));
                componente.setTipoMateriale(componenteResultSet.getString("TipoMateriale"));
                componente.setPeso(componenteResultSet.getFloat("Peso"));
                componente.setNumMarce(componenteResultSet.getString("NumMarce"));
                componente.setCilindrata(componenteResultSet.getString("Cilindrata"));
                componente.setTipoMotore(componenteResultSet.getString("TipoMotore"));
                componente.setNumCilindri(componenteResultSet.getInt("NumCilindri"));
                componente.setNomeCostruttore(componenteResultSet.getString("NomeCostruttore"));
                componente.setTargaVettura(componenteResultSet.getString("TargaVettura"));
                daticomponenti.add(componente);
            }
            componenteResultSet.close();
            componenteStatement.close();
            model.addAttribute("componenti", daticomponenti);


            PreparedStatement pilotaStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM pilota");

            ResultSet pilotaresultSet = pilotaStatement.executeQuery();
            List<Pilota> datiPilota = new ArrayList<>();
            while (pilotaresultSet.next()) {
                Pilota pilota = new Pilota();
                pilota.setID(pilotaresultSet.getInt("ID"));
                pilota.setNome(pilotaresultSet.getString("Nome"));
                pilota.setCognome(pilotaresultSet.getString("Cognome"));
                pilota.setDataNascita(pilotaresultSet.getDate("DataNascita"));
                pilota.setNazionalita(pilotaresultSet.getString("Nazionalita"));
                pilota.setTipoPilota(pilotaresultSet.getString("TipoPilota"));
                pilota.setLicenzePossedute(pilotaresultSet.getInt("LicenzePossedute"));
                pilota.setDataLicenza(pilotaresultSet.getDate("DataLicenza"));
                pilota.setFinanziatoreGD(pilotaresultSet.getString("FinanziatoreGD"));
                datiPilota.add(pilota);
            }

            pilotaresultSet.close();
            pilotaStatement.close();

            model.addAttribute("datiPilota", datiPilota);


            PreparedStatement op1Statement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM scuderia");
            ResultSet resultSet = op1Statement.executeQuery();
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

            resultSet.close();
            op1Statement.close();

            model.addAttribute("scuderieInserite", scuderieInserite);


            PreparedStatement partecipaStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM partecipa");

            ResultSet partecipaResultSet = partecipaStatement.executeQuery();
            List<Partecipa> datiPartecipa = new ArrayList<>();
            while (partecipaResultSet.next()) {
                Partecipa partecipa = new Partecipa();
                partecipa.setIdGara(partecipaResultSet.getInt("id_Gara"));
                partecipa.setTargaVettura(partecipaResultSet.getString("targa_Vettura"));
                partecipa.setMotivoRitiro(partecipaResultSet.getString("MotivoRitiro"));
                partecipa.setPunti(partecipaResultSet.getInt("Punti"));
                datiPartecipa.add(partecipa);
            }

            partecipaResultSet.close();
            partecipaStatement.close();

            model.addAttribute("datiPartecipa", datiPartecipa);



            PreparedStatement guidareStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM guidare");
            ResultSet guidareResultSet =guidareStatement.executeQuery();

            List<Guidare> datiguidare = new ArrayList<>();
            while (guidareResultSet.next()) {
                Guidare guidare = new Guidare();
                guidare.setIdPilota(guidareResultSet.getInt("id_Pilota"));
                guidare.setTargaVettura(guidareResultSet.getString("targa_vettura"));
                datiguidare.add(guidare);
            }
            guidareResultSet.close();
            guidareStatement.close();

            model.addAttribute("guidare", datiguidare);


            PreparedStatement finanziareStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM finanziare");

            ResultSet finanziarersultSet = finanziareStatement.executeQuery();
            List<Finanziare> datifinanziare = new ArrayList<>();
            while (finanziarersultSet.next()) {
                Finanziare finanziare = new Finanziare();
                finanziare.setQuantitaDenaro(finanziarersultSet.getFloat("QuantitaDenaro"));
                finanziare.setData(finanziarersultSet.getDate("Data"));
                finanziare.setIdPilota(finanziarersultSet.getInt("id_Pilota"));
                finanziare.setNomeScuderia(finanziarersultSet.getString("NomeScuderia"));
                datifinanziare.add(finanziare);
            }

            pilotaresultSet.close();
            finanziareStatement.close();

            model.addAttribute("datiFinanziare", datifinanziare);


        } catch (SQLException e) {
            e.printStackTrace();
            return "errore";
        }
        return "VisualizzaDatabase";
    }

}
