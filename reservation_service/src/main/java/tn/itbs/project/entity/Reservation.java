package tn.itbs.project.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Reservation {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)

private int id;
private int userId;
private int salleId;

private LocalDateTime dateReservation;

@Enumerated(EnumType.STRING)  // Sauvegarde l'ENUM sous forme de texte dans la BD
private ReservationStatus status; // EN_ATTENTE, CONFIRMEE, REJETEE

public enum ReservationStatus {
    Attente,
    Confirme,
    Rejete
}


}
