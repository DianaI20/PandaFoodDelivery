package ro.utcluj.pandafooddelivery.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.controller.dto.AdministratorDTO;
import ro.utcluj.pandafooddelivery.model.Administrator;
import ro.utcluj.pandafooddelivery.model.Restaurant;
import ro.utcluj.pandafooddelivery.service.exception.InvalidEmailException;
import ro.utcluj.pandafooddelivery.service.mappper.AdministratorMapper;
import ro.utcluj.pandafooddelivery.service.validator.EmailValidator;
import ro.utcluj.pandafooddelivery.service.validator.UserValidator;

@Service
@AllArgsConstructor
public class AdministratorRegistrationService {


    private final UserService userService;
    private RestaurantService restaurantService;
    private final EmailValidator emailValidator;
    private final AdministratorMapper administratorMapper;
    private UserValidator userValidator;

    /**
     * Register an administrator.
     * @param administratorDTO the information for administrator
     * @return Ok Response if the operation was successful
     */
    public ResponseEntity register(AdministratorDTO administratorDTO) {

        try {
            userValidator.validateRegistration(administratorDTO.getEmail());
        } catch (InvalidEmailException e) {
            return ResponseEntity.internalServerError().build();
        }

        Administrator admin = administratorMapper.convertFromDTO(administratorDTO);
        Restaurant restaurant = new Restaurant(administratorDTO.getRestaurantName(),
                administratorDTO.getRestaurantLocation(),
                administratorDTO.getDeliveryZone(), admin);
        userService.save(admin);
        restaurantService.save(restaurant);
        return ResponseEntity.ok().build();
    }

}
