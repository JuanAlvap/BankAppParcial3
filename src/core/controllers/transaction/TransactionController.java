/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.transaction;

import bank.TransactionType;
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
public class TransactionController {

    public static ArrayList<Transaction> getSortedTransactions() {
        ArrayList<Transaction> sortedClone = (ArrayList<Transaction>) getTransactions().clone();
        Collections.reverse(sortedClone);
        return sortedClone;
    }

    private static ArrayList<Transaction> getTransactions() {
        return TransactionStorage.getInstance().getTransactions();
    }

    public static Response executeTransfer(String sourceAccountId, String destinationAccountId, String amount) {
        try {

            try {
                //No es numero

            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            if (sourceAccountId.equals("")) {
                //String vacio
                return new Response("Destination Account Id must not be empty", Status.BAD_REQUEST);
            }

            if (destinationAccountId.equals("")) {
                //String vacio
                return new Response("Destination Account Id must not be empty", Status.BAD_REQUEST);
            }

            if (amount.equals("")) {
                return new Response("amount must not be empty", Status.BAD_REQUEST);
            }

            try {
                //No es numero
            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            double amountNumber;

            try {
                //Verificar si es numero
                amountNumber = Double.parseDouble(amount);
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

    public static Response executeWithdraw(String sourceAccountId, String amount) {
        try {

            if (sourceAccountId.equals("")) {
                //String vacio
                return new Response("Destination Account Id must not be empty", Status.BAD_REQUEST);
            }

            if (amount.equals("")) {
                return new Response("amount must not be empty", Status.BAD_REQUEST);
            }

            try {
                //No es numero

            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            double amountNumber;

            try {
                //Verificar si es numero
                amountNumber = Double.parseDouble(amount);
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
            System.out.println("ERROR: "+ ex);
            return new Response("Unexpected Error", Status.INTERNAL_SERVER_ERROR);
        }
    }

    public static Response executeDeposit(String destinationAccountId, String amount) {
        try {

            if (destinationAccountId.equals("")) {
                //String vacio
                return new Response("Destination Account Id must not be empty", Status.BAD_REQUEST);
            }

            if (amount.equals("")) {
                return new Response("amount must not be empty", Status.BAD_REQUEST);
            }

            try {
                //No es numero

            } catch (NumberFormatException ex) {
                return new Response("Id must be numeric", Status.BAD_REQUEST);
            }

            double amountNumber;

            try {
                //Verificar si es numero
                amountNumber = Double.parseDouble(amount);
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
