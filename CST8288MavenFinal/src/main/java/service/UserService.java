package service;

import dao.RegistrationDAO;
import dao.RegistrationDAOImpl;
import model.UserDTO;

public class UserService {
    private RegistrationDAO registrationDAO = new RegistrationDAOImpl();

    public boolean registerUser(UserDTO user) {
        if (registrationDAO.getUserByEmail(user.getEmail()) == null) {
            registrationDAO.addUser(user);
            return true;
        }
        return false;
    }

    public UserDTO authenticate(String email, String password) {
        UserDTO user = registrationDAO.getUserByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }
}
