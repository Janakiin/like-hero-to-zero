package Hero.likeherotozero.repository;

import Hero.likeherotozero.model.Country;
import Hero.likeherotozero.model.Emission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CountriesRepository extends JpaRepository<Country, Long> {

    // alle Länder
    @Query("SELECT c FROM Country c ORDER BY c.name")
    List<Country> fetchCountries();

    @Query("SELECT c FROM Country c WHERE c.isoCode = :isoCode")
    Optional<Country> findByIsoCode(@Param("isoCode") String isoCode);
    public interface CountryRepository extends JpaRepository<Country, Long> {

        Optional<Country> findByIsoCode(String isoCode);
    }

}
