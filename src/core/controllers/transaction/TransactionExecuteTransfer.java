package core.controllers.transaction;

import core.controllers.BaseService;
import core.controllers.Validator;
import core.controllers.account.validate.PositiveNumberValidate;
import core.controllers.user.validate.StringNotEmptyValidate;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.Deposit;
import core.models.Transaction;
import core.models.Withdraw;
import core.models.storage.TransactionStorage;

public class TransactionExecuteTransfer extends BaseService implements TransactionType {

    public TransactionExecuteTransfer() {
    }

    @Override
    public Response execute(String sourceAccountId, String destinationAccountId, String amount) {
        try {
            Validator validator = new Validator();
            if (!validator.validate(new StringNotEmptyValidate(), sourceAccountId)) {
                return new Response("Source Account Id must not be empty", Status.BAD_REQUEST);
            }

            if (!validator.validate(new StringNotEmptyValidate(), destinationAccountId)) {
                return new Response("Destination Account Id must not be empty", Status.BAD_REQUEST);
            }

            if (!validator.validate(new PositiveNumberValidate(), amount)) {
                return new Response("Amount must be a positive number", Status.BAD_REQUEST);
            }

            Account sourceAccount = findAccountById(sourceAccountId);
            Account destinationAccount = findAccountById(destinationAccountId);

            if (sourceAccount == null) {
                return new Response("ID does not match any source account".toUpperCase(), Status.NOT_FOUND);
            }
            
            double amountNumber = Double.parseDouble(amount);
            if (destinationAccount == null) {
                sourceAccount.realizeMovement(new Deposit(), amountNumber);
                return new Response("ID does not match any account".toUpperCase(), Status.NOT_FOUND);
            }

            boolean withdrawed = sourceAccount.realizeMovement(new Withdraw(), amountNumber);
            if (!withdrawed) {
                return new Response("insufficient funds".toUpperCase(), Status.NOT_FOUND);
            }

            destinationAccount.realizeMovement(new Deposit(), amountNumber);
            TransactionStorage.getInstance().addTransaction(new Transaction("TRANSFER", sourceAccount, destinationAccount, amountNumber));
            return new Response("OK", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected Error", Status.INTERNAL_SERVER_ERROR);
        }

    }
}
