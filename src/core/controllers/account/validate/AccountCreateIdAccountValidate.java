package core.controllers.account.validate;

import core.models.Account;
import core.models.storage.AccountStorage;
import java.util.Random;

public class AccountCreateIdAccountValidate {

    public AccountCreateIdAccountValidate() {
    }

    public String createIdAccountValidate() {
        String accountId = "";
        boolean validateRandom = true;
        do {
            Random random = new Random();
            int first = random.nextInt(1000);
            int second = random.nextInt(1000000);
            int third = random.nextInt(100);

            accountId = String.format("%03d", first) + "-" + String.format("%06d", second) + "-" + String.format("%02d", third);

            for (Account account : AccountStorage.getInstance().getAccounts()) {
                if (account.getId().equals(accountId)) {
                    validateRandom = false;
                    break;
                }
            }
        } while (!validateRandom);

        return accountId;
    }

}
