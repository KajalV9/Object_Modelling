package com.crio.jukebox.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.crio.jukebox.entities.User;
import com.crio.jukebox.repositories.IUserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;


@DisplayName("UserServiceTest")
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private IUserRepository userRepositoryMock;

    @InjectMocks
    private UserService userService;


    @Test
    @DisplayName("create method should create User")
    public void create_ShouldReturnUser(){
        User expectedUser = new User("1", "User1");
        when(userRepositoryMock.save(any(User.class))).thenReturn(expectedUser);

        //Act
        User actualUser = userService.create("User1");

        //Assert
        Assertions.assertEquals(expectedUser,actualUser);
        verify(userRepositoryMock,times(1)).save(any(User.class));
    }

   
}
