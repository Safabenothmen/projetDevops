package tn.itbs.project.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReservationEvent {
    private int reservationId;
    private int userId;
    private int salleId;
    private Reservation.ReservationStatus status;
}
