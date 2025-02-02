package org.example.library_on_spring.service;

import org.example.library_on_spring.database.entity.User;
import org.example.library_on_spring.database.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public User update(Long id, User updateUser){
        User existingUser = findById(id);
        existingUser.setFirstname(updateUser.getFirstname());
        existingUser.setLastname(updateUser.getLastname());
        existingUser.setSurname(updateUser.getSurname());
        return userRepository.save(existingUser);
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}