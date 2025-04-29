package tn.itbs.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import tn.itbs.project.entity.User;
import tn.itbs.project.service.*;
@Controller
public class UserController { 
	@Autowired
	private UserService UserService;
@PostMapping("/register")
	public ResponseEntity<User> registerUser(@RequestParam String nom,
			@RequestParam String prenom,
			@RequestParam String mdp,
			@RequestParam String email,
			@RequestParam String role)
{
    User.UserRole userRole;
    try {
        userRole = User.UserRole.valueOf(role);  // Convertir la chaîne en énumération
    } catch (IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(null); // Role invalide, renvoie une erreur 400
    }

	User newuser=UserService.registerUser(nom, prenom, mdp, email, userRole);
	return ResponseEntity.ok(newuser);
	
}

@PostMapping("/login")
public ResponseEntity<User> login(@RequestParam String email, 
        @RequestParam String mdp) {
User user = UserService.loginUser(email, mdp);
if(user != null) {
return ResponseEntity.ok(user);
}
return ResponseEntity.status(401).build();
}

@GetMapping("/all")
public ResponseEntity<List<User>> getAllUsers() {
    List<User> users = UserService.getAllUsers();
    return ResponseEntity.ok(users);
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteUser(@PathVariable int id) {
    if (UserService.deleteUser(id)) {
        return ResponseEntity.ok("User deleted successfully.");
    }
    return ResponseEntity.status(404).body("User not found.");
}

}
