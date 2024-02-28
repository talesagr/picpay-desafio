package com.picpaysimplificado.middleware;

import com.picpaysimplificado.DTO.ValidateTransactionDTO;
import com.picpaysimplificado.exceptions.CustomException;

public abstract class ValidateTransactionMiddleware {
    protected ValidateTransactionMiddleware next;

    public ValidateTransactionMiddleware(ValidateTransactionMiddleware next){
        this.next = next;
    }

    public abstract CustomException handler(ValidateTransactionDTO dto);
}
