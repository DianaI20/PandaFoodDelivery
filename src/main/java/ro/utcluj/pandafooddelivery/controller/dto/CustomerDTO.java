package ro.utcluj.pandafooddelivery.controller.dto;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class CustomerDTO {

    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final String password;
    private final String address;


}