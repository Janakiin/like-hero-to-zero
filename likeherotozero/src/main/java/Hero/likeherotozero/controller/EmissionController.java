package Hero.likeherotozero.controller;

import Hero.likeherotozero.model.Emission;
import Hero.likeherotozero.model.Country;
import Hero.likeherotozero.repository.EmissionsRepository;
import Hero.likeherotozero.service.EmissionService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
@Getter
@Setter
public class EmissionController {

    private final EmissionService emissionService;

    public EmissionController(EmissionService emissionService) {
        this.emissionService = emissionService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("countries", emissionService.getAllCountries());
        return "index";
    }

    @GetMapping("/emissions")
    public String fetchEmissions(@RequestParam Long countryId, Model model) {

        String currentCountry = emissionService.getCountryNameById(countryId);
        Page<Emission> emissionsProCountry = emissionService.getEmissionsByCountryId(countryId);

        model.addAttribute("currentCountry", currentCountry);
        model.addAttribute("emissionsPage", emissionsProCountry);

        return "emissionsOverview";
    }

    @GetMapping("/emissions/add")
    public String showAddEmissionForm(Model model) {
        model.addAttribute("countries", emissionService.getAllCountries());
        model.addAttribute("emission", new Emission()); // leeres Objekt für Formular
        return "addEmission";
    }

    @PostMapping("/emissions/add")
    public String addEmission(@ModelAttribute Emission emission, Model model) {

        // Country aus DB holen
        Long countryId = emission.getCountry() != null ? emission.getCountry().getId() : null;
        if (countryId != null) {
            Country country = emissionService.getCountryById(countryId); // neue Service-Methode
            emission.setCountry(country);
        }

        emissionService.saveEmission(emission);

        if (countryId != null) {
            return "redirect:/emissions?countryId=" + countryId;
        }
        return "redirect:/";
    }
}