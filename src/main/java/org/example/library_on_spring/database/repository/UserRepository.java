package org.example.library_on_spring.database.repository;

import org.example.library_on_spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrSurnameContainingIgnoreCase(
            String firstname, String lastname, String surname
    );

    boolean existsByFirstnameAndLastname(String firstname, String lastname);

    Optional<User> findByUsername(String username);
}
