/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test.repos;

import com.example.test.entity.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Root
 */

//JPA (Java Persistence API)
// описывающая систему управления сохранением java объектов в таблицы реляционных баз данных в удобном виде.

//Spring Data — это репозиторий. Это несколько интерфейсов которые используют JPA Entity для
      //  взаимодействия с ней.    CrudRepository обеспечивает основные операции по поиску, сохранения, удалению данных



public interface RolesRepository  extends JpaRepository<Roles, String>{
     Roles findByRoleName(String roleName);

}
