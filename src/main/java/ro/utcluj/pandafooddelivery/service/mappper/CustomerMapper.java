package ro.utcluj.pandafooddelivery.service.mappper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcluj.pandafooddelivery.controller.dto.CustomerDTO;
import ro.utcluj.pandafooddelivery.model.Customer;

@Service
@AllArgsConstructor
public class CustomerMapper implements ObjectMapper<Customer, CustomerDTO> {


    @Override
    public Customer convertFromDTO(CustomerDTO customerDTO) {

        return new Customer(customerDTO.getFirstName(),
                            customerDTO.getLastName(),
                            customerDTO.getEmail(),
                            customerDTO.getPassword(),
                            customerDTO.getPhoneNumber(),
                            customerDTO.getAddress());
    }

    @Override
    public CustomerDTO convertToDTO(Customer customer) {

        return new CustomerDTO( customer.getFirstName(),
                                customer.getLastName(),
                                customer.getEmail(),
                                customer.getPhoneNumber(),
                                customer.getPassword(),
                                customer.getAddress());
    }

}
