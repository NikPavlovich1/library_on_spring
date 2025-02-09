package org.example.library_on_spring.dto;

import lombok.Value;

@Value
public class BookUpdateDto {
    private String title;
    private String author;
    private String category;
    private Integer categoryOrder;
}
