package com.picpaysimplificado.exceptions.transaction;

import com.picpaysimplificado.exceptions.CustomException;
import org.springframework.http.HttpStatus;

public class NotAuthorizedTransactionException extends CustomException {
    public NotAuthorizedTransactionException(){
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Transação não autorizada");
    }
}
