
package com.example.test.form;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Root
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserForm {
    private String name;
    private String login;
    private String password;
    private String confirmPassword;
    private String role;

    
}
