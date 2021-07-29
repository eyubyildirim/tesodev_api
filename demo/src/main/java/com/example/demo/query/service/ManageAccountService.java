package com.example.demo.query.service;

import com.example.demo.common.event.AccountActivatedEvent;
import com.example.demo.common.event.AccountCreatedEvent;
import com.example.demo.common.event.AccountCreditedEvent;
import com.example.demo.common.event.AccountDebitedEvent;
import com.example.demo.query.entity.Account;
import com.example.demo.query.query.FindAccountByIdQuery;
import com.example.demo.query.repository.AccountRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ManageAccountService {

    private final AccountRepository accountRepository;

    public ManageAccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @EventHandler
    public void on(AccountCreatedEvent accountCreatedEvent) {
        log.info("Handling AccountCreatedEvent...");
        Account account = new Account();
        account.setAccountId(accountCreatedEvent.getId());
        account.setBalance(accountCreatedEvent.getBalance());
        account.setStatus("CREATED");

        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountActivatedEvent accountActivatedEvent) {
        log.info("Handling AccountActivatedEvent...");
        accountRepository.findById(accountActivatedEvent.getId()).ifPresent(account -> {
            account.setStatus(accountActivatedEvent.getStatus());
            accountRepository.save(account);
        });
    }

    @EventHandler
    public void on(AccountCreditedEvent accountCreditedEvent) {
        log.info("Handling AccountCreditedEvent...");
        accountRepository.findById(accountCreditedEvent.getId()).ifPresent(account -> {
            account.setBalance(account.getBalance().add(accountCreditedEvent.getAmount()));
            accountRepository.save(account);
        });
    }

    @EventHandler
    public void on(AccountDebitedEvent accountDebitedEvent) {
        log.info("Handling AccountDebitedEvent...");
        accountRepository.findById(accountDebitedEvent.getId()).ifPresent(account -> {
            account.setBalance(account.getBalance().subtract(accountDebitedEvent.getAmount()));
            accountRepository.save(account);
        });
    }

    @QueryHandler
    public Account handle(FindAccountByIdQuery query) {
        log.info("Handling FindAccountByIdQuery...");

        return accountRepository.findById(query.getAccountId()).orElse(null);
    }
}
