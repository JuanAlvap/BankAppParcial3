/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core.controllers.account.validate;

import core.models.Account;
import core.models.storage.AccountStorage;
import java.util.Random;

/**
 *
 * @author Juanpi
 */
public class AccountCreateIdAccountValidate {

    public AccountCreateIdAccountValidate() {
    }

    public String createIdAccountValidate() {
        String accountId = "";
        boolean validateRandom = true;
        while (validateRandom) {
        Random random = new Random();
        int first = random.nextInt(1000);
        int second = random.nextInt(1000000);
        int third = random.nextInt(100);

        accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);

        for (Account account : AccountStorage.getInstance().getAccounts()) {
            if (account.getId().equals(accountId)) {
                validateRandom = true;
                break;
            } else {
                validateRandom = false;
            }
        }
        }
        return accountId;
    }

}
