/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test.controllers;

import com.example.test.form.PasswordForm;
import com.example.test.form.UserForm;
import com.example.test.service.UsersService;
//import com.app.validation.UserValidator;
import java.security.Principal;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Root
 */
@Controller
@RequiredArgsConstructor

public class MainController {
    
   private final UsersService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {

        return "loginPage";
    }
   
    
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model) {
        model.addAttribute("title", "Logout");
        return "loginPage";
    }
     @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String page403(Model model) {
        model.addAttribute("title", "Logout");
        return "403";
    }
    
    @RequestMapping("/registerSuccessful")
    public String viewRegisterSuccessful(Model model) {
        return "/registerSuccessfulPage";
    }
    // Show Register page.

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String viewRegister(Model model) {

        UserForm form = new UserForm();
        model.addAttribute("userForm", form);
        return "/registerPage";
    }

    // This method is called to save the registration information.
    // @Validated: To ensure that this Form
    // has been Validated before this method is invoked.
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String saveRegister(Model model, //
            @ModelAttribute("userForm") @Validated UserForm userForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
        // Validate result
        if (result.hasErrors()) {
            return "registerPage";
        }
        String error = userService.createUser(userForm.getLogin(), userForm.getName(), userForm.getPassword());
        if (error != null && !error.isEmpty()) {
            model.addAttribute("errorMessage", error);
            return "registerPage";
        }
        model.addAttribute("login", userForm.getLogin());
        return "registerSuccessPage";
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
    public String ressetPassword(Model model, Principal principal) {
        String login = principal.getName();
        PasswordForm form = new PasswordForm(login);
        model.addAttribute("passForm", form);
        return "resetPassword";
    }

    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
    public String ressetingPassword(Model model, //
            @ModelAttribute("passForm") @Validated PasswordForm passForm, //
            BindingResult result, //
            final RedirectAttributes redirectAttributes) {
        String res = "resetPassword"; //ПЕРЕХОД С ДАННЫМИ
                // Validate result
                if (!result.hasErrors()) {
                    String error = userService.resetPassword(passForm.getLogin(), passForm.getPassword());
                    if (error != null && !error.isEmpty()) {
                        model.addAttribute("errorMessage", error);
                    } else {
                        res = "redirect:/logout";
                    }
                }
        return res;
    }


}
