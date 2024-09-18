package emazon.user.ports.application.http.controller;

import emazon.user.domain.api.IUserServicePort;
import emazon.user.domain.model.User;
import emazon.user.ports.application.http.dto.UserRequest;
import emazon.user.ports.application.http.dto.UserResponse;
import emazon.user.ports.application.http.mapper.IUserRequestMapper;
import emazon.user.ports.application.http.mapper.IUserResponseMapper;
import emazon.user.ports.application.http.util.openapi.controller.UserRestControllerConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = UserRestControllerConstants.TAG_NAME, description = UserRestControllerConstants.TAG_DESCRIPTION)
public class UserRestController {
    private final IUserServicePort userServicePort;
    private final IUserRequestMapper userRequestMapper;
    private final IUserResponseMapper userResponseMapper;

    @PostMapping("/warehouse-asst")
    @PreAuthorize(UserRestControllerConstants.HAS_ROLE_ADMIN)
    @Operation(summary = UserRestControllerConstants.OPERATION_SUMMARY_SAVE_WAREHOUSE_ASST)
    public ResponseEntity<UserResponse> saveWarehouseAsstUser(@RequestBody UserRequest userRequest) {
        User user = userRequestMapper.userRequestToUser(userRequest);
        userServicePort.saveWarehouseAsstUser(user);
        UserResponse userResponse = userResponseMapper.userToUserResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }

    @PostMapping("/register")
    @Operation(summary = UserRestControllerConstants.OPERATION_SUMMARY_REGISTER_CLIENT, description = UserRestControllerConstants.OPERATION_DESCRIPTION_REGISTER_CLIENT)
    public ResponseEntity<UserResponse> saveClientUser(@RequestBody UserRequest userRequest) {
        User user = userRequestMapper.userRequestToUser(userRequest);
        userServicePort.saveClientUser(user);
        UserResponse userResponse = userResponseMapper.userToUserResponse(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userResponse);
    }
}