package ro.utcluj.pandafooddelivery.service.validator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.model.User;
import ro.utcluj.pandafooddelivery.repository.UserRepository;
import ro.utcluj.pandafooddelivery.service.exception.InvalidEmailException;
import ro.utcluj.pandafooddelivery.service.exception.WrongPasswordException;
import ro.utcluj.pandafooddelivery.service.mappper.UserMapper;

import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class UserValidator {


    private UserMapper userMapper;
    private UserRepository userRepository;
    private final static String USER_NOT_FOUND_MESSAGE = "User with email %s not found";
    private final static String USER_ALREADY_EXISTS = "User with email %s already exists.";

    private PasswordManager passwordManager;

    public ResponseEntity validateCredentials(String email, String password, Optional<User> user) throws WrongPasswordException {

        if (!user.isPresent()) {
            log.error(String.format(USER_NOT_FOUND_MESSAGE, email));
            throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email));
        } else {
            if (passwordManager.validate(password, user.get().getPassword())) {
                return ResponseEntity.ok().body(userMapper.convertToDTO(user.get()));
            }
            return ResponseEntity.internalServerError().build();
        }
    }

    public void  validateRegistration(String email) throws InvalidEmailException {

        boolean userExists = userRepository.findByEmail(email).isPresent();
        if (userExists) {
            log.error(String.format(USER_ALREADY_EXISTS,email));
            throw new InvalidEmailException(USER_ALREADY_EXISTS);
        }
    }
}
