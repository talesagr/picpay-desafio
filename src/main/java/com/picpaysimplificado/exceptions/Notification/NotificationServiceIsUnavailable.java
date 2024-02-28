package com.picpaysimplificado.exceptions.Notification;

import com.picpaysimplificado.exceptions.CustomException;
import org.springframework.http.HttpStatus;

public class NotificationServiceIsUnavailable extends CustomException {
    public NotificationServiceIsUnavailable(){
        super(HttpStatus.INTERNAL_SERVER_ERROR, "Serviço de notificacão esta fora do ar!");
    }

}
