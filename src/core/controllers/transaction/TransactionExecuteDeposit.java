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
import core.models.storage.TransactionStorage;

public class TransactionExecuteDeposit extends BaseService implements TransactionType {

    public TransactionExecuteDeposit() {
    }

    @Override
    public Response execute(String sourceAccountId, String destinationAccountId, String amount) {
        Validator validator = new Validator();
        try {

            if (validator.validate(new StringNotEmptyValidate(), sourceAccountId)) {
                return new Response("Source Account Id must be empty", Status.BAD_REQUEST);
            }

            if (!validator.validate(new StringNotEmptyValidate(), destinationAccountId)) {
                return new Response("Destination Account Id must not be empty", Status.BAD_REQUEST);
            }

            if (!validator.validate(new PositiveNumberValidate(), amount)) {
                return new Response("Amount must be a positive number", Status.BAD_REQUEST);
            }

            Account destinationAccount = findAccountById(destinationAccountId);

            if (destinationAccount == null) {
                return new Response("ID does not match any account".toUpperCase(), Status.NOT_FOUND);
            }

            double amountNumber = Double.parseDouble(amount);
            destinationAccount.realizeMovement(new Deposit(), amountNumber);
            TransactionStorage.getInstance().addTransaction(new Transaction("DEPOSIT", null, destinationAccount, amountNumber));
            return new Response("OK", Status.CREATED);

        } catch (Exception ex) {
            return new Response("Unexpected Error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}
