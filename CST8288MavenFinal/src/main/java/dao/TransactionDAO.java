package dao;

import java.util.List;
import model.TransactionDTO;

/**
 * Interface for managing transactions in the database.
 * Defines methods to retrieve transaction based on user ID.
 * @author Hussein
 */

public interface TransactionDAO{

	 /**
     * Retrieve transactions related to a specific user.
     * @param userId of the user whose transactions need to be retrieved.
     * @return A list of TransactionDTO objects representing all transactions related to that user.
     */

	List<TransactionDTO> getTransactionsByUserID (int userID);
	
}
