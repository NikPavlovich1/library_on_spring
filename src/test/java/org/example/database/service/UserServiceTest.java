package org.example.database.service;

import org.example.library_on_spring.database.entity.User;
import org.example.library_on_spring.database.repository.UserRepository;
import org.example.library_on_spring.dto.UserCreateEditDto;
import org.example.library_on_spring.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class) // Подключаем Mockito
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void testFindById() {
        User user = new User();
        user.setId(1L);
        user.setFirstname("John");
        user.setLastname("Doe");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        User result = userService.findById(1L);
        assertEquals("John", result.getFirstname());
        assertEquals("Doe", result.getLastname());
    }

//    @Test
//    public void testUpdate() {
//        User existingUser = new User();
//        existingUser.setId(1L);
//        existingUser.setFirstname("John");
//        existingUser.setLastname("Doe");
//        User updatedUser = new User();
//        updatedUser.setFirstname("Jane");
//        updatedUser.setLastname("Smith");
//        when(userRepository.findById(1L)).thenReturn(Optional.of(existingUser));
//        when(userRepository.save(existingUser)).thenReturn(existingUser);
//        User result = userService.update(1L, updatedUser);
//        assertEquals("Jane", result.getFirstname());
//        assertEquals("Smith", result.getLastname());
//    }
}