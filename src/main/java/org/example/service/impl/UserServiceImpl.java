package org.example.service.impl;

import org.example.domain.Users;
import org.example.service.UserService;

import java.util.Scanner;

public class UserServiceImpl implements UserService {
    static Scanner scanner = new Scanner(System.in);


    @Override
    public Users login(String email, String password) {
        for (Users user : USERS) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public Users register(String name, String email, String password) {
        for (Users user1 : USERS) {
            if (user1.getEmail().equals(email) && user1.getPassword().equals(password)) {
                return null;
            }
        }
        Users users = new Users(name, password, email);
        USERS.add(users);
        return users;
    }

    @Override
    public void changePassword(String newPassword) {
        for (Users users : USERS){
            users.setPassword(newPassword);
        }
    }
}