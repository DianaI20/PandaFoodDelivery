package ro.utcluj.pandafooddelivery.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.controller.dto.CustomerDTO;
import ro.utcluj.pandafooddelivery.service.exception.InvalidEmailException;
import ro.utcluj.pandafooddelivery.service.mappper.CustomerMapper;
import ro.utcluj.pandafooddelivery.service.validator.EmailValidator;
import ro.utcluj.pandafooddelivery.service.validator.UserValidator;

@Slf4j
@Service
@AllArgsConstructor
public class CustomerRegistrationService {


    private final UserService userService;
    private final UserValidator userValidator;
    private final CustomerMapper customerMapper;
    private final String USER_REGISTERED = "User with email %s has been registered successfully";

    /**
     * Register a customer.
     * @param customerDTO information for a customer
     * @return Ok response if the operation was performed successfully
     */
    public ResponseEntity register(CustomerDTO customerDTO) {

        try {
            userValidator.validateRegistration(customerDTO.getEmail());
        } catch (InvalidEmailException e) {
            return ResponseEntity.internalServerError().build();
        }

        log.info(String.format(USER_REGISTERED, customerDTO.getEmail()));
        userService.save(customerMapper.convertFromDTO(customerDTO));
        return ResponseEntity.ok().build();
    }
}
