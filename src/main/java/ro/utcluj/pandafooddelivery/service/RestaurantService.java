package ro.utcluj.pandafooddelivery.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.controller.dto.FoodItemDTO;
import ro.utcluj.pandafooddelivery.controller.dto.RestaurantDTO;
import ro.utcluj.pandafooddelivery.model.Restaurant;
import ro.utcluj.pandafooddelivery.model.User;
import ro.utcluj.pandafooddelivery.repository.RestaurantRepository;
import ro.utcluj.pandafooddelivery.repository.UserRepository;
import ro.utcluj.pandafooddelivery.service.mappper.FoodItemMapper;
import ro.utcluj.pandafooddelivery.service.mappper.RestaurantMapper;
import ro.utcluj.pandafooddelivery.service.validator.RestaurantValidator;

import javax.management.InstanceNotFoundException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class RestaurantService {


    private RestaurantRepository restaurantRepository;
    private UserRepository userRepository;
    private RestaurantMapper restaurantMapper;
    private FoodItemMapper foodItemMapper;
    private PDFExporter pdfExporter;
    private RestaurantValidator restaurantValidator;

    /**
     * Find all restaurants.
     *
     * @return All instances of type Restaurant
     * @throws InstanceNotFoundException
     */
    public ResponseEntity findAll() throws InstanceNotFoundException {

        List<Restaurant> restaurants = restaurantRepository.findAll();
        List<RestaurantDTO> restaurantDTOS = restaurantMapper.convertToDTO(restaurants);
        return ResponseEntity.ok().body(restaurantDTOS);
    }

    /**
     * Find restaurant by id.
     *
     * @param id Id of the restaurant
     * @return restaurant corresponding to that id
     */
    public ResponseEntity findById(Long id) {

        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            return ResponseEntity.ok().body(restaurantMapper.convertToDTO(restaurant.get()));
        } else {
            try {
                throw new InstanceNotFoundException("Restaurant does not exits");
            } catch (InstanceNotFoundException e) {
                return ResponseEntity.notFound().build();
            }
        }
    }

    /**
     * Find all food items from a restaurant.
     *
     * @param id id of the restaurant
     * @return a ResponseEntity containing the food items of that restaurant
     */
    public ResponseEntity findAllFoodItems(Long id) {

        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        try {
            restaurantValidator.validate(restaurant);
            List<FoodItemDTO> foodItemDTOS = foodItemMapper.convertToDTO(restaurant.get().getFoodItems());
            return ResponseEntity.ok().body(foodItemDTOS);
        } catch (InstanceNotFoundException e) {
            log.error("Instance not found");
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Find a restaurant by administrator.
     *
     * @param email email of the administrator
     * @return ResponseEntity containing restaurant information
     */
    public ResponseEntity findByAdministrator(String email) {

        User administrator = userRepository.findByEmail(email).get();
        Optional<Restaurant> restaurant = restaurantRepository.findByAdministrator(administrator);

        if (restaurant.isPresent()) {
            return ResponseEntity.ok().body(restaurantMapper.convertToDTO(restaurant.get()));
        } else {
            try {
                throw new InstanceNotFoundException("Restaurant does not exits");
            } catch (InstanceNotFoundException e) {
                return ResponseEntity.notFound().build();
            }
        }
    }

    /**
     * Insert a restaurant in the database
     *
     * @param restaurant object to be inserted
     * @return ResponseEntity.ok if the operation was performed successfully
     */
    public ResponseEntity save(Restaurant restaurant) {

        restaurantRepository.save(restaurant);
        log.info("New restaurant was saved. ");
        return (ResponseEntity.ok().build());
    }

    /**
     * @param id id of the restaurant which will have its menu printed
     * @return ok
     */
    public ResponseEntity exportPDF(Long id) {

        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        try {
            restaurantValidator.validate(restaurant);
            pdfExporter.export(restaurant.get());
        } catch (InstanceNotFoundException e) {
            log.error("Instance not found");
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }


}
