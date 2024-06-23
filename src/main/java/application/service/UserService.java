package application.service;

import application.dao.UserDao;
import application.entity.User;

import javax.persistence.EntityManagerFactory;
import java.util.List;

public class UserService {

    private final UserDao userDao;

    public UserService(EntityManagerFactory factory) {
        userDao = new UserDao(factory);
    }

    public void addUser(User newUser) {
        userDao.create(newUser);
    }

    public void updateUser(User updatedUser) {
        userDao.update(updatedUser);
    }

    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    // for login
    public User findUser(String user, String pass) throws Exception {
        List<User> users = userDao.find(user);
        if (users.size() == 0) {
            throw new Exception("User not found!");
        }
        User u = users.get(0);

        if (!pass.equals(u.getPassword())) { // Use equals instead of compareTo for string comparison
            throw new Exception("Password does not match");
        }
        return u;
    }
}
