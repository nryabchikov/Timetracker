package by.krainet.timetracker.repository;

import java.util.Optional;

import by.krainet.timetracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}