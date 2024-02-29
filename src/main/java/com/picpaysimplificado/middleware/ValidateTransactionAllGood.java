package com.picpaysimplificado.middleware;

import com.picpaysimplificado.DTO.ValidateTransactionDTO;
import com.picpaysimplificado.exceptions.CustomException;

public class ValidateTransactionAllGood extends ValidateTransactionMiddleware {
    public ValidateTransactionAllGood() {
        super(null);
    }

    @Override
    public CustomException handler(ValidateTransactionDTO dto) {
        return null;
    }
}
