package org.example.library_on_spring.database.repository;

import org.example.library_on_spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    List<User> findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrSurnameContainingIgnoreCase(
            String firstname, String lastname, String surname
    );



}
