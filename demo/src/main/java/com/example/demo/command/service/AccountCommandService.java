package com.example.demo.command.service;

import com.example.demo.command.command.CreateAccountCommand;
import com.example.demo.command.command.DepositMoneyCommand;
import com.example.demo.command.command.WithdrawMoneyCommand;
import com.example.demo.command.dto.CreateAccountRequest;
import com.example.demo.command.dto.DepositRequest;
import com.example.demo.command.dto.WithdrawRequest;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class AccountCommandService {

    private final CommandGateway commandGateway;

    public AccountCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> createAccount(CreateAccountRequest createAccountRequest) {
        return commandGateway.send(new CreateAccountCommand(UUID.randomUUID().toString(), createAccountRequest.getStartingBalance()));
    }

    public CompletableFuture<String> depositToAccount(DepositRequest depositRequest) {
        return commandGateway.send(new DepositMoneyCommand(depositRequest.getAccountId(), depositRequest.getAmount()));
    }

    public CompletableFuture<String> withdrawFromAccount(WithdrawRequest withdrawRequest) {
        return commandGateway.send(new WithdrawMoneyCommand(withdrawRequest.getAccountId(), withdrawRequest.getAmount()));
    }
}
