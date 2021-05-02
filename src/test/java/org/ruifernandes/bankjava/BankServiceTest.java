package org.ruifernandes.bankjava;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.ruifernandes.bankjava.containers.PostgresContainer;
import org.ruifernandes.bankjava.model.Balance;
import org.ruifernandes.bankjava.model.jpa.Client;
import org.ruifernandes.bankjava.model.jpa.Transaction;
import org.ruifernandes.bankjava.repository.BankRepository;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
@QuarkusTestResource(PostgresContainer.class)
public class BankServiceTest {

    @Inject
    BankRepository bankRepository;

    @Test
    void createClient() {
        var client = new Client(null, "", "", "");

        client = bankRepository.createClient(client);

        assertEquals(Integer.valueOf(1), client.getId());
    }

    @Test
    void createTransaction() {
        var transaction = new Transaction(null, 1, 2, 100);
        transaction = bankRepository.createTransaction(transaction);

        assertEquals( 1, transaction.getId());
    }

    @Test
    void getBalance() {
        Balance secondClientBalance = bankRepository.getBalance(100);
        assertEquals(0, secondClientBalance.getBalance().intValue());
    }

}
