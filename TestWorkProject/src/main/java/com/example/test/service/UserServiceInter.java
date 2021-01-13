package com.example.test.service;

public interface UserServiceInter {

    String createUser(String login,String name,String password);

    String resetPassword(String login,String password);

}
