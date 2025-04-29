package tn.itbs.project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.itbs.project.entity.Salle;
import tn.itbs.project.repository.SalleRepository;

import java.util.List;

@Service
public class SalleService {
    
    private final SalleRepository salleRepository;

    @Autowired 
    public SalleService(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    public Salle addSalle(Salle salle) {
        if (salleRepository.findByNom(salle.getNom()) != null) {
            throw new RuntimeException("Une salle avec ce nom existe déjà");
        }
        return salleRepository.save(salle);
    }

    public List<Salle> getAvailableSalles() {
        return salleRepository.findByDisponible(true);
    }
    
    
    public Salle updateSalle(int id, Salle salleDetails) {
        Salle salle = salleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Salle non trouvée"));
        
        if (salleDetails.getNom() != null) {
            salle.setNom(salleDetails.getNom());
        }
        if (salleDetails.getCapacite() != 0) {
            salle.setCapacite(salleDetails.getCapacite());
        }
        
        salle.setDisponible(salleDetails.isDisponible());
        
        return salleRepository.save(salle);
    }
    
    
    public boolean deleteSalle(int id) {
	    if (salleRepository.existsById(id)) { 
	        salleRepository.deleteById(id);
	        return true; 
	    }
	    return false; 
	}
    
}