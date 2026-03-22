package Hero.likeherotozero.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "emission", schema = "dbo", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"country_id", "year"})
})
public class Emission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @NotNull(message = "Land ist erforderlich")
    private Hero.likeherotozero.model.Country country;

    @NotNull(message = "Jahr ist erforderlich")
    @Min(value = 1750, message = "Jahr darf nicht kleiner als 1750 sein")
    @Max(value = 2025, message = "Jahr darf nicht in der Zukunft liegen")
    private Integer year;

    @NotNull(message = "Emissionswert ist erforderlich")
    @DecimalMin(value = "0.0", inclusive = true, message = "Wert muss positiv sein")
    private Double value;

    public Emission() {}

    public Long getId() { return id; }
    public Integer getYear() { return year; }
    public Double getValue() { return value; }
    public Country getCountry() { return country; }

    public void setId(Long id) { this.id = id; }
    public void setYear(Integer year) { this.year = year; }
    public void setValue(Double value) { this.value = value; }
    public void setCountry(Country country) { this.country = country; }
}