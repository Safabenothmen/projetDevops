package tn.itbs.project.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReservationEvent {
    private int reservationId;
    private int userId;
    private int salleId;
    private ReservationStatus status;

    public enum ReservationStatus {
        Attente,
        Confirme,
        Rejete
    }
}
