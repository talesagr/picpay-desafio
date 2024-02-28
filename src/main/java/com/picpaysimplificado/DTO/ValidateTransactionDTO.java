package com.picpaysimplificado.DTO;

import com.picpaysimplificado.domain.user.User;

import java.math.BigDecimal;

public record ValidateTransactionDTO(User user, BigDecimal amount) {
}
