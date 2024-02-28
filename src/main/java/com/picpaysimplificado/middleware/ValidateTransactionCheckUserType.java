package com.picpaysimplificado.middleware;

import com.picpaysimplificado.DTO.ValidateTransactionDTO;
import com.picpaysimplificado.domain.user.UserType;
import com.picpaysimplificado.exceptions.CustomException;
import com.picpaysimplificado.exceptions.user.UserTypeMerchantNotAuthorizedToDoTransactionException;

public class ValidateTransactionCheckUserType extends ValidateTransactionMiddleware {
    public ValidateTransactionCheckUserType(ValidateTransactionMiddleware next) {
        super(next);
    }

    @Override
    public CustomException handler(ValidateTransactionDTO dto) {
        if (dto.user().getUserType() == UserType.MERCHANT){
            return new UserTypeMerchantNotAuthorizedToDoTransactionException();
        }
        return next.handler(dto);
    }
}
