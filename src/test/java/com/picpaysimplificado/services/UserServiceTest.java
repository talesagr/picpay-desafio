package com.picpaysimplificado.services;

import com.picpaysimplificado.DTO.UserDTO;
import com.picpaysimplificado.DTO.ValidateTransactionDTO;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.exceptions.CustomException;
import com.picpaysimplificado.exceptions.user.UserNotFoundException;
import com.picpaysimplificado.middleware.ValidateTransactionAllGood;
import com.picpaysimplificado.middleware.ValidateTransactionCheckBalanceAboveZero;
import com.picpaysimplificado.middleware.ValidateTransactionCheckUserType;
import com.picpaysimplificado.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindUserByID_Success() throws Exception {
        Long userID = 1L;
        User expectedUser = new User();
        when(userRepository.findUserById(userID)).thenReturn(Optional.of(expectedUser));

        User foundUser = userService.findUserById(userID);

        assertNotNull(foundUser);
        assertEquals(expectedUser,foundUser);
    }

    @Test
    void testFindUserByID_UserNotFound() {
        Long userID = 1L;
        when(userRepository.findUserById(userID)).thenReturn(Optional.empty());

        assertThrows(UserNotFoundException.class, ()-> userService.findUserById(userID));
    }

    @Test
    void testCreateUser(){
        UserDTO userDTO = new UserDTO(
                "test",
                "testFinalName",
                "123456",
                new BigDecimal("1000"),
                "email@teste.com",
                "12345",
                UserType.COMMON);
        User newUser = new User();
        when(userRepository.save(newUser)).thenReturn(newUser);

        User createdUser = userService.createUser(userDTO);

        assertNotNull(createdUser);
        assertEquals(newUser, createdUser);
        assertEquals(createdUser.getUserType(), UserType.COMMON);
        assertEquals(createdUser.getBalance(), new BigDecimal("1000"));
    }

    @Test
    void testGetAllUsers(){
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        when(userRepository.findAll()).thenReturn(users);

        List<User> allUsers = userService.getAllUsers();

        assertEquals(users.size(), allUsers.size());
        assertEquals(users, allUsers);
    }

}
