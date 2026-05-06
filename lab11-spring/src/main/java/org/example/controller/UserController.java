package org.example.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.UserMapper;
import org.example.model.dto.UserDto;
import org.example.model.entity.User;
import org.example.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/add")
    public UserDto createUser(@RequestBody @Valid UserDto request) {
        log.info("поступил http-запрос на создание юзера");
        User response = userService.createUser(request);
        return userMapper.toDto(response);
    }

    @GetMapping("/all")
    public List<UserDto> getAllUsers() {
        log.debug("поступил http-запрос на получение всех юзеров");
        return userService.getAllUsers().stream()
                .map(userMapper::toDto)
                .toList();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable Long id) {
        log.debug("поступил http-запрос на получение юзера по id: {}", id);
        User response = userService.getUserById(id);
        return userMapper.toDto(response);
    }

    @PutMapping("/{id}")
    public UserDto updateUser(@PathVariable Long id, @RequestBody @Valid UserDto request) {
        log.info("поступил http-запрос на обновление юзера по id: {}", id);
        User response = userService.updateUser(id, request);
        return userMapper.toDto(response);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        log.info("поступил http-запрос на удаление юзера по id: {}", id);
        userService.deleteUser(id);
        return String.format("пользователь %s удален", id);
    }
}