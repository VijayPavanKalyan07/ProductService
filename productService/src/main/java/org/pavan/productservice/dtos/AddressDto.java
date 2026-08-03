package org.pavan.productservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddressDto {
    private String city;
    private String street;
    private int number;
    private String zipcode;
    private GeolocationDto geolocation;
}
