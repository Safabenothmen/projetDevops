package tn.itbs.project.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;  

    private String nom;
    private String prenom;
    private String mdp;
    
    @Column(unique = true)
    private String email;  
    @Enumerated(EnumType.STRING)  // Sauvegarde l'ENUM sous forme de texte dans la BD
    private UserRole role;

    // L'énumération pour les rôles d'utilisateur
    public enum UserRole {
        Employe, Admin  
    }
}
