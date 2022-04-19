package ro.utcluj.pandafooddelivery.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import ro.utcluj.pandafooddelivery.model.User;
import ro.utcluj.pandafooddelivery.service.UserService;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    public User getUserByEmail(String email){
        return userService.loadUserByUsername(email);
    }
}
