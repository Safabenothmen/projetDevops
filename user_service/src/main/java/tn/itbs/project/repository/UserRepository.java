package tn.itbs.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.itbs.project.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findByEmail(String email);
    boolean existsByEmail(String email);

}
