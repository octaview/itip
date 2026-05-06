package org.example.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.model.dto.UserDto;
import org.example.model.entity.User;
import org.example.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public User createUser(UserDto request) {
        log.info("начинаем создание нового юзера: {}", request.getName());
        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setDeviceToken(request.getDeviceToken());
        user.setTelegramChatId(request.getTelegramChatId());
        user.setCreatedAt(LocalDateTime.now());
        
        User savedUser = userRepository.save(user);
        log.info("юзер успешно создан, id: {}", savedUser.getId());
        return savedUser;
    }

    public List<User> getAllUsers() {
        log.debug("достаем всех юзеров из бд");
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        log.debug("ищем юзера с id: {}", id);
        return userRepository.findById(id).orElseThrow(() -> {
            log.warn("юзер с id {} не найден", id);
            return new RuntimeException("пользователь не найден");
        });
    }

    @Transactional
    public User updateUser(Long id, UserDto request) {
        log.info("начинаем обновление юзера с id: {}", id);
        User user = getUserById(id);
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setDeviceToken(request.getDeviceToken());
        user.setTelegramChatId(request.getTelegramChatId());
        
        return userRepository.save(user);
    }

    @Transactional
    public void deleteUser(Long id) {
        log.info("удаляем юзера с id: {}", id);
        User user = getUserById(id);
        userRepository.delete(user);
    }
}