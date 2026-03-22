package Hero.likeherotozero.controller;

import Hero.likeherotozero.service.EmissionService;
import Hero.likeherotozero.service.ImportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.io.InputStream;

@Controller
public class ImportController {
    private final ImportService importService;
    public ImportController(ImportService importService) {
        this.importService = importService;
    }
    @GetMapping("/import")
    public String importData() throws IOException {
        InputStream is = getClass()
                .getClassLoader()
                .getResourceAsStream("data.csv");


        this.importService.importData(is);
        return "redirect:/";
    }
}
