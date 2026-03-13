package Hero.likeherotozero.service;

import Hero.likeherotozero.model.Country;
import Hero.likeherotozero.model.Emission;
import Hero.likeherotozero.repository.EmissionsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmissionService {

    private final EmissionsRepository emissionsRepository;

    public EmissionService(EmissionsRepository emissionsRepository) {
        this.emissionsRepository = emissionsRepository;
    }

    public List<Country> getAllCountries() {
        List<Country> countries = emissionsRepository.fetchCountries();
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
        return emissionsRepository.fetchCountries().stream()
                .filter(c -> c.getId().equals(countryId))
                .findFirst()
                .orElse(null);
    }
}
