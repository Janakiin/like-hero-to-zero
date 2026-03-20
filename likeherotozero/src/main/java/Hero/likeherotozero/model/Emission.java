package Hero.likeherotozero.model;

import jakarta.persistence.*;

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
    private Hero.likeherotozero.model.Country country;

    private Integer year;
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