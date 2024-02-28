package com.picpaysimplificado.DTO;

import java.math.BigDecimal;

public record TransactionDTO(BigDecimal value, Long senderID, Long receiverID) {

}
