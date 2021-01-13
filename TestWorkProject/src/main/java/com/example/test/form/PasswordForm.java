/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author Root
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordForm {

    private String login;
    private String password;
    private String confirmPassword;

    public PasswordForm(String login) {
        this.login = login;
    }



}
