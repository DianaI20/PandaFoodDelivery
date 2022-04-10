package ro.utcluj.pandafooddelivery.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.utcluj.pandafooddelivery.controller.request.CustomerRegistrationRequest;
import ro.utcluj.pandafooddelivery.service.UserService;

@RestController
@RequestMapping(path = "/customer/login")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public UserDetails login(@RequestBody CustomerRegistrationRequest request){
        return userService.loadUserByUsername(request.getEmail());
    }
}
