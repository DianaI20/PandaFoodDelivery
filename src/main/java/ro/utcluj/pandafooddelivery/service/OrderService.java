package ro.utcluj.pandafooddelivery.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.controller.dto.OrderDTO;
import ro.utcluj.pandafooddelivery.model.Order;
import ro.utcluj.pandafooddelivery.model.OrderStatus;
import ro.utcluj.pandafooddelivery.model.Restaurant;
import ro.utcluj.pandafooddelivery.repository.OrderRepository;
import ro.utcluj.pandafooddelivery.repository.RestaurantRepository;
import ro.utcluj.pandafooddelivery.service.mappper.OrderMapper;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class OrderService {


    private final static String ORDER_SAVED = "A new order was saved.";
    private final static String ORDER_UPDATED = "Order %d updates status from %s to %s";
    private final static String ORDER_ERROR = "Order could not be placed: %s";

    private OrderRepository orderRepository;
    private RestaurantRepository restaurantRepository;
    private OrderMapper orderMapper;
    private EmailSender emailSender;



    /**
     * Insert a new order.
     * @param orderDTO information about an order
     * @return ResponseEntity ok if the order was inserted successfully
     */
    public ResponseEntity save(OrderDTO orderDTO) {

        Order order = orderMapper.convertFromDTO(orderDTO);
        try {
            orderRepository.save(order);

        } catch (Exception e) {
            log.error(ORDER_ERROR);
            return ResponseEntity.internalServerError().build();
        }
        emailSender.sendEmail(order, "dianaiordan73@gmail.com");

        log.info(ORDER_SAVED);
        return ResponseEntity.ok().build();
    }

    /**
     * Find all orders placed at a restaurant.
     * @param id restaurant id
     * @return ResponseEntity containing all the orders from a specific id or Internal Server Error otherwise
     */
    public ResponseEntity findAllByRestaurantId(Long id) {

        Optional<Restaurant> restaurant = restaurantRepository.findById(id);
        if (restaurant.isPresent()) {
            return ResponseEntity.ok().body(restaurant.get().getOrders());
        } else {
            log.error(String.format(ORDER_ERROR, "restaurant not found"));
            return ResponseEntity.status(500).build();
        }
    }

    /**
     * Find orders by customer's id.
     * @param id customer id
     * @return all orders for a specific customer
     */
    public ResponseEntity findAllByCustomerId(Long id) {

        List<Order> orders = orderRepository.findByCustomerId(id);
        List<OrderDTO> orderDTOS = orderMapper.convertToDTO(orders);
        return ResponseEntity.ok().body(orderDTOS);
    }

    /**
     * Update status of an order.
     * @param orderId id of the order
     * @param status  new status to be set
     * @return ResponseEntity ok if the operation was successfully performed
     */
    public ResponseEntity updateStatus(Long orderId, OrderStatus status) {

        Order order = orderRepository.findById(orderId).get();
        OrderStatus oldStatus = order.getStatus();
        order.setStatus(status);
        orderRepository.save(order);
        log.info(String.format(ORDER_UPDATED,oldStatus, status));
        return ResponseEntity.ok().build();
    }
}
