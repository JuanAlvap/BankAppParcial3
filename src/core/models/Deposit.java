package core.models;

public class Deposit implements Movement {

    @Override
    public boolean execute(Account acc, double amount) {
        acc.setBalance(acc.getBalance() + amount);
        return true;

    }
}
