package com.picpaysimplificado.services;

import com.picpaysimplificado.DTO.UserDTO;
import com.picpaysimplificado.DTO.ValidateTransactionDTO;
import com.picpaysimplificado.domain.user.User;
import com.picpaysimplificado.exceptions.user.UserNotFoundException;
import com.picpaysimplificado.middleware.ValidadeTransactionAllGood;
import com.picpaysimplificado.middleware.ValidateTransactionCheckBalanceAboveZero;
import com.picpaysimplificado.middleware.ValidateTransactionCheckUserType;
import com.picpaysimplificado.middleware.ValidateTransactionMiddleware;
import com.picpaysimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        ValidateTransactionDTO dto = new ValidateTransactionDTO(sender, amount);
        ValidateTransactionMiddleware validate =
                new ValidateTransactionCheckBalanceAboveZero(
                        new ValidateTransactionCheckUserType(
                                new ValidadeTransactionAllGood()
                        )
                );
        validate.handler(dto);
    }

    public User findUserById(Long id) throws Exception{
        return this.repository.findUserById(id).orElseThrow(() -> new UserNotFoundException());
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return this.repository.findAll();
    }

    public void saveUser(User user){
        this.repository.save(user);
    }
}
