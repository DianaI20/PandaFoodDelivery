package ro.utcluj.pandafooddelivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.utcluj.pandafooddelivery.model.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}

