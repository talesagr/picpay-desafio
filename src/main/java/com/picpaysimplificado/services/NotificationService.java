package com.picpaysimplificado.services;

import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.exceptions.Notification.NotificationServiceIsUnavailable;
import org.springframework.stereotype.Service;
import java.util.Random;

@Service
public class NotificationService {

    public void sendNotification(User user, String message) throws Exception {
        Random random = new Random();
        if(random.nextBoolean() == false){
            throw new NotificationServiceIsUnavailable();
        }

        System.out.println("Notificacao enviada para o Usuario!");
    }
}
