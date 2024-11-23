package core.controllers.transaction;

import core.controllers.utils.Response;

public interface TransactionType {
    public Response execute(String source, String destination, String amount);
}
