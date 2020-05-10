package com.aubay.hackathon.controller;

import com.aubay.hackathon.enums.RoleEnum;
import com.aubay.hackathon.model.core.UserCore;
import com.aubay.hackathon.model.request.UserRequest;
import com.aubay.hackathon.model.response.UserResponse;
import com.aubay.hackathon.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.Optional;

import static com.aubay.hackathon.constants.ApplicationConstant.USER_END_POINT;

@RestController
@RequestMapping(USER_END_POINT)
public class UserController {

    private final ModelMapper mapper;

    private final IUserService userService;

    @Autowired
    public UserController(ModelMapper mapper, IUserService userService) {
        this.mapper = mapper;
        this.userService = userService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {

        Optional<UserCore> userCore = userService.get(id);
        if (userCore.isPresent()) {
            UserResponse userResponse = mapper.map(userCore.get(), UserResponse.class);
            userResponse.setRole(UserResponse.Role.builder()
                    .id(RoleEnum.getByCode(userCore.get().getRole()).getCode())
                    .descricao(RoleEnum.getByCode(userCore.get().getRole()).name())
                    .build());
            return ResponseEntity.ok(userResponse);
        }
        return ResponseEntity.noContent().build();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveUser(@RequestBody @Valid UserRequest userRequest){

        UserCore userCore = mapper.map(userRequest, UserCore.class);
        return ResponseEntity.ok(mapper.map(userService.save(userCore), UserResponse.class));
    }

    @PutMapping(value = "/{user_id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> updateUser(@PathVariable("user_id") Long userId, @RequestBody @Valid UserRequest userRequest){

        UserCore userCore = mapper.map(userRequest, UserCore.class);
        userCore.setId(userId);
        return ResponseEntity.ok(mapper.map(userService.save(userCore), UserResponse.class));
    }
}
