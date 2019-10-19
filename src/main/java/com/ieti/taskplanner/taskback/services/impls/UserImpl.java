package com.ieti.taskplanner.taskback.services.impls;

import com.ieti.taskplanner.taskback.model.User;
import com.ieti.taskplanner.taskback.services.UserService;
import java.util.*;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sergio
 */
@Service
public class UserImpl implements UserService {
    List<User> users = new ArrayList<>();

    @Override
    public List<User> getUsersList() {
        return users;
    }

    @Override
    public User getUserById(String id) {
        User user = null;
        for (User u : users){
            if(u.getId().equals(id)) user = u;
        }
        return user;
    }

    @Override
    public void createUser(User user) {
        users.add(user);
    }

    @Override
    public void updateUser(User user) {
        int i = 0;
        int pos = 0;
        boolean found = false;
        for(User u:users){
            if(u.getId().equals(user.getId())){
                pos = i;
                found = true;
            }
            i++;
        }
        if(found) {
            users.set(pos, user);
        }
    }

    @Override
    public void removeUser(String userId) {
        int i = 0;
        int pos = 0;
        boolean found = false;
        for(User u:users){
            i++;
            if(u.getId().equals(userId)){
                pos = i;
                found = true;
            }
        }
        if(found) {
            users.remove(pos);
        }
    }

    @Override
    public boolean authorizeUser(String id, String password ) {
        boolean authorize = false;
        for(User u : users){
            if(u.getId().equals(id) && u.getPassword().equals(password)) authorize=true;
        }
        return authorize;
    }
}
