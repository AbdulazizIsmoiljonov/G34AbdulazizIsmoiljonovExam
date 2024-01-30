package org.example.service;

import org.example.domain.Users;

import java.util.ArrayList;

public interface UserService {
    ArrayList<Users> USERS = new ArrayList<>();

    Users login(String email, String password);

    Users register(String name, String email, String password);

    void changePassword(String newPassword);
}
