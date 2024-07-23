package dao;

import model.UserDTO;

public interface RegistrationDAO {
    boolean addUser(UserDTO user);
    UserDTO getUserByEmail(String email);
}
