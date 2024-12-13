package org.example.library_on_spring.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    private String surname;

    @Column(nullable = false)
    private LocalDate createdAt;

    @OneToMany(mappedBy = "user")
    private List<Book> books = new ArrayList<>();

    @PrePersist
    public void setCreatedAt() {
        this.createdAt = LocalDate.now();
    }
}
