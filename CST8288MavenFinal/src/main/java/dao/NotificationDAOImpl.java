package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.SubscriptionDTO;
import model.NotificationDTO;

public class NotificationDAOImpl implements NotificationDAO {
	public NotificationDAOImpl() {
		
	}

	@Override
	public boolean sendNotification(NotificationDTO notification) {
        System.out.println("Sending notification...");
        System.out.println("To: " + notification.getUserID());
        System.out.println("Method: " + notification.getMethod());
        System.out.println("Message: " + notification.getMessage());
        return true;
	}
}
