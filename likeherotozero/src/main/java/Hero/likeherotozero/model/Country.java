package Hero.likeherotozero.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "country", schema = "dbo")
public class Country {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(name = "iso_code")
    private String isoCode;

    public Long getId() { return id; }
    public String getName() { return name; }
    public String getIsoCode() { return isoCode; }

    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setIsoCode(String isoCode) { this.isoCode = isoCode; }
}