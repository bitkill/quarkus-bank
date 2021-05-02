package org.ruifernandes.bankjava.service;

import org.ruifernandes.bankjava.model.Balance;
import org.ruifernandes.bankjava.model.jpa.Client;
import org.ruifernandes.bankjava.model.jpa.Transaction;
import org.ruifernandes.bankjava.repository.BankRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class BankService {

    @Inject
    BankRepository bankRepository;

    public Client newClient(Integer balance) {
        Client client = bankRepository.createClient(new Client(0, "", "", ""));
        bankRepository.createTransaction(new Transaction(0,0, client.getId(), balance));
        return client;
    }

    public Transaction newTransaction(Transaction transaction) {
        return bankRepository.createTransaction(transaction);
    }

    public Balance getBalance(Integer clientId) {
        return bankRepository.getBalance(clientId);
    }
}
