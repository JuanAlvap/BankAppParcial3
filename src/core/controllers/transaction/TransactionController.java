/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.transaction;

import core.models.TransactionType;
import core.controllers.transaction.validate.EmptyValidate;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.Transaction;
import core.models.User;
import core.models.storage.AccountStorage;
import core.models.storage.TransactionStorage;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author cande
 */
public class TransactionController extends BaseController {

    public static ArrayList<Transaction> getSortedTransactions() {
        ArrayList<Transaction> sortedClone = (ArrayList<Transaction>) getTransactions().clone();
        Collections.reverse(sortedClone);
        return sortedClone;
    }

    private static ArrayList<Transaction> getTransactions() {
        return TransactionStorage.getInstance().getTransactions();
    }

    public static Response executeTransfer(String sourceAccountId, String destinationAccountId, String amount) {
        EmptyValidate emptyValidate = new EmptyValidate();
        try {

            if (!emptyValidate.isEmpty(sourceAccountId)) {
                //String vacio
                return new Response("Source Account Id must not be empty", Status.BAD_REQUEST);
            }

            if (!emptyValidate.isEmpty(destinationAccountId)) {
                //String vacio
                return new Response("Destination Account Id must not be empty", Status.BAD_REQUEST);
            }

            double amountNumber;

            try {
                amountNumber = Double.parseDouble(amount);
                if (amountNumber < 0) {
                    return new Response("Amount must be numeric", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Amount must be numeric", Status.BAD_REQUEST);
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
            boolean withdrawed = sourceAccount.withdraw(amountNumber);
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

    public static Response executeWithdraw(String sourceAccountId, String destinationAccountId, String amount) {
        EmptyValidate emptyValidate = new EmptyValidate();
        try {

            if (!emptyValidate.isEmpty(sourceAccountId)) {
                //String vacio
                return new Response("Source Account Id must not be empty", Status.BAD_REQUEST);
            }

            if (emptyValidate.isEmpty(destinationAccountId)) {
                return new Response("Destination Account Id not valid", Status.BAD_REQUEST);
            }

            double amountNumber;

            try {
                //Verificar si es numero
                amountNumber = Double.parseDouble(amount);
                if (amountNumber < 0) {
                    return new Response("Amount must be numeric", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Amount must be numeric", Status.BAD_REQUEST);
            }

            Account sourceAccount = null;
            for (Account account : AccountStorage.getInstance().getAccounts()) {
                if (account.getId().equals(sourceAccountId)) {
                    sourceAccount = account;
                }
            }

            if (sourceAccount == null) {
                return new Response("ID does not match any account".toUpperCase(), Status.NOT_FOUND);
            }

            boolean withdrawed = sourceAccount.withdraw(amountNumber);
            if (withdrawed) {
                TransactionStorage.getInstance().addTransaction(new Transaction(TransactionType.WITHDRAW, sourceAccount, null, amountNumber));
                return new Response("OK", Status.CREATED);
            } else {
                return new Response("insufficient funds".toUpperCase(), Status.NOT_FOUND);
            }
        } catch (Exception ex) {
            System.out.println("ERROR: " + ex);
            return new Response("Unexpected Error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response executeDeposit(String sourceAccountId, String destinationAccountId, String amount) {
        EmptyValidate emptyValidate = new EmptyValidate();
        try {

            if (!emptyValidate.isEmpty(sourceAccountId)) {
                //String vacio
                return new Response("Source Account Id not valid", Status.BAD_REQUEST);
            }

            if (!emptyValidate.isEmpty(destinationAccountId)) {
                return new Response("Destination Account Id must not be empty", Status.BAD_REQUEST);
            }

            double amountNumber;

            try {
                //Verificar si es numero
                amountNumber = Double.parseDouble(amount);
                if (amountNumber < 0) {
                    return new Response("Amount must be numeric", Status.BAD_REQUEST);
                }
            } catch (NumberFormatException ex) {
                return new Response("Amount must be numeric", Status.BAD_REQUEST);
            }

            Account destinationAccount = null;
            for (Account account : AccountStorage.getInstance().getAccounts()) {
                if (account.getId().equals(destinationAccountId)) {
                    destinationAccount = account;
                }
            }
            if (destinationAccount != null) {
                destinationAccount.deposit(amountNumber);
                TransactionStorage.getInstance().addTransaction(new Transaction(TransactionType.DEPOSIT, null, destinationAccount, amountNumber));
                return new Response("OK", Status.CREATED);
            } else {
                return new Response("ID does not match any account".toUpperCase(), Status.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new Response("Unexpected Error", Status.INTERNAL_SERVER_ERROR);
        }
    }
}