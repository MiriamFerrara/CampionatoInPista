package it.unisa.CampionatoInPista.pageController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("CampionatoInPista")
public class HomeController {

    @GetMapping("/Homepage")
    public String getHomepage() {
        return "Homepage";
    }
}
