package tn.itbs.project.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import tn.itbs.project.entity.ReservationEvent;
import tn.itbs.project.repository.SalleRepository;
import tn.itbs.project.entity.Salle;

@Service
public class ReservationEventListener {

    @Autowired
    private SalleRepository salleRepository;

    @KafkaListener(topics = "reservation-events", groupId = "salle-service-group")
    public void handleReservationEvent(ReservationEvent event) {
        System.out.println("🎧 SalleService a reçu : " + event);

        if (event.getStatus() == ReservationEvent.ReservationStatus.Confirme) {
            salleRepository.findById(event.getSalleId()).ifPresent(salle -> {
                salle.setDisponible(false);  // salle occupée
                salleRepository.save(salle);
                System.out.println("🟡 Salle " + salle.getNom() + " maintenant indisponible.");
            });
        } else if (event.getStatus() == ReservationEvent.ReservationStatus.Rejete) {
            salleRepository.findById(event.getSalleId()).ifPresent(salle -> {
                salle.setDisponible(true);  // libérée si rejetée
                salleRepository.save(salle);
                System.out.println("🟢 Salle " + salle.getNom() + " remise disponible.");
            });
        }
    }
}
