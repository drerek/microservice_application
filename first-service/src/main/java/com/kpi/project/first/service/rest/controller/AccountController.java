package com.kpi.project.first.service.rest.controller;

import com.kpi.project.first.service.entity.User;
import com.kpi.project.first.service.service.AccountService;
import com.kpi.project.first.service.service.vm.LoginVM;
import com.kpi.project.first.service.service.vm.RecoveryPasswordVM;
import com.kpi.project.first.service.service.vm.UserAndTokenVM;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@RestController
@PropertySource("classpath:strings.properties")
public class AccountController {

    private static Logger log = LoggerFactory.getLogger(AccountController.class);

    @Autowired
    private AccountService accountService;

    @PostMapping("/auth/login")
    public ResponseEntity<UserAndTokenVM> login(@Valid @RequestBody LoginVM loginModel) {
        log.debug("Trying to login user by credentials");

        UserAndTokenVM userAndTokenVM = accountService.login(loginModel);

        log.debug("Set token '{}' to header", userAndTokenVM.getToken());

        HttpHeaders headers = new HttpHeaders();
        headers.add("Token", userAndTokenVM.getToken());

        log.debug("Send user data with response status OK");

        return new ResponseEntity<>(userAndTokenVM, headers, HttpStatus.OK);
    }

    @PostMapping("/auth/register")
    public ResponseEntity registerAccount(@Valid @RequestBody User user) {
        log.debug("Trying to register user");

        accountService.register(user);

        log.debug("Send response status CREATED");

        return new ResponseEntity(HttpStatus.CREATED);
    }

    @PostMapping("/auth/confirmation")
    public ResponseEntity confirmRegistration(@Valid @RequestBody RecoveryPasswordVM model) {
        log.debug("Trying to confirm password for user by token '{}'", model.getToken());

        accountService.confirmRegistration(model);

        log.debug("Send response status OK");

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/auth/recovery/{email}")
    public ResponseEntity mailRecoveryPassword(@PathVariable String email) {
        log.debug("Trying to recovery password by email '{}'", email);

        accountService.recoveryPasswordMail(email);

        log.debug("Send response status ACCEPTED");

        return new ResponseEntity(HttpStatus.ACCEPTED);
    }

    @PostMapping("/auth/recovery")
    public ResponseEntity passwordRecovery(@Valid @RequestBody RecoveryPasswordVM model) {
        log.debug("Trying to recovery password by token '{}'", model.getToken());

        accountService.confirmRegistration(model);

        log.debug("Send response status OK");

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
