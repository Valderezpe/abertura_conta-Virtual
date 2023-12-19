package com.valderez.br.aberturaconta.Controllers;

import com.valderez.br.aberturaconta.domain.transaction.Transaction;
import com.valderez.br.aberturaconta.dtos.TransactionDTO;
import com.valderez.br.aberturaconta.services.transactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transf")
public class TransActionController {
    @Autowired
    private transactionService transactionService;

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionDTO Transaction) throws Exception {
        Transaction newTransaction = this.transactionService.createTransaction(Transaction);
        return  new ResponseEntity<>(newTransaction, HttpStatus.OK);
    }
}
