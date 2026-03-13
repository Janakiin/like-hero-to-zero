package Hero.likeherotozero.repository;

import Hero.likeherotozero.model.Country;
import Hero.likeherotozero.model.Emission;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmissionsRepository extends JpaRepository<Emission, Long> {

    // alle Länder
    @Query("SELECT c FROM Country c ORDER BY c.name")
    List<Country> fetchCountries();

    // Emissionen für ein Land
    @Query("SELECT e FROM Emission e WHERE e.country.id = :countryId ORDER BY e.year")
    Page<Emission> fetchEmissionsByCountryId(Long countryId, Pageable pageable);

    default Emission saveEmission(Emission emission) {
        return save(emission);
    }
}
