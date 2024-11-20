package core.models.storage;

import java.util.ArrayList;
import core.models.Transaction;

public class TransactionStorage {

    private static TransactionStorage instance;

    private ArrayList<Transaction> transactions;

    private TransactionStorage() {
        this.transactions = new ArrayList<>();
    }

    public static TransactionStorage getInstance() {
        if (instance == null) {
            instance = new TransactionStorage();
        }
        return instance;
    }

    public boolean addTransaction(Transaction transaction) {
        if (transaction.getDestinationAccount() == null && transaction.getSourceAccount() == null) {
            return false;
        }
        this.transactions.add(transaction);
        return true;
    }

}
