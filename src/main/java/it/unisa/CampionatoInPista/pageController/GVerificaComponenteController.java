package it.unisa.CampionatoInPista.pageController;

import it.unisa.CampionatoInPista.database.DatabaseConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/CampionatoInPista")
public class GVerificaComponenteController {
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
