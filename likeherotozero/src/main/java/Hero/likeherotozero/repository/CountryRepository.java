// src/main/java/hero/likeherotozero/repository/CountryRepository.java
package Hero.likeherotozero.repository;

import Hero.likeherotozero.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    // findAll(), findById(), save() etc. schon enthalten
}