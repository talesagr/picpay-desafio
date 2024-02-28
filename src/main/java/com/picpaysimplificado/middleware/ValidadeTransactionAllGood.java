package com.picpaysimplificado.middleware;

import com.picpaysimplificado.DTO.ValidateTransactionDTO;
import com.picpaysimplificado.exceptions.CustomException;

public class ValidadeTransactionAllGood extends ValidateTransactionMiddleware {
    public ValidadeTransactionAllGood() {
        super(null);
    }

    @Override
    public CustomException handler(ValidateTransactionDTO dto) {
        return null;
    }
}
