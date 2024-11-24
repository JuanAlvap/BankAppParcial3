package core.controllers.transaction;

public class TransactionFactory {

    public static TransactionType getTransactionType(String typeOfTransaction) {
        return switch (typeOfTransaction) {
            case "Deposit" ->
                new TransactionExecuteDeposit();
            case "Withdraw" ->
                new TransactionExecuteWithdraw();
            case "Transfer" ->
                new TransactionExecuteTransfer();
            default ->
                throw new IllegalArgumentException("Invalid transaction type");
        };
    }
}
