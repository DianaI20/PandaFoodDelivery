package ro.utcluj.pandafooddelivery.service.validators;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
@AllArgsConstructor
public class EmailValidator implements Predicate<String> {

    @Override
    public boolean test(String s) {
        //  TODO Regex validate email
        return true;
    }
}
