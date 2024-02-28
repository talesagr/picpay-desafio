package com.picpaysimplificado.exceptions.user;

import com.picpaysimplificado.exceptions.CustomException;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends CustomException {
    public UserNotFoundException() { super(HttpStatus.NOT_FOUND, "Usuário não encontrado!"); }
}
