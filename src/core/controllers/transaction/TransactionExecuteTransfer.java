package core.controllers.transaction;

import core.controllers.Validator;
import core.controllers.account.validate.PositiveNumberValidate;
import core.controllers.transaction.validate.EmptyValidate;
import core.controllers.user.validate.StringNotEmptyValidate;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.Transaction;
import core.models.TransactionType;
import core.models.storage.AccountStorage;
import core.models.storage.TransactionStorage;

public class TransactionExecuteTransfer {

    public TransactionExecuteTransfer() {
    }
    
    public Response executeTransfer(String sourceAccountId, String destinationAccountId, String amount) {
        EmptyValidate emptyValidate = new EmptyValidate();
        try {
            Validator validator = new Validator();
            if (!validator.validate(new StringNotEmptyValidate(),sourceAccountId)) {
                //String vacio
                return new Response("Source Account Id must not be empty", Status.BAD_REQUEST);
            }

            if (!validator.validate(new StringNotEmptyValidate(),destinationAccountId)) {
                //String vacio
                return new Response("Destination Account Id must not be empty", Status.BAD_REQUEST);
            }

            if(!validator.validate(new PositiveNumberValidate(), amount)){
                return new Response("Amount must be a positive number", Status.BAD_REQUEST);
            }

            Account sourceAccount = null;
            Account destinationAccount = null;
            for (Account account : AccountStorage.getInstance().getAccounts()) {
                if (account.getId().equals(sourceAccountId)) {
                    sourceAccount = account;
                }
            }
            for (Account account : AccountStorage.getInstance().getAccounts()) {
                if (account.getId().equals(destinationAccountId)) {
                    destinationAccount = account;
                }
            }
            boolean withdrawed;
            
            double amountNumber = Double.parseDouble(amount);
            if(sourceAccount == null){
                return new Response("ID does not match any source account".toUpperCase(), Status.NOT_FOUND);
            }else{
                withdrawed = sourceAccount.withdraw(amountNumber);
            }
            
            if (destinationAccount != null && withdrawed) {
                destinationAccount.deposit(amountNumber);
                TransactionStorage.getInstance().addTransaction(new Transaction(TransactionType.TRANSFER, sourceAccount, destinationAccount, amountNumber));

                return new Response("OK", Status.CREATED);
            } else if (!withdrawed) {
                return new Response("insufficient funds".toUpperCase(), Status.NOT_FOUND);
            } else {
                sourceAccount.deposit((amountNumber));
                return new Response("ID does not match any account".toUpperCase(), Status.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new Response("Unexpected Error", Status.INTERNAL_SERVER_ERROR);
        }

    }
    
}
