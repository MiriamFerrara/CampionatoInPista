package it.unisa.CampionatoInPista.pageController;

import it.unisa.CampionatoInPista.database.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/CampionatoInPista")
public class G7_VerificaComponenteController {
    @Autowired
    private DatabaseConnection databaseConnection;

    @GetMapping("/7VerificaComponente")
    public String getVerificaComponente() {

        return "7VerificaComponente";
    }

    @PostMapping("/7VerificaComponente")
    public String VerificaComponente() {


        return "successo";
    }

}
