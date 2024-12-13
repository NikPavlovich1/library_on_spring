package org.example.library_on_spring.database.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "books")
@Builder
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 100)
    private String author;

    @Column(nullable = false, length = 10)
    private String category;

    @Column(nullable = false)
    private Long categoryOrder;

    @Column
    private String compositeKey;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @Column(nullable = true)
    private User user;

    @PrePersist
    public void compositeKey() {
        this.compositeKey = this.category + "-" + this.categoryOrder;
    }
}
