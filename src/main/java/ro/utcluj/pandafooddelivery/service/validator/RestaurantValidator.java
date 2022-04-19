package ro.utcluj.pandafooddelivery.service.validator;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ro.utcluj.pandafooddelivery.model.Restaurant;

import javax.management.InstanceNotFoundException;
import java.util.Optional;

@Slf4j
@Component
public class RestaurantValidator {


    public void validate(Optional<Restaurant> restaurant) throws InstanceNotFoundException {
        if(!restaurant.isPresent()){
            log.info("Trying to access an object that does not exist");
            throw new InstanceNotFoundException("Restaurant does not exits");
        }
    }
}
