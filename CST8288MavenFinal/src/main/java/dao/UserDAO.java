package dao;

import model.User;
import java.sql.SQLException;

public interface UserDAO {

	void insertUser(User user) throws SQLException;
	User validateUser(String email, String password) throws SQLException;
	int getUserCount(String username) throws SQLException;
	void resetUserCount(String username) throws SQLException;
}