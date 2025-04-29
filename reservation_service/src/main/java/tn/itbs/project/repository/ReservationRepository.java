package tn.itbs.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.itbs.project.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {


}
