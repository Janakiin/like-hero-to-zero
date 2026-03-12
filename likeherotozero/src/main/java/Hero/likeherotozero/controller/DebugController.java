package Hero.likeherotozero.controller;

import Hero.likeherotozero.entity.Country;
import Hero.likeherotozero.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DebugController {

    @Autowired
    private CountryRepository countryRepo;

    @GetMapping("/api/countries")
    public List<Country> getCountries() {
        return countryRepo.findAll();
    }
}