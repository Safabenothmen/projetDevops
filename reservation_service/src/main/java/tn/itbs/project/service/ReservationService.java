package tn.itbs.project.service;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tn.itbs.project.entity.Reservation;
import tn.itbs.project.entity.Reservation.ReservationStatus;
import tn.itbs.project.entity.ReservationEvent;
import tn.itbs.project.repository.ReservationRepository;

@Service
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final KafkaTemplate<String, ReservationEvent> kafkaTemplate;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, KafkaTemplate<String, ReservationEvent> kafkaTemplate) {
        this.reservationRepository = reservationRepository;
        this.kafkaTemplate = kafkaTemplate;
    }

    //  L'employé fait une demande de réservation
    public Reservation demandeReservation(int userId, int salleId) {
        Reservation reservation = new Reservation();
        reservation.setUserId(userId);
        reservation.setSalleId(salleId);
        reservation.setStatus(ReservationStatus.Attente);
        reservation.setDateReservation(LocalDateTime.now());

        // Sauvegarde en base
        reservation = reservationRepository.save(reservation);

        // 🔵 Envoyer un message Kafka
        kafkaTemplate.send("reservation-events", new ReservationEvent(
            reservation.getId(),
            reservation.getUserId(),
            reservation.getSalleId(),
            reservation.getStatus()
        ));

        return reservation;
    }

    //  L’admin confirme la réservation
    public Reservation confirmerReservation(int id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));

        reservation.setStatus(ReservationStatus.Confirme);
        reservation = reservationRepository.save(reservation);

        //  Envoyer un message Kafka
        kafkaTemplate.send("reservation-events", new ReservationEvent(
            reservation.getId(),
            reservation.getUserId(),
            reservation.getSalleId(),
            reservation.getStatus()
        ));

        return reservation;
    }

    //  L’admin rejette la réservation
    public Reservation rejeterReservation(int id) {
        Reservation reservation = reservationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réservation non trouvée"));

        reservation.setStatus(ReservationStatus.Rejete);
        reservation = reservationRepository.save(reservation);

        // 🔵 Envoyer un message Kafka
        kafkaTemplate.send("reservation-events", new ReservationEvent(
            reservation.getId(),
            reservation.getUserId(),
            reservation.getSalleId(),
            reservation.getStatus()
        ));

        return reservation;
    }
}
