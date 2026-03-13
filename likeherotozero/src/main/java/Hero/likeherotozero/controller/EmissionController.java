package Hero.likeherotozero.controller;

import Hero.likeherotozero.model.Emission;
import Hero.likeherotozero.model.Country;
import Hero.likeherotozero.repository.EmissionsRepository;
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

    private final EmissionsRepository emissionsRepository;

    private Page<Emission> emissionsProCountry; // leer zu Beginn

    public EmissionController(EmissionsRepository emissionsRepository) {
        this.emissionsRepository = emissionsRepository;
    }

    @GetMapping("/")
    public String index(Model model) {

        List<Country> countries = emissionsRepository.fetchCountries();

        model.addAttribute("countries", countries);
        model.addAttribute("emissionsProCountry", emissionsProCountry);

        return "index";
    }

    @GetMapping("/emissions")
    public String fetchEmissions(@RequestParam Long countryId, Model model) {

        emissionsProCountry = emissionsRepository.fetchEmissionsByCountryId(countryId,  PageRequest.of(0, 100));

        List<Country> countries = emissionsRepository.fetchCountries();

        model.addAttribute("emissionsPage", emissionsProCountry);

        return "emissionsOverview";
    }
}