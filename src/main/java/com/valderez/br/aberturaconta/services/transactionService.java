package com.valderez.br.aberturaconta.services;

import com.valderez.br.aberturaconta.domain.transaction.Transaction;
import com.valderez.br.aberturaconta.domain.user.User;
import com.valderez.br.aberturaconta.dtos.TransactionDTO;
import com.valderez.br.aberturaconta.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Service
public class transactionService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionRepository repository;
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private NotificationService notificationService;

  public Transaction createTransaction(TransactionDTO transaction) throws Exception {
      User sender = this.userService.findUserById(transaction.senderID());
      User receiver = this.userService.findUserById(transaction.receiverID());

      userService.validateTransaction(sender, transaction.value());

    boolean isAuthorized = this.authorizeTransaction(sender, transaction.value());
      if(isAuthorized){
          throw new Exception("Transação não autorizada!");
      }
      Transaction newTransaction = new Transaction();
      newTransaction.setAmount(transaction.value());
      newTransaction.setSender(sender);
      newTransaction.setReceiver(receiver);
      newTransaction.setTimestamp(LocalDateTime.now());

      sender.setBalance(sender.getBalance().subtract(transaction.value()));
      receiver.setBalance(receiver.getBalance().add(transaction.value()));

      this.repository.save(newTransaction);
      this.userService.saveUser(sender);
      this.userService.saveUser(receiver);

      this.notificationService.sendNotification(sender, "Transação realizado com sucesso!");
      this.notificationService.sendNotification(receiver, "Transferência recebido com sucesso!");

      return  newTransaction;

  }
  public  boolean authorizeTransaction(User sender, BigDecimal value){
    ResponseEntity<Map> authorizationResponse = restTemplate.getForEntity("(https://run.mocky.io/v3/54dc2cf1-3add-45b5-b5a9-6bf7e7f1f4a6", Map.class);

            if(authorizationResponse.getStatusCode() == HttpStatus.OK){
                String message = (String) authorizationResponse.getBody().get("message");
                return "Autorização".equalsIgnoreCase(message);
            }else return false;
  }
}
