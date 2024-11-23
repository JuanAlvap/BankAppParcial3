package core.controllers.transaction;

import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Transaction;
import core.models.storage.TransactionStorage;
import java.util.ArrayList;

public class TransactionController{

    public static ArrayList<Transaction> getTransactions() {
        return TransactionStorage.getInstance().getTransactions();
    }

    public static Response execute(String sourceAccountId, String destinationAccountId, String amount, String typeOfTransaction) {
        TransactionType type = null;
        switch (typeOfTransaction) {
            case "Deposit" ->
                type = new TransactionExecuteDeposit();
            case "Withdraw" ->
                type = new TransactionExecuteWithdraw();
            case "Transfer" ->
                type = new TransactionExecuteTransfer();
            default -> {
                return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
            }
        }

        Response response = type.execute(sourceAccountId, destinationAccountId, amount);
        return response;
    }

}
