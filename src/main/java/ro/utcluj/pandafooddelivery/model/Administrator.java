package ro.utcluj.pandafooddelivery.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Administrator extends User{


    public Administrator(String firstName, String lastName, String email, String password, String phoneNumber) {
        super(firstName, lastName, email, password, phoneNumber, UserType.ADMINISTRATOR);
     }

    public Administrator() {

    }

}
