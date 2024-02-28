package com.picpaysimplificado.exceptions.user;

import com.picpaysimplificado.exceptions.CustomException;
import org.springframework.http.HttpStatus;

public class UserTypeMerchantNotAuthorizedToDoTransactionException extends CustomException {
    public UserTypeMerchantNotAuthorizedToDoTransactionException(){
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Usuário do tipo lojista não está autorizado a realizar transação!");
    }

}
