package org.example.library_on_spring.service;

import org.example.library_on_spring.database.entity.Book;
import org.example.library_on_spring.database.entity.User;
import org.example.library_on_spring.database.repository.UserRepository;
import org.example.library_on_spring.dto.UserCreateEditDto;
import org.example.library_on_spring.dto.UserReadDto;
import org.example.library_on_spring.mapper.UserCreateEditMapper;
import org.example.library_on_spring.mapper.UserReadMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserReadMapper userReadMapper;
    private final UserCreateEditMapper userCreateEditMapper;


    public UserService(UserRepository userRepository, UserReadMapper userReadMapper, UserCreateEditMapper userCreateEditMapper) {
        this.userRepository = userRepository;
        this.userReadMapper = userReadMapper;
        this.userCreateEditMapper = userCreateEditMapper;

    }

    public List<UserReadDto> findAll() {
        return userRepository.findAll().stream()
                .map(userReadMapper::map)
                .toList();
    }

    public UserReadDto findById(Long id) {
        return userRepository.findById(id)
                .map(userReadMapper::map)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден " + id));
    }

//    public User getUserById(Long userId) {
//        return userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
//    }

    @Transactional
    public UserReadDto create(UserCreateEditDto userDto) {
        return Optional.of(userDto)
                .map(userCreateEditMapper::map)
                .map(userRepository::save)
                .map(userReadMapper::map)
                .orElseThrow();
    }

    public UserReadDto update(Long id, UserCreateEditDto userDto) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        existingUser.setFirstname(userDto.getFirstname());
        existingUser.setLastname(userDto.getLastname());
        existingUser.setSurname(userDto.getSurname());
      if (userDto.getBookId() != null) {
            Book book = new Book();
            book.setId(userDto.getBookId());
            existingUser.getBook().add(book);
        }
        return userReadMapper.map(userRepository.save(existingUser));
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> findByFullName(String firstname, String lastname, String surname) {
        return userRepository.findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrSurnameContainingIgnoreCase(
                firstname, lastname, surname
        );
    }

    public List<User> searchUsers(String query) {
        return userRepository.findByFirstnameContainingIgnoreCaseOrLastnameContainingIgnoreCaseOrSurnameContainingIgnoreCase(
                query, query, query
        );
    }

}