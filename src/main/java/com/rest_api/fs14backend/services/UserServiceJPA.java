package com.rest_api.fs14backend.services;

import com.rest_api.fs14backend.mappers.UserMapper;
import com.rest_api.fs14backend.model.UserDTO;
import com.rest_api.fs14backend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
@Primary
@RequiredArgsConstructor
public class UserServiceJPA implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    @Override
    public List<UserDTO> listUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userToUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<UserDTO> getUserById(UUID id) {

        return Optional.ofNullable(userMapper.userToUserDto(userRepository.findById(id).orElse(null)));
    }

    @Override
    public UserDTO saveNewUser(UserDTO user) {

        return userMapper.userToUserDto(userRepository.save(userMapper.userDtoToUser(user)));
    }

    @Override
    public Optional<UserDTO> updateUserById(UUID userId, UserDTO user) {
        AtomicReference<Optional<UserDTO>> ref = new AtomicReference<>();
        userRepository.findById(userId).ifPresentOrElse(foundUser -> {
            foundUser.setName(user.getName());
            foundUser.setEmail(user.getEmail());
            foundUser.setPassword(user.getPassword());
            ref.set(Optional.of(userMapper.userToUserDto(userRepository.save(foundUser))));
        },()->{
            ref.set(Optional.empty());
        });
        return ref.get();
    }

    @Override
    public Boolean deleteById(UUID userId) {
        if(userRepository.existsById(userId)){
            userRepository.deleteById(userId);
            return true;
        }
        return false;
    }

    @Override
    public void patchUserById(UUID userId, UserDTO user) {

    }
}
