package servlet;

import util.DBConnection;
import dao.SubscriptionDAO;
import dao.SubscriptionDAOImpl;
import model.InventoryDTO;
import model.SubscriptionDTO;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
*
* @author Hussein Majed
*/
public class InventoryNotificationService {
    private SubscriptionDAO subscriptionDAO;

    private Connection connection;
    
    public InventoryNotificationService() throws SQLException {
        this.connection = DatabaseConnection.getInstance().getConnection();
    }
    
    /**
     * Constructs an InventoryNotificationService with a database connection.
     * 
     * @param connection the database connection used for DAO initialization.
     */
    public InventoryNotificationService(Connection connection) {
        try {
			this.subscriptionDAO = new SubscriptionDAOImpl();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    /**
     * Notifies all subscribers matching the criteria based on the given inventory.
     * 
     * 
     * @param inventory the inventory update to notify subscribers about.
     * @return true if there are matching subscribers and notifications were sent; false otherwise.
     */
    public boolean notifySubscribers(InventoryDTO inventory) {
    // Check if the surplus status of the inventory is 'yes'
    if ("yes".equalsIgnoreCase(inventory.getSurplusStatus())) {
        // Simulate sending a notification
        System.out.println("Notification sent successfully" + inventory.getName()+"in" + inventory.getLocation());
        return true; // Return true indicating a "notification" has been "sent"
    }
    return false; // Return false if the surplus status is not 'yes'

    }
}
