package com.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.main.client.NotificationClient;
import com.main.dto.EmailRequest;
import com.main.dto.UserProfileUpdateRequest;
import com.main.dto.UserRegistrationRequest;
import com.main.entity.User;
import com.main.exception.UserNotFoundException;
import com.main.repository.UserRepository;
import com.main.service.JwtService;
import com.main.service.UserService;
import com.main.service.UserServiceImpl;

@SpringBootTest
//@ExtendWith(MockitoExtension.class)
class UserServiceApplicationTests {

	@Mock
    private JwtService jwtService;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Mock
    private UserRepository userRepo;

    @Mock
    private NotificationClient notificationClient;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testAddNewUsers_Success() {
        // Arrange
        UserRegistrationRequest request = new UserRegistrationRequest("testUser", "Password123!", "test@example.com", "user");
        User user = new User();
        user.setUserName(request.getUserName());
        user.setPassword("encodedPassword");
        user.setEmail(request.getEmail());
        user.setRole(request.getRole());

        when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");
        when(userRepo.save(any(User.class))).thenReturn(user);

        // Act
        User savedUser = userService.addNewUsers(request);

        // Assert
        verify(userRepo, times(1)).save(any(User.class));
        verify(notificationClient, times(1)).sendEmail(any(EmailRequest.class));
        assertEquals("testUser", savedUser.getUserName());
        assertEquals("encodedPassword", savedUser.getPassword());
        assertEquals("test@example.com", savedUser.getEmail());
        assertEquals("user", savedUser.getRole());
    }
}
