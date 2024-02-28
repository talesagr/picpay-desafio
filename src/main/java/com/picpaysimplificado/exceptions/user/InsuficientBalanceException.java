package com.picpaysimplificado.exceptions.user;

import com.picpaysimplificado.exceptions.CustomException;
import org.springframework.http.HttpStatus;

public class InsuficientBalanceException extends CustomException {
    public InsuficientBalanceException(){
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Saldo insuficiente!");
    }
}
