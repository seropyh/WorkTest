
package com.example.test.service;

import com.example.test.entity.Roles;
import com.example.test.entity.Users;
import com.example.test.repos.RolesRepository;
import com.example.test.repos.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author Root
 */
@Service
@RequiredArgsConstructor

public class UsersService implements com.example.test.service.UserServiceInter {
    private final UsersRepository usersRepository;
    private final RolesRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    // создание пользователя
    @Override
    public String createUser(String login, String name, String password) {
        String error = null;
        Users newUser = null;
        try {
            newUser = new Users();
            newUser.setLogin(login);
            newUser.setName(name);
            newUser.setPassword(passwordEncoder.encode(password));
            Roles role = roleRepository.findByRoleName("ROLE_USER");
            newUser.setRole(role);
            newUser = usersRepository.save(newUser);
        } // Other error!!
        catch (Exception e) {
            error = e.getMessage();
        }
        return error;
    }


    // сброс пароля пользователя
    @Override
    public String resetPassword(String login, String password) {
        String error = null;
        Users user = null;
        try {
            user = usersRepository.findByLogin(login);
            if (user != null) {
                user.setPassword(passwordEncoder.encode(password));
                user = usersRepository.save(user);
            }
        } // Other error!!
        catch (Exception e) {
            error = e.getMessage();
        } // Other error!!
        return error;
    }
}




