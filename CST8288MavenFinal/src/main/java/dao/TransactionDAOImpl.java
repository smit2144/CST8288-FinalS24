package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.TransactionDTO;
import util.DBConnection;

/**
 * @author Hussein
 */

public class TransactionDAOImpl implements TransactionDAO {
	
    private static final String SELECT_TRANSACTIONS_BY_USER_ID
    = "SELECT t.TransactionID, t.ItemID, t.Quantity, t.Amount, t.Date, t.TransactionType, f.Name AS ItemName "
    + "FROM Transactions t "
    + "JOIN FoodItems f ON t.ItemID = f.ItemID "
    + "WHERE t.UserID = ?";
    
    /**
     * Retrieve transactions associated with the specified user ID from  database.
     * @param userId of the user whose transactions need to be retrieved.
     * @return A list of TransactionDTO objects representing the transactions of the specified user.
     */
    
    @Override
    public List<TransactionDTO> getTransactionsByUserID(int userID) {
        List<TransactionDTO> transactions = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRANSACTIONS_BY_USER_ID)) {
            preparedStatement.setInt(1, userID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    // Assuming you have a constructor that matches these parameters or you've adjusted it accordingly.
                    TransactionDTO transaction = new TransactionDTO(
                            resultSet.getInt("TransactionID"),
                            resultSet.getString("ItemName"), // Assuming this should replace ItemID with ItemName
                            resultSet.getInt("itemID"),
                            resultSet.getInt("Quantity"),
                            userID,
                            resultSet.getDouble("Amount"),
                            resultSet.getTimestamp("Date"),
                            resultSet.getString("TransactionType")
                    );
                    transactions.add(transaction);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return transactions;
    }
    /**
     * Insert a new transaction into the database.
     * @param transaction The TransactionDTO object representing the transaction to be inserted.
     */
    public void insertTransaction(TransactionDTO transaction) {
        String sql = "INSERT INTO Transactions (ItemID, UserID, Quantity, Amount, Date, TransactionType) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection(); 
        PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            
            preparedStatement.setInt(1, transaction.getItemID());
            preparedStatement.setInt(2, transaction.getUserID());
            preparedStatement.setInt(3, transaction.getQuantity());
            preparedStatement.setDouble(4, transaction.getAmount());
            preparedStatement.setTimestamp(5, new java.sql.Timestamp(transaction.getDate().getTime()));
            preparedStatement.setString(6, transaction.getTransactionType());

            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TransactionDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Retrieve transactions associated with the specified user ID from the database.
     *
     * @param userId The ID of the user whose transactions are to be retrieved.
     * @return A list of TransactionDTO objects representing the transactions of the specified user.
     * @throws SQLException if a database access error occurs.
     */
    public List<TransactionDTO> getTransactions(int userId) throws SQLException {
        List<TransactionDTO> transactions = new ArrayList<>();

        String query = "SELECT t.*, f.Name " +
                       "FROM Transactions t " +
                       "JOIN fooditems f ON t.ItemID = f.ItemID " +
                       "WHERE f.UserID = ?";
        try (Connection connection = DBConnection.getConnection(); 
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                TransactionDTO transaction = new TransactionDTO();
                transaction.setTransactionID(resultSet.getInt("TransactionID"));
                transaction.setItemName(resultSet.getString("Name"));
                transaction.setUserID(resultSet.getInt("UserID"));
                transaction.setQuantity(resultSet.getInt("Quantity"));
                transaction.setAmount(resultSet.getDouble("Amount"));
                transaction.setDate(resultSet.getTimestamp("Date"));
                transaction.setTransactionType(resultSet.getString("TransactionType"));

                transactions.add(transaction);
            }
        }

        return transactions;
    }
    /**
     * Retrieve all transactions from the database.
     *
     * @return A list of TransactionDTO objects representing all transactions.
     * @throws SQLException if a database access error occurs.
     */
    public List<TransactionDTO> getAllTransactions() throws SQLException {
        List<TransactionDTO> transactions = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Get connection
            connection = DBConnection.getConnection();

            // SQL query to select specific columns from both tables
            String query = "SELECT t.TransactionID, t.ItemID, t.UserID, t.Quantity, t.Amount, t.Date, t.TransactionType, f.Name " +
                   "FROM Transactions t " +
                   "JOIN FoodItems f ON t.ItemID = f.ItemID ";

            // Create statement
            statement = connection.prepareStatement(query);

            // Execute query
            resultSet = statement.executeQuery();

            // Process result set
            while (resultSet.next()) {
                // Create TransactionDTO object
                TransactionDTO transaction = new TransactionDTO();
                // Set transaction properties
                transaction.setTransactionID(resultSet.getInt("TransactionID"));
                transaction.setItemID(resultSet.getInt("ItemID"));
                transaction.setUserID(resultSet.getInt("UserID"));
                transaction.setQuantity(resultSet.getInt("Quantity"));
                transaction.setAmount(resultSet.getDouble("Amount"));
                transaction.setDate(resultSet.getTimestamp("Date"));
                transaction.setTransactionType(resultSet.getString("TransactionType"));
                transaction.setItemName(resultSet.getString("Name"));
                // Add transaction to list
                transactions.add(transaction);
            }
        } finally {
            // Close resources
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return transactions;
    }
	@Override
	public boolean addTransaction(TransactionDTO transaction) {
		// TODO Auto-generated method stub
		return false;
	}
      
}
