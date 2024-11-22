package core.controllers.transaction;

import core.controllers.Validator;
import core.controllers.account.validate.PositiveNumberValidate;
import core.controllers.user.validate.StringNotEmptyValidate;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.Transaction;
//import core.models.TransactionType;
import core.models.storage.AccountStorage;
import core.models.storage.TransactionStorage;

public class TransactionExecuteDeposit implements TransactionType {

    public TransactionExecuteDeposit() {
    }
    
    @Override
    public Response execute(String sourceAccountId, String destinationAccountId, String amount) {
        Validator validator = new Validator();
        try {

            if (validator.validate(new StringNotEmptyValidate(), sourceAccountId)) {
                //String vacio
                return new Response("Source Account Id must be empty", Status.BAD_REQUEST);
            }

            if (!validator.validate(new StringNotEmptyValidate(), destinationAccountId)) {
                //String vacio
                return new Response("Destination Account Id must not be empty", Status.BAD_REQUEST);
            }

            if (!validator.validate(new PositiveNumberValidate(), amount)) {
                return new Response("Amount must be a positive number", Status.BAD_REQUEST);
            }

            Account destinationAccount = null;
            for (Account account : AccountStorage.getInstance().getAccounts()) {
                if (account.getId().equals(destinationAccountId)) {
                    destinationAccount = account;
                }
            }

            double amountNumber = Double.parseDouble(amount);

            if (destinationAccount != null) {
                destinationAccount.deposit(amountNumber);
                TransactionStorage.getInstance().addTransaction(new Transaction( null, destinationAccount, amountNumber));
                return new Response("OK", Status.CREATED);
            } else {
                return new Response("ID does not match any account".toUpperCase(), Status.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new Response("Unexpected Error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
