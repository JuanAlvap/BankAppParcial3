/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers;

import bank.TransactionType;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.Transaction;
import core.models.User;
import java.util.ArrayList;

/**
 *
 * @author cande
 */
public class TransactionController {

    private static ArrayList<Account> accounts;
    private static ArrayList<Transaction> transactions;

    public static Response executeTransaction(String sourceAccountId, String destinationAccountId, String amount) {
        try {
            double amountNumber = Double.parseDouble(amount);
            Account sourceAccount = null;
            Account destinationAccount = null;
            for (Account account : accounts) {
                if (account.getId().equals(sourceAccountId)) {
                    sourceAccount = account;
                }
            }
            for (Account account : accounts) {
                if (account.getId().equals(destinationAccount)) {
                    destinationAccount = account;
                }
            }
            if (sourceAccount != null && destinationAccount != null && sourceAccount.withdraw(amountNumber)) {
                transactions.add(new Transaction(TransactionType.TRANSFER, sourceAccount, destinationAccount, amountNumber));
            }
        } catch (Exception ex) {
            return new Response("Unexpected Error", Status.INTERNAL_SERVER_ERROR);
        }

        return null;
    }

    public static Response executeWithdraw(String sourceAccountId, String amount) {
        try {
            double amountNumber = Double.parseDouble(amount);

            Account sourceAccount = null;
            for (Account account : accounts) {
                if (account.getId().equals(sourceAccountId)) {
                    sourceAccount = account;
                }
            }
            if (sourceAccount != null && sourceAccount.withdraw(amountNumber)) {
                transactions.add(new Transaction(TransactionType.WITHDRAW, sourceAccount, null, amountNumber));
            }
        } catch (Exception ex) {

        }

        return null;
    }

    public static Response executeDeposit(String destinationAccountId, String amount) {
        try {
            double amountNumber = Double.parseDouble(amount);

            Account destinationAccount = null;
            for (Account account : accounts) {
                if (account.getId().equals(destinationAccountId)) {
                    destinationAccount = account;
                }
            }
            if (destinationAccount != null) {
                destinationAccount.deposit(amountNumber);
                transactions.add(new Transaction(TransactionType.DEPOSIT, null, destinationAccount, amountNumber));

            }
        } catch (Exception ex) {

        }

        return null;
    }
}
