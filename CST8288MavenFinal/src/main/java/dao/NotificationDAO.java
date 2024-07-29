package dao;

import model.NotificationDTO;

public interface NotificationDAO {

	boolean sendNotification(NotificationDTO notification);
}
