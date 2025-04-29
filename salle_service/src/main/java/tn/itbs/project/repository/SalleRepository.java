package tn.itbs.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.itbs.project.entity.Salle;
@Repository
public interface SalleRepository extends JpaRepository<Salle, Integer>{
	List<Salle> findByDisponible(boolean disponible);
    Salle findByNom(String nom);
	

}
