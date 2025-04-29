package tn.itbs.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import tn.itbs.project.entity.User;
import tn.itbs.project.repository.UserRepository;
@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;
	public User registerUser(String nom,String prenom,String mdp,String email,User.UserRole role) {
		User user=new User();
		user.setNom(nom);
		user.setPrenom(prenom);
		user.setMdp(mdp);
		user.setEmail(email);
		user.setRole(role);
		return userRepository.save(user);
		
	
	}
	public User loginUser(String email,String mdp) {
		 User user = userRepository.findByEmail(email);
	        if(user != null ) {
	            return user;
	        }
	        return null;
	    }

	public List<User> getAllUsers() {
        return userRepository.findAll();
    }
	public boolean deleteUser(int id) {
	    if (userRepository.existsById(id)) { 
	        userRepository.deleteById(id);
	        return true; 
	    }
	    return false; 
	}

	 
}



