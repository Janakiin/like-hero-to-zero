// src/main/java/hero/likeherotozero/controller/EmissionController.java
package Hero.likeherotozero.controller;

import Hero.likeherotozero.entity.Country;
import Hero.likeherotozero.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class EmissionController {

    @Autowired
    private CountryRepository countryRepo;

    @GetMapping("/")
    public String home(Model model) {
        List<Country> countries = countryRepo.findAll();
        System.out.println(countries.size());
        model.addAttribute("countries", countries);
        return "index"; // index.html muss unter templates/
    }
}