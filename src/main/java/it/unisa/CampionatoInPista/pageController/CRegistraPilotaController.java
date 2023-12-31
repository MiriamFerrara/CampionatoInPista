package it.unisa.CampionatoInPista.pageController;

import it.unisa.CampionatoInPista.database.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/CampionatoInPista")
public class CRegistraPilotaController {
    @Autowired
    private DatabaseConnection databaseConnection;

    @GetMapping("/RegistraPilota")
    public String getRegistraVettura(Model model) {
        return null;
    }


}


