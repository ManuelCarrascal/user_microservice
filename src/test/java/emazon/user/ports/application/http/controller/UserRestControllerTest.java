package emazon.user.ports.application.http.controller;

import emazon.user.domain.api.IUserServicePort;
import emazon.user.domain.model.User;
import emazon.user.ports.application.http.dto.UserRequest;
import emazon.user.ports.application.http.dto.UserResponse;
import emazon.user.ports.application.http.mapper.IUserRequestMapper;
import emazon.user.ports.application.http.mapper.IUserResponseMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UserRestControllerTest {
    @Mock
    private IUserServicePort userServicePort;

    @Mock
    private IUserRequestMapper userRequestMapper;

    @Mock
    private IUserResponseMapper userResponseMapper;

    @InjectMocks
    private UserRestController userRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveWarehouseAsstUser_shouldReturnCreatedUserResponse() {
        UserRequest userRequest = new UserRequest();
        User user = new User();
        UserResponse expectedResponse = new UserResponse();

        when(userRequestMapper.userRequestToUser(userRequest)).thenReturn(user);
        when(userResponseMapper.userToUserResponse(user)).thenReturn(expectedResponse);

        ResponseEntity<UserResponse> response = userRestController.saveWarehouseAsstUser(userRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());

        verify(userRequestMapper).userRequestToUser(userRequest);
        verify(userServicePort).saveWarehouseAsstUser(user);
        verify(userResponseMapper).userToUserResponse(user);
    }

    @Test
    void saveClientUser_shouldReturnCreatedUserResponse() {
        UserRequest userRequest = new UserRequest();
        User user = new User();
        UserResponse expectedResponse = new UserResponse();

        when(userRequestMapper.userRequestToUser(userRequest)).thenReturn(user);
        when(userResponseMapper.userToUserResponse(user)).thenReturn(expectedResponse);

        ResponseEntity<UserResponse> response = userRestController.saveClientUser(userRequest);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(expectedResponse, response.getBody());

        verify(userRequestMapper).userRequestToUser(userRequest);
        verify(userServicePort).saveClientUser(user);
        verify(userResponseMapper).userToUserResponse(user);
    }
}