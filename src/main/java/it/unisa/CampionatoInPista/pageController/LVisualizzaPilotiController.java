package it.unisa.CampionatoInPista.pageController;


import it.unisa.CampionatoInPista.database.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/CampionatoInPista")
public class LVisualizzaPilotiController {
    @Autowired
    private DatabaseConnection databaseConnection;

    @GetMapping("/10VisualizzaPiloti")
    public String getVisualizzaPiloti(){
        return "10VisualizzaPiloti";

    }

}
