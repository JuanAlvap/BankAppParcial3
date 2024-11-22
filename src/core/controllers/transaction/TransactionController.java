/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.transaction;

import core.controllers.BaseController;
import core.controllers.Validator;
import core.controllers.account.validate.PositiveNumberValidate;
import core.controllers.transaction.validate.EmptyValidate;
import core.controllers.user.validate.StringNotEmptyValidate;
import core.controllers.utils.Response;
import core.controllers.utils.Status;
import core.models.Account;
import core.models.Transaction;
//import core.models.TransactionType;
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

    public static Response execute(String sourceAccountId, String destinationAccountId, String amount, String typeOfTransaction) {
        TransactionType type = null;
        switch(typeOfTransaction){
            case "Deposit" -> type = new TransactionExecuteDeposit();
            case "Withdraw" -> type = new TransactionExecuteWithdraw();
            case "Transfer" -> type = new TransactionExecuteTransfer();
            default -> {
                return new Response("Unexpected error", Status.INTERNAL_SERVER_ERROR);
            }
        }
        
        Response response = type.execute(sourceAccountId, destinationAccountId, amount);
        return response;
        }
        
    }

