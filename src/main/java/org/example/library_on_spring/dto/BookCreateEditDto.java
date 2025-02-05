package org.example.library_on_spring.dto;

import lombok.Value;
import lombok.experimental.FieldNameConstants;

@Value
@FieldNameConstants
public class BookCreateEditDto {

    String title;

    String author;

    String category;

    Long categoryOrder;
}
