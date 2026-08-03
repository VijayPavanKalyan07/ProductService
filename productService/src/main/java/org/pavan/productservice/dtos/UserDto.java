package org.pavan.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private long id;
    private String email;
    private String username;
    private String password;
    private NameDto name;
    private AddressDto address;
    private long phone;
}
