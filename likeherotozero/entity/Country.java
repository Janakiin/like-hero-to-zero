package hero.likeherotozero.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String isoCode;
}