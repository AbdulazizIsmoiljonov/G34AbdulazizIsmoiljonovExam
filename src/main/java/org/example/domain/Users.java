package org.example.domain;

public class Users {
    private Integer sequence = 0;
    {
        sequence++;
    }
    private int id = sequence;
    private String email;
    private String name;
    private String password;

    public Users(String name, String password,String email) {
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
