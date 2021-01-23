/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test;

import com.example.test.entity.Roles;
import com.example.test.entity.Users;
import com.example.test.repos.RolesRepository;
import com.example.test.repos.UsersRepository;
import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Root
 * 
 */
//инициализация базы даннхы пользователями и ролями
@Component
@RequiredArgsConstructor
public class SetupDataLoader implements  ApplicationListener<ContextRefreshedEvent> {
    private boolean alreadySetup = false;
    private final UsersRepository userRepository;
    private final RolesRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    
    
    
     // API


    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }

        final Roles adminRole =  createRoleIfNotFound("Администратор","ROLE_ADMIN");
        final Roles userRole = createRoleIfNotFound("Пользователь","ROLE_USER");

        // == create initial user
        createUserIfNotFound("admin", "admin", "admin", adminRole);

        alreadySetup = true;
    }

   

    @Transactional
    private final Roles createRoleIfNotFound(final String name,final String roleName) {
        Roles role = roleRepository.findByRoleName(roleName);
        if (role == null) {
            role = new Roles(roleName,name);
        }
        role = roleRepository.save(role);
        return role;
    }

    @Transactional
    private final Users createUserIfNotFound(final String login, final String name, final String password,Roles role) {
        Users user = userRepository.findByLogin(login);
        if (user == null) {
            user = new Users();
            user.setName(name);
            user.setPassword(passwordEncoder.encode(password));
            user.setLogin(login);
            
        }
        user.setRole(role);
        user = userRepository.save(user);
        return user;
    }

}

