package ro.utcluj.pandafooddelivery.controller.registration;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import ro.utcluj.pandafooddelivery.controller.request.UserDTO;
import ro.utcluj.pandafooddelivery.model.User;
import ro.utcluj.pandafooddelivery.service.UserService;
import ro.utcluj.pandafooddelivery.service.exception.WrongPasswordException;

@RestController
@RequestMapping(path = "/login")
public class LoginController {

    @Autowired
    private UserService userService;
    private  User currentUser;


    @GetMapping
    public ResponseEntity getUserByUsernameAndPassword(@RequestParam("email") String email, @RequestParam String password){

        try {
            currentUser = userService.getUserByUsernameAndPassword(email, password);
            return ResponseEntity.ok().body(new UserDTO(currentUser.getId(), currentUser.getEmail(),currentUser.getUserType()));
        } catch (WrongPasswordException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }catch (UsernameNotFoundException f){
            f.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}

