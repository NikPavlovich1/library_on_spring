package org.example.library_on_spring.database.repository;

import org.example.library_on_spring.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u " +
            "from User u")
    public List<User> findAll();

    @Query("select u " +
            "from User u " +
            "where u.id = :id")
    public Optional<User> findById(Long id);

    @Query("insert into User (firstname, lastname, createdAt) " +
            "values (:firstname, :lastname, :createdAt)")
    public User save(User user);

    @Query("update User u " +
            "set u.firstname = :firstname, u.lastname = :lastname " +
            "where u.id = :id")
    public void update(User user);

    @Query("delete from User u " +
            "where u.id = :id")
    public void remove(User user);
}
