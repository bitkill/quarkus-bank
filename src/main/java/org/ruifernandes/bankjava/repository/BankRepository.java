package org.ruifernandes.bankjava.repository;

import org.ruifernandes.bankjava.model.Balance;
import org.ruifernandes.bankjava.model.jpa.Client;
import org.ruifernandes.bankjava.model.jpa.Transaction;
import org.ruifernandes.bankjava.model.rest.RestClient;
import org.ruifernandes.bankjava.model.rest.RestTransaction;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.transaction.Transactional;

@ApplicationScoped
public class BankRepository {

    @Inject
    EntityManager entityManager;

    @Transactional
    public Client createClient(Client client) {
        entityManager.persist(client);
        return client;
    }

    @Transactional
    public Transaction createTransaction(Transaction transaction) {
        entityManager.persist(transaction);
        return transaction;
    }

    public Balance getBalance(Integer clientId) {
        Query query = entityManager.createNativeQuery(
            "SELECT debit - credit " +
                "FROM (SELECT COALESCE(sum(amount), 0) AS debit FROM transaction " +
                "WHERE to_client_id = ? ) a," +
                " ( SELECT COALESCE(sum(amount), 0) AS credit " +
                "FROM transaction WHERE from_client_id = ? ) b;"
        )
            .setParameter(1, clientId)
            .setParameter(2, clientId);

        var bal =  query.getResultList().stream().findFirst();

        return new Balance((Number) bal.orElse(0));
    }
}
