package ro.utcluj.pandafooddelivery.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.model.User;
import ro.utcluj.pandafooddelivery.repository.UserRepository;
import ro.utcluj.pandafooddelivery.service.exception.InvalidEmailException;
import ro.utcluj.pandafooddelivery.service.exception.WrongPasswordException;
import ro.utcluj.pandafooddelivery.service.validator.PasswordManager;
import ro.utcluj.pandafooddelivery.service.validator.UserValidator;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {


    private UserRepository userRepository;
    private final PasswordManager passwordManager;
    private UserValidator userValidator;

    @Override
    public User loadUserByUsername(String email) throws UsernameNotFoundException {
        return null;
    }

    /**
     * @param email user's email
     * @param password user's password
     * @return user that matches thee email and password
     * @throws UsernameNotFoundException
     * @throws WrongPasswordException
     */
    public ResponseEntity findByEmailAndPassword(String email, String password)
            throws UsernameNotFoundException, WrongPasswordException {

        Optional<User> user = userRepository.findByEmail(email);
        return userValidator.validateCredentials(email, password, user);
    }

    /**
     * @param user user to be inserted
     * @return ResponseEntity.ok if the user was inserted successfully
     */
    public ResponseEntity save(User user) {

        user.setPassword(passwordManager.encrypt(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }
}
