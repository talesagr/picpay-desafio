package com.picpaysimplificado.exceptions.user;

import com.picpaysimplificado.exceptions.CustomException;
import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends CustomException {

    public UserAlreadyExistsException() {
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Usuário já esta cadastrado!");
    }
}
