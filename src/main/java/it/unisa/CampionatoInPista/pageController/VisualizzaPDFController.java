package it.unisa.CampionatoInPista.pageController;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Controller
@RequestMapping("/CampionatoInPista")
public class VisualizzaPDFController {

    @GetMapping("/visualizzaPDF")
    @ResponseBody
    public ResponseEntity<byte[]> visualizzaPDF() throws IOException {
        String pathToPDF = "C:/Users/Utente/IdeaProjects/CampionatoInPista/1-Documentazione/[BD][FerraraMiriam]ProgettoDiCorso-Parte1-Parte2.pdf";
        Resource resource = new FileSystemResource(pathToPDF);

        if (!resource.exists()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File non trovato".getBytes());
        }

        byte[] pdfBytes;
        try {
            pdfBytes = Files.readAllBytes(Path.of(resource.getURI()));
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore nella lettura del file".getBytes());
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }


        @GetMapping("/visualizzaSchemi")
        @ResponseBody
        public ResponseEntity<byte[]> visualizzaSchemi() throws IOException {
            String pathToPDF = "C:/Users/Utente/IdeaProjects/CampionatoInPista/1-Documentazione/[BD][FerraraMiriam]Schemi.pdf";
            Resource resource = new FileSystemResource(pathToPDF);

            if (!resource.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File non trovato".getBytes());
            }

            byte[] pdfBytes;
            try {
                pdfBytes = Files.readAllBytes(Path.of(resource.getURI()));
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Errore nella lettura del file".getBytes());
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        }

}

