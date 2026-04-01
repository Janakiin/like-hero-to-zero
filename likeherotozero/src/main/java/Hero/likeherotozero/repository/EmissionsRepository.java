package Hero.likeherotozero.repository;

import Hero.likeherotozero.model.Country;
import Hero.likeherotozero.model.Emission;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmissionsRepository extends JpaRepository<Emission, Long> {
    // Emissionen für ein Land
    @Query("SELECT e FROM Emission e WHERE e.country.id = :countryId ORDER BY e.year")
    Page<Emission> fetchEmissionsByCountryId(Long countryId, Pageable pageable);

    default Emission saveEmission(Emission emission) {
        return save(emission);
    }

    @Query("""
    SELECT CASE WHEN COUNT(e) > 0 THEN true ELSE false END
    FROM Emission e
    WHERE e.country.id = :countryId
      AND e.year = :year
    """)
    boolean existsByCountryIdAndYear(@Param("countryId") Long countryId,
                                     @Param("year") int year);
}
