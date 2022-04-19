package ro.utcluj.pandafooddelivery.service.validator;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.service.exception.InvalidEmailException;

@Slf4j
@Service
public class EmailValidator {

    private String INVALID_EMAIL_MESSAGE = "Email %s is not valid";

    public void validate(String email) throws InvalidEmailException {
        //  TODO Regex validate email
        if(email.length() == 0){
            log.error(String.format(INVALID_EMAIL_MESSAGE, email));
            throw new InvalidEmailException("Email not valid");
        }
    }
}
