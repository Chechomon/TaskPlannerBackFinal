package com.ieti.taskplanner.taskback.services;

import com.ieti.taskplanner.taskback.model.User;
import java.util.List;

/**
 *
 * @author Sergio
 */
public interface UserService {
    List<User> getUsersList();
    User getUserById(String id);
    boolean createUser(User user);
    void updateUser(User user);
    void removeUser(String userId);
    boolean authorizeUser(String id, String password);
    User findUserByUsernameAndPassword(String username, String password);
}
