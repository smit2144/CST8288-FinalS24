package service;

import dao.RegistrationDAO;
import dao.RegistrationDAOImpl;
import dao.LoginDAO;
import model.UserDTO;

public class UserService {
    private RegistrationDAO registrationDAO = new RegistrationDAOImpl();
    private LoginDAO loginDAO = new LoginDAO();

    public boolean registerUser(UserDTO user) {
        if (registrationDAO.getUserByEmail(user.getEmail()) == null) {
            return registrationDAO.addUser(user);
        }
        return false;
    }

    public UserDTO authenticate(String email, String password) {
        return loginDAO.getUserByEmailAndPassword(email, password);
    }
}
