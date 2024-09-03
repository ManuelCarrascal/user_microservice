package emazon.user.ports.application.http.controller;

import emazon.user.domain.api.IRoleServicePort;
import emazon.user.domain.api.IUserServicePort;
import emazon.user.domain.model.User;
import emazon.user.domain.util.RoleConstants;
import emazon.user.ports.application.http.dto.UserRequest;
import emazon.user.ports.application.http.dto.UserResponse;
import emazon.user.ports.application.http.mapper.IUserRequestMapper;
import emazon.user.ports.application.http.mapper.IUserResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")

@RequiredArgsConstructor

public class UserRestController {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;
    private final IRoleServicePort roleServicePort;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserResponse> saveWarehouseAsstUser(@RequestBody UserRequest userRequest) {
        User user = userRequestMapper.userRequestToUser(userRequest);
        user.setRoleId(roleServicePort.getRoleId(RoleConstants.AUX_BODEGA.name()));
        userServicePort.saveWarehouseAsstUser(user);
        UserResponse userResponse = userResponseMapper.userToUserResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}
