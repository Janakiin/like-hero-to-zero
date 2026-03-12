package Hero.likeherotozero.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "app_user") // <- wichtig für SQL Server
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role;
}