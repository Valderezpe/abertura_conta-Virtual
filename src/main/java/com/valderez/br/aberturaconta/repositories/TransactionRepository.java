package com.valderez.br.aberturaconta.repositories;

import com.valderez.br.aberturaconta.domain.transaction.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
