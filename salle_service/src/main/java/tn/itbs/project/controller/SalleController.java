package tn.itbs.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import tn.itbs.project.entity.Salle;
import tn.itbs.project.service.SalleService;

@Controller
public class SalleController {
	@Autowired
	private SalleService SalleService;
	
	 @PostMapping("/ajoutsalle")
	    public ResponseEntity<Salle> addSalle(@RequestBody Salle salle) {
	        return ResponseEntity.ok(SalleService.addSalle(salle));
	    }
	 
	 @GetMapping("/disponibles")
	    public ResponseEntity<List<Salle>> getAvailableSalles() {
	        return ResponseEntity.ok(SalleService.getAvailableSalles());
	    }
	 
	 @PutMapping("/update/{id}")
	    public ResponseEntity<Salle> updateSalle(
	            @PathVariable int id,
	            @RequestBody Salle salleDetails) {
	        return ResponseEntity.ok(SalleService.updateSalle(id, salleDetails));
	    }
	 @DeleteMapping("/deleteSalle/{id}")
	 public ResponseEntity<String> deleteSalle(@PathVariable int id) {
	     if (SalleService.deleteSalle(id)) {
	         return ResponseEntity.ok("Salledeleted successfully.");
	     }
	     return ResponseEntity.status(404).body("salle not found.");
	 }

}
