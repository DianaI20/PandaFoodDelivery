package ro.utcluj.pandafooddelivery.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ro.utcluj.pandafooddelivery.model.Restaurant;
import ro.utcluj.pandafooddelivery.model.UserType;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class UserDTO {

    private final Long id;
    private final String email;
    private  String password;
    private final UserType userType;

    public UserDTO(Long id, String email, UserType userType) {
        this.id = id;
        this.email = email;
        this.userType = userType;
    }


}
