package org.pavan.productservice.services;

import org.pavan.productservice.dtos.LoginRequestDto;
import org.pavan.productservice.dtos.LoginResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequest);
}
