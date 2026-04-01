package Hero.likeherotozero.service;

import Hero.likeherotozero.model.Country;
import Hero.likeherotozero.model.Emission;
import Hero.likeherotozero.repository.CountriesRepository;
import Hero.likeherotozero.repository.EmissionsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmissionService {

    private final EmissionsRepository emissionsRepository;
    private final CountriesRepository countriesRepository;

    public EmissionService(EmissionsRepository emissionsRepository, CountriesRepository countriesRepository) {
        this.emissionsRepository = emissionsRepository;
        this.countriesRepository = countriesRepository;
    }

    public List<Country> getAllCountries() {
        List<Country> countries = countriesRepository.fetchCountries();
        return countries != null ? countries : List.of();
    }

    public String getCountryNameById(Long countryId) {
        return getAllCountries().stream()
                .filter(c -> c.getId().equals(countryId))
                .map(Country::getName)
                .findFirst()
                .orElse("Nicht gefunden");
    }

    public Page<Emission> getEmissionsByCountryId(Long countryId) {
        Page<Emission> emissions = emissionsRepository.fetchEmissionsByCountryId(countryId, PageRequest.of(0, 100));
        return emissions != null ? emissions : Page.empty();
    }

    public Emission saveEmission(Emission emission) {
        return emissionsRepository.saveEmission(emission);
    }

    public Country getCountryById(Long countryId) {
        return countriesRepository.fetchCountries().stream()
                .filter(c -> c.getId().equals(countryId))
                .findFirst()
                .orElse(null);
    }

    public boolean existsByCountryAndYear(long countryId, int year) {
        return emissionsRepository.existsByCountryIdAndYear(countryId, year);
    }
}
