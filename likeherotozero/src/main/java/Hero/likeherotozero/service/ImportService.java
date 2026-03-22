package Hero.likeherotozero.service;

import Hero.likeherotozero.model.Country;
import Hero.likeherotozero.model.Emission;
import Hero.likeherotozero.repository.CountriesRepository;
import Hero.likeherotozero.repository.EmissionsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class ImportService {

    private final EmissionsRepository emissionsRepository;
    private final CountriesRepository countriesRepository;

    public ImportService(EmissionsRepository emissionsRepository,
                         CountriesRepository countriesRepository) {
        this.emissionsRepository = emissionsRepository;
        this.countriesRepository = countriesRepository;
    }

    public void importData(InputStream inputStream) throws IOException {

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {

            reader.readLine(); // 👈 1. Zeile: Header überspringen

            //String line = reader.readLine();
            String line;
            while ((line = reader.readLine()) != null) {

                String[] parts = line.split(",", 4);
                if (parts.length < 4) continue;

                String countryName = parts[0].trim();
                String iso = parts[1].trim().toUpperCase();
                int year = Integer.parseInt(parts[2].trim());
                double value = Double.parseDouble(parts[3].trim().replace(",", "."));

                Country country = countriesRepository
                        .findByIsoCode(iso)
                        .orElseGet(() -> {
                            Country c = new Country();
                            c.setName(countryName);
                            c.setIsoCode(iso);
                            return countriesRepository.save(c);
                        });

                if (!emissionsRepository.existsByCountryAndYear(country, year)) {
                    Emission emission = new Emission();
                    emission.setCountry(country);
                    emission.setYear(year);
                    emission.setValue(value);

                    emissionsRepository.save(emission);
                }
            }
        }
    }
}
