package br.com.alura.codechella.controller;

import br.com.alura.codechella.domain.authentication.service.UserRegistrationService;
import br.com.alura.codechella.domain.authentication.vo.UserRegistrationData;
import br.com.alura.codechella.domain.authentication.vo.UserData;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserRegistrationService userRegistrationService;

    public UserController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping
    @Transactional
    public ResponseEntity<UserData> register(@RequestBody @Valid UserRegistrationData registrationData, UriComponentsBuilder uriComponentsBuilder) {
        var registeredUserData = userRegistrationService.registerUser(registrationData);
        var uri = uriComponentsBuilder.path("users/{id}").buildAndExpand(registeredUserData.id()).toUri();
        return ResponseEntity.created(uri).body(registeredUserData);
    }

}
