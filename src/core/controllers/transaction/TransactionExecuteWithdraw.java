package core.controllers.transaction;

import core.controllers.BaseService;
import core.controllers.Validator;
import core.controllers.account.validate.PositiveNumberValidate;
import core.controllers.user.validate.StringNotEmptyValidate;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.Transaction;
import core.models.Withdraw;
import core.models.storage.TransactionStorage;

public class TransactionExecuteWithdraw extends BaseService implements TransactionType{

    public TransactionExecuteWithdraw() {
    }
    
    @Override
    public Response execute(String sourceAccountId, String destinationAccountId, String amount) {
        Validator validator = new Validator();
        try {

            if (!validator.validate(new StringNotEmptyValidate(), sourceAccountId)) {
                //String vacio
                return new Response("Source Account Id must not be empty", Status.BAD_REQUEST);
            }

            if (validator.validate(new StringNotEmptyValidate(), destinationAccountId)) {
                //String vacio
                return new Response("Destination Account Id must be empty", Status.BAD_REQUEST);
            }

            if (!validator.validate(new PositiveNumberValidate(), amount)) {
                return new Response("Amount must be a positive number", Status.BAD_REQUEST);
            }

            Account sourceAccount = findAccountById(sourceAccountId);

            double amountNumber = Double.parseDouble(amount);
            if (sourceAccount == null) {
                return new Response("ID does not match any account".toUpperCase(), Status.NOT_FOUND);
            }

            boolean withdrawed = sourceAccount.realizeMovement(new Withdraw(),amountNumber);
            if (withdrawed) {
                TransactionStorage.getInstance().addTransaction(new Transaction("WITHDRAW",sourceAccount, null, amountNumber));
                return new Response("OK", Status.CREATED);
            } else {
                return new Response("insufficient funds".toUpperCase(), Status.NOT_FOUND);
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
            return new Response("Unexpected Error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
