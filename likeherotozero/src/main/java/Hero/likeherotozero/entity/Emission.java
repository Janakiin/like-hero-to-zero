package Hero.likeherotozero.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
public class Emission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Country country;

    private Integer year;

    private Double co2Kt;

    private String source;
}