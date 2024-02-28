package com.picpaysimplificado.services;

import com.picpaysimplificado.DTO.TransactionDTO;
import com.picpaysimplificado.domain.transaction.Transaction;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.exceptions.transaction.NotAuthorizedTransactionException;
import com.picpaysimplificado.exceptions.user.InsuficientBalanceException;
import com.picpaysimplificado.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository repository;

    @Autowired
    private NotificationService notificationService;
    @Autowired
    private RestTemplate restTemplate;

    @Transactional(rollbackFor = Exception.class)
    public Transaction createTransaction(TransactionDTO transaction) throws Exception{
        User sender = this.userService.findUserById(transaction.senderID());
        User receiver = this.userService.findUserById(transaction.receiverID());

        validateAndAuthorizeTransaction(sender, transaction.value());

        Transaction newTransaction = this.setNewTransaction(transaction.value(), sender, receiver);

        updateBalanceAndSave(sender,receiver,newTransaction);

        notifyUsers(sender,receiver);

        return newTransaction;
    }

    private void notifyUsers(User sender, User receiver) throws Exception {
        this.notificationService.sendNotification(sender, "Transacao realizada com sucesso!");
        this.notificationService.sendNotification(receiver, "Transacao recebida com sucesso!");
    }

    private void updateBalanceAndSave(User sender, User receiver, Transaction transaction) {
        setBalanceOnTransaction(sender,receiver,transaction.getAmount());
        repository.save(transaction);
        userService.saveUser(sender);
        userService.saveUser(receiver);
    }

    private void validateAndAuthorizeTransaction(User sender, BigDecimal amount) throws Exception {
        userService.validateTransaction(sender, amount);
        if(!authorizeTransaction(sender,amount)) {
            throw new NotAuthorizedTransactionException();
        }

    }

    private void setBalanceOnTransaction(User sender, User receiver, BigDecimal value) {
        sender.setBalance(sender.getBalance().subtract(value));
        receiver.setBalance(receiver.getBalance().add(value));
    }

    private Transaction setNewTransaction(BigDecimal value, User sender, User receiver) {
        return new Transaction(value,sender,receiver,LocalDateTime.now());
    }

    public boolean authorizeTransaction(User sender, BigDecimal value){
       ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("https://run.mocky.io/v3/5794d450-d2e2-4412-8131-73d0293ac1cc", Map.class);
       if(authorizationResponse.getStatusCode() == HttpStatus.OK){
           String message = (String) authorizationResponse.getBody().get("message");
           return "Autorizado".equalsIgnoreCase(message);
       } else return false;
    }

    public List<Transaction> getAllTransactions(){
        return this.repository.findAll();
    }

}
