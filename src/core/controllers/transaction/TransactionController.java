package core.controllers.transaction;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Transaction;
import core.models.storage.TransactionStorage;
import java.util.ArrayList;

public class TransactionController {

    public static ArrayList<Transaction> getTransactions() {
        return TransactionStorage.getInstance().getTransactions();
    }

    public static Response execute(String sourceAccountId, String destinationAccountId, String amount, String typeOfTransaction) {
        try {
            TransactionType type = TransactionFactory.getTransactionType(typeOfTransaction);
            return type.execute(sourceAccountId, destinationAccountId, amount);
        } catch (IllegalArgumentException e) {
            return new Response(e.getMessage(), Status.INTERNAL_SERVER_ERROR);
        }
    }

}
