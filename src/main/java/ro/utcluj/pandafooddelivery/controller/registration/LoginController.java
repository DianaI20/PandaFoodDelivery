package ro.utcluj.pandafooddelivery.controller.registration;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.utcluj.pandafooddelivery.controller.request.UserDTO;
import ro.utcluj.pandafooddelivery.model.User;
import ro.utcluj.pandafooddelivery.service.UserService;
import ro.utcluj.pandafooddelivery.service.exception.WrongPasswordException;

@RestController
@RequestMapping(path = "/login")
@AllArgsConstructor
public class LoginController {

    private final UserService userService;
    private  User currentUser;

    @GetMapping
    public ResponseEntity getUserByUsernameAndPassword(@RequestBody UserDTO request){

        try {
            currentUser = userService.getUserByUsernameAndPassword(request.getEmail(), request.getPassword());
            return ResponseEntity.ok().body(new UserDTO(currentUser.getEmail(), currentUser.getPassword()));
        } catch (WrongPasswordException e) {
            e.printStackTrace();
        }catch (UsernameNotFoundException f){
            f.printStackTrace();
        }
        return null;
    }

}

