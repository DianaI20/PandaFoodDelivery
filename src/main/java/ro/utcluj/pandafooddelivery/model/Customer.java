package ro.utcluj.pandafooddelivery.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Customer extends User {


    private String address;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders;

    public Customer(String firstName, String lastName, String email, String password, String phoneNumber, String address) {
        super(firstName, lastName, email, password, phoneNumber, UserType.CUSTOMER);
        this.address = address;
    }

    @Override
    public String toString() {

        return  "First name:"       + this.getFirstName() + "\n" +
                "Last name:"        + this.getLastName() + "\n" +
                "Address:"          + this.getAddress() + "\n" +
                "Phone number:"     + this.getPhoneNumber() + "\n";

    }
}
