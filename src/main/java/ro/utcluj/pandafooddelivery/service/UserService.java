package ro.utcluj.pandafooddelivery.service;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.model.User;
import ro.utcluj.pandafooddelivery.repository.UserRepository;
import ro.utcluj.pandafooddelivery.service.exception.WrongPasswordException;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private final static String USER_NOT_FOUND_MESSAGE = "User with email %s not found";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MESSAGE, email)));
    }

    public User getUserByUsernameAndPassword(String email, String password) throws UsernameNotFoundException, WrongPasswordException {
        Optional<User>  user = userRepository.findByEmail(email);
        if(!user.isPresent()){
            throw new UsernameNotFoundException("Incorrect email");
        }else{
            if(bCryptPasswordEncoder.matches(password,user.get().getPassword())){
                return user.get();
            }else {
                throw new WrongPasswordException("Incorrect password. ");
            }
        }

    }

    public String signUpUser(User user){
        boolean userExists = userRepository.findByEmail(user.getUsername()).isPresent();
        if(userExists){
            throw new IllegalStateException("Email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        // TODO  : send confirmation token
        return "it works";
    }
}
