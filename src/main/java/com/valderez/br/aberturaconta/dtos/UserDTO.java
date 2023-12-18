package com.valderez.br.aberturaconta.dtos;

import com.valderez.br.aberturaconta.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String firstname, String lastName, String document, BigDecimal balance, String email, String password, UserType userType) {
}
