package org.example.library_on_spring.dto;

import lombok.Value;


@Value
public class UserShortReadDto{
        private Long id;
        private String firstname;
        private String lastname;
        private String surname;
}
