package core.models;

public class Withdraw implements Movement {

    @Override
    public boolean execute(Account acc, double amount) {
        if (amount > acc.getBalance()) {
            return false;
        }
        acc.setBalance(acc.getBalance() - amount);
        return true;
    }
}
