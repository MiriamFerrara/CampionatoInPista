package it.unisa.CampionatoInPista.pageController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/CampionatoInPista")
public class HomeController {

    @GetMapping("/Homepage")
    public String getHomepage() {
        return "Homepage";
    }
    @GetMapping("/visualizzaTabelle")
    public String getVisualizzaTabelle() {
        return "visualizzaTabelle/VisualizzaTabelle";
    }










/*
    @GetMapping("/VisualizzaTabelle")
    public String getVisualizzaTabelle() {
        return "VisualizzaTabelle";
    }

    @GetMapping("/VisualizzaScuderia")
    public String visualizzaScuderia(Model model) {
        List<Scuderia> scuderie = new ArrayList<>();

        try {
            PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                    "SELECT * FROM scuderia");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Scuderia scuderia = new Scuderia();
                scuderia.setNome(resultSet.getString("Nome"));
                scuderia.setSede(resultSet.getString("Sede"));
                scuderia.setPaese(resultSet.getString("Paese"));
                scuderia.setNumFinanziamenti(resultSet.getInt("NumFinanziamenti"));
                scuderia.setTargaVettura(resultSet.getString("TargaVettura"));
                scuderie.add(scuderia);
            }
            model.addAttribute("scuderie", scuderie);

            resultSet.close();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return "VisualizzaScuderia";
    }*/
}

