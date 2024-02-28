package com.picpaysimplificado.middleware;

import com.picpaysimplificado.DTO.ValidateTransactionDTO;
import com.picpaysimplificado.exceptions.CustomException;
import com.picpaysimplificado.exceptions.user.InsuficientBalanceException;

import java.math.BigDecimal;

public class ValidateTransactionCheckBalanceAboveZero extends ValidateTransactionMiddleware {

    public ValidateTransactionCheckBalanceAboveZero(ValidateTransactionMiddleware next) {
        super(next);
    }

    @Override
    public CustomException handler(ValidateTransactionDTO dto) {
        if (dto.user().getBalance().compareTo(dto.amount()) < 0 || dto.user().getBalance().compareTo(BigDecimal.ZERO) < 0) {
            throw new InsuficientBalanceException();
        }
        return next.handler(dto);
    }
}
