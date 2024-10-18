package com.main;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.main.dto.UserProfile;
import com.main.dto.UserProfileUpdateRequest;
import com.main.dto.UserRegistrationRequest;
import com.main.entity.User;
import com.main.exception.UserNotFoundException;
import com.main.repository.UserRepository;
import com.main.service.UserServiceImpl;

@SpringBootTest
class UserServiceApplicationTests {

	@Test
	void contextLoads() {
	}

	@Mock
    private UserRepository userRepository;  // Mock the UserRepository

    @InjectMocks
    private UserServiceImpl userService;  // Inject the mock into UserServiceImpl

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialize mocks
    }

    // Positive test case
    @Test
    public void testAddNewUsers_Success() {
        // Arrange
        UserRegistrationRequest request = new UserRegistrationRequest("testUser", "password", "test@test.com", "USER");
        User user = new User();
        user.setUserId(1);
        user.setUserName("testUser");
        user.setEmail("test@test.com");
        user.setRole("USER");

        when(userRepository.save(any(User.class))).thenReturn(user);  // Mock repository save() method

        // Act
        UserProfile result = userService.addNewUsers(request);

        // Assert
        assertEquals(1, result.getUserId());
        assertEquals("testUser", result.getUserName());
        assertEquals("test@test.com", result.getEmail());
        assertEquals("USER", result.getRole());
    }
    
    // Negative Test Case
    @Test
    public void testAddNewUsers_Failure() {
        // Arrange
        UserRegistrationRequest request = new UserRegistrationRequest("testUser", "password", "test@test.com", "USER");

        when(userRepository.save(any(User.class))).thenThrow(new RuntimeException("Failed to save user"));  // Mock an exception

        try {
            // Act
            userService.addNewUsers(request);
        } catch (RuntimeException ex) {
            // Assert
            assertEquals("Failed to save user", ex.getMessage());
        }
    }
    
 // Positive test case
    @Test
    public void testGetAllUsers_Success() {
        // Arrange
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setUserId(1);
        user1.setUserName("testUser1");
        user1.setEmail("test1@test.com");
        user1.setRole("USER");

        User user2 = new User();
        user2.setUserId(2);
        user2.setUserName("testUser2");
        user2.setEmail("test2@test.com");
        user2.setRole("ADMIN");

        userList.add(user1);
        userList.add(user2);

        // Mock the repository findAll() method to return the user list
        when(userRepository.findAll()).thenReturn(userList);

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertEquals(2, result.size());  // Check if 2 users are returned
        assertEquals("testUser1", result.get(0).getUserName());  // Check the first user details
        assertEquals("testUser2", result.get(1).getUserName());  // Check the second user details
    }
    
 // Negative test case: No users found (empty list)
    @Test
    public void testGetAllUsers_NoUsersFound() {
        // Arrange
        when(userRepository.findAll()).thenReturn(new ArrayList<>());  // Mock an empty list return

        // Act
        List<User> result = userService.getAllUsers();

        // Assert
        assertTrue(result.isEmpty());  // Check if the result is empty
    }

    // Negative test case: Exception thrown while fetching users
    @Test
    public void testGetAllUsers_ExceptionThrown() {
        // Arrange
        when(userRepository.findAll()).thenThrow(new RuntimeException("Database Error"));  // Mock an exception

        try {
            // Act
            userService.getAllUsers();
        } catch (RuntimeException ex) {
            // Assert
            assertEquals("Database Error", ex.getMessage());
        }
    }
    
    // positive test case to check if user is present for updating
    @Test
    public void testUpdateUserProfile_UserFoundAndUpdatedSuccessfully() {
        // Arrange
        int userId = 1;
        User existingUser = new User();
        existingUser.setUserId(userId);
        existingUser.setUserName("OldUserName");
        existingUser.setEmail("oldemail@example.com");
        existingUser.setRole("USER");
        
        UserProfileUpdateRequest updateRequest = new UserProfileUpdateRequest();
        updateRequest.setUserName("NewUserName");
        updateRequest.setEmail("newemail@example.com");
        updateRequest.setRole("ADMIN");

        User updatedUser = new User();
        updatedUser.setUserId(userId);
        updatedUser.setUserName("NewUserName");
        updatedUser.setEmail("newemail@example.com");
        updatedUser.setRole("ADMIN");

        when(userRepository.findById(userId)).thenReturn(Optional.of(existingUser)); // Mock finding the user
        when(userRepository.save(existingUser)).thenReturn(updatedUser); // Mock saving the updated user

        // Act
        UserProfile result = userService.updateUserProfile(userId, updateRequest);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals("NewUserName", result.getUserName());
        assertEquals("newemail@example.com", result.getEmail());
        assertEquals("ADMIN", result.getRole());

        // Verify interactions
        verify(userRepository, times(1)).findById(userId);  // Make sure findById was called
        verify(userRepository, times(1)).save(existingUser); // Make sure save was called
    }
    
    // Negative Test Case if user is not present while updating
    @Test
    public void testUpdateUserProfile_UserNotFound() {
        // Arrange
        int userId = 1;
        UserProfileUpdateRequest updateRequest = new UserProfileUpdateRequest();
        updateRequest.setUserName("NewUserName");
        updateRequest.setEmail("newemail@example.com");
        updateRequest.setRole("ADMIN");

        when(userRepository.findById(userId)).thenReturn(Optional.empty()); // Mock user not found

        // Act & Assert
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.updateUserProfile(userId, updateRequest);
        });

        assertEquals("User not found with id: " + userId, exception.getMessage());

        // Verify interactions
        verify(userRepository, times(1)).findById(userId); // Make sure findById was called
        verify(userRepository, never()).save(any(User.class)); // Ensure save is never called
    }
    
    // Positive Test case to check if user single user is present
    @Test
    public void testGetUserById_UserFound() {
        // Arrange
        int userId = 1;
        User userEntity = new User();
        userEntity.setUserId(userId);
        userEntity.setUserName("TestUser");
        userEntity.setEmail("testuser@example.com");
        userEntity.setRole("USER");

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity)); // Mock finding the user

        // Act
        UserProfile result = userService.getUserById(userId);

        // Assert
        assertNotNull(result);
        assertEquals(userId, result.getUserId());
        assertEquals("TestUser", result.getUserName());
        assertEquals("testuser@example.com", result.getEmail());
        assertEquals("USER", result.getRole());

        // Verify interaction
        verify(userRepository, times(1)).findById(userId); // Ensure findById is called
    }
    
    // Negative Test Case to check if user Id is present
    @Test
    public void testGetUserById_UserNotFound() {
        // Arrange
        int userId = 1;

        when(userRepository.findById(userId)).thenReturn(Optional.empty()); // Mock user not found

        // Act & Assert
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.getUserById(userId);
        });

        assertEquals("User not found with id: " + userId, exception.getMessage());

        // Verify interaction
        verify(userRepository, times(1)).findById(userId); // Ensure findById is called
    }
    
    // Positive Test Case to Check if user found and Deleted
    @Test
    public void testDeleteUser_UserFound() {
        // Arrange
        int userId = 1;
        User userEntity = new User();
        userEntity.setUserId(userId);
        userEntity.setUserName("TestUser");

        when(userRepository.findById(userId)).thenReturn(Optional.of(userEntity)); // Mock user found

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).findById(userId); // Verify that findById was called once
        verify(userRepository, times(1)).delete(userEntity); // Verify that delete was called once
    }
    
    // Negative Test Case to check if user not found
    @Test
    public void testDeleteUser_UserNotFound() {
        // Arrange
        int userId = 1;

        when(userRepository.findById(userId)).thenReturn(Optional.empty()); // Mock user not found

        // Act & Assert
        UserNotFoundException exception = assertThrows(UserNotFoundException.class, () -> {
            userService.deleteUser(userId);
        });

        assertEquals("User not found with id: " + userId, exception.getMessage());

        // Verify
        verify(userRepository, times(1)).findById(userId); // Verify that findById was called once
        verify(userRepository, never()).delete(any(User.class)); // Verify that delete was never called
    }
}
