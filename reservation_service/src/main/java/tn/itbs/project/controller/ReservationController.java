package tn.itbs.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import tn.itbs.project.entity.Reservation;
import tn.itbs.project.service.ReservationService;

@RestController
@RequestMapping("/reservations") // Base URL pour toutes les routes
public class ReservationController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    //  L'employé fait une demande
    @PostMapping("/demande")
    public ResponseEntity<Reservation> creerDemande(@RequestParam int userId, @RequestParam int salleId) {
        return ResponseEntity.ok(reservationService.demandeReservation(userId, salleId));
    }

    //  L’admin confirme
    @PostMapping("/{id}/confirmer")
    public ResponseEntity<Reservation> confirmerReservation(@PathVariable int id) {
        return ResponseEntity.ok(reservationService.confirmerReservation(id));
    }

    //  L’admin rejette
    @PostMapping("/{id}/rejeter")
    public ResponseEntity<Reservation> rejeterReservation(@PathVariable int id) {
        return ResponseEntity.ok(reservationService.rejeterReservation(id));
    }
}
