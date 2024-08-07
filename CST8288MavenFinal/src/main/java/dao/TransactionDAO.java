package dao;

import java.util.List;
import model.TransactionDTO;

/**
 * Interface for managing transactions in the database.
 * @author Hussein
 */
public interface TransactionDAO {
    /**
     * Adds a transaction to the database.
     * @param transaction The TransactionDTO object representing the transaction to be added.
     * @return true if the transaction was successfully added, false otherwise.
     */
    boolean addTransaction(TransactionDTO transaction);

    /**
     * Retrieves transactions related to a specific user.
     * @param userID The ID of the user whose transactions need to be retrieved.
     * @return A list of TransactionDTO objects representing all transactions related to that user.
     */
    List<TransactionDTO> getTransactionsByUserID(int userID);
}
