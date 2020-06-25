package formatter.service;

import formatter.dao.UserDAO;
import formatter.dao.impl.UserDAOImpl;

import java.util.List;

public class UserService {
    UserDAO userDAO = new UserDAOImpl();
    public List<String> getAllLogins() {
        return userDAO.getAllLogins();
    }


}
