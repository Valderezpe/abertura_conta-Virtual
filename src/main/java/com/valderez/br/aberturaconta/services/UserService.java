package com.valderez.br.aberturaconta.services;

import com.valderez.br.aberturaconta.domain.user.User;
import com.valderez.br.aberturaconta.domain.user.UserType;
import com.valderez.br.aberturaconta.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public  void ValidateTransaction(User sender, BigDecimal amount) throws Exception {
      if(sender.getUserType() == UserType.MERCHANT){
          throw  new Exception("Usurious tipo Lojista não estar autprizado a realizar transação");
        }
      if (sender.getBalance().compareTo(amount) <0){
          throw new Exception("Saldo insuficiente");
      }
    }
    public User findUserById(Long id) throws Exception {
    return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuario não encontrado!"));
    }
}
