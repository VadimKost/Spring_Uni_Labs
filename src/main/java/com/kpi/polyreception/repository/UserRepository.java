package com.kpi.polyreception.repository;

import com.kpi.polyreception.entity.Role;
import com.kpi.polyreception.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public User findByUsername(String username) {
        for(User user : users){
            if(user.getUsername().equals(username))
                return user;
        }
        return null;
    }

    public List<User> findAll() {
        return users;
    }

    public void save(User user) {
        users.add(user);
    }
}
