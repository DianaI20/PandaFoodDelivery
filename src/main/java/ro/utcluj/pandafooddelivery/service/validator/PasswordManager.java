package ro.utcluj.pandafooddelivery.service.validator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.service.exception.WrongPasswordException;

@Slf4j
@Service
@AllArgsConstructor
public class PasswordManager{

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final String WRONG_PASSWORD_MESSAGE = "User provided a wrong password.";
    /**
     *
     * @param password password not encoded
     * @param encryptedPassword encrypted password
     * @return true if the strings match, false otherwise
     */
    public boolean validate(String password, String encryptedPassword) throws WrongPasswordException {

        if(bCryptPasswordEncoder.matches(password, encryptedPassword)){
            return true;
        }else{
            log.error(WRONG_PASSWORD_MESSAGE);
            throw new WrongPasswordException(WRONG_PASSWORD_MESSAGE);
        }
    }

    /**
     *
     * @param rawPassword password
     * @return password encoded
     */
    public String encrypt(String rawPassword){
        return bCryptPasswordEncoder.encode(rawPassword);
    }
}
