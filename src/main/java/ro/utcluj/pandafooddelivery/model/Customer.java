package ro.utcluj.pandafooddelivery.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User{

    private String address;

    public Customer(String firstName, String lastName, String email, String password, String phoneNumber, String address) {
        super(firstName, lastName, email, password, phoneNumber, UserType.CUSTOMER);
        this.address = address;
    }


}
