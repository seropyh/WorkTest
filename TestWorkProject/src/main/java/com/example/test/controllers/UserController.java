/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.test.controllers;


import com.example.test.entity.Users;
import com.example.test.form.PasswordForm;

import com.example.test.repos.UsersRepository;

import com.example.test.service.UsersService;

import java.io.*;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author Root
 */
@Controller
@RequiredArgsConstructor
public class UserController {

    /*@Autowired
    UsersRepository usersRepository;
    @Autowired
    UsersService usersService;
    */
    private final UsersRepository usersRepository;

    private final UsersService usersService;


    int inPage = 5;


    @RequestMapping(value = "/cabinet/files", method = RequestMethod.GET)
    public String files(@RequestParam(name = "page", required = false, defaultValue = "0") int page, Model model, Principal principal) {
        if (page < 1) {
            page = 1;
        }
        int prev = page;
        if (prev > 1) {
            prev--;
            model.addAttribute("prev", prev);
        }
        int next = page;
        next++;
        model.addAttribute("next", next);
        model.addAttribute("page", page);
        Users user = usersRepository.findByLogin(principal.getName());

        Pageable pageReq = PageRequest.of(page - 1, inPage, Sort.by("createDate").descending().and(Sort.by("name")));
        return "cabinet/files";
    }


    @RequestMapping(value = "/cabinet", method = RequestMethod.GET)
    public String index(@RequestParam(name = "page", required = false, defaultValue = "0") int page, Model model, Principal principal) {
        if (page < 1) {
            page = 1;
        }
        int prev = page;
        if (prev > 1) {
            prev--;
            model.addAttribute("prev", prev);
        }
        int next = page;
        next++;
        model.addAttribute("next", next);
        model.addAttribute("page", page);
        Users user = usersRepository.findByLogin(principal.getName());

        Pageable pageReq = PageRequest.of(page - 1, inPage, Sort.by("createDate").descending().and(Sort.by("name")));
        return "cabinet/index";
    }

    @RequestMapping(value = "/cabinet/links", method = RequestMethod.GET)
    public String links(@RequestParam(name = "page", required = false, defaultValue = "0") int page, Model model, Principal principal) {
        if (page < 1) {
            page = 1;
        }
        int prev = page;
        if (prev > 1) {
            prev--;
            model.addAttribute("prev", prev);
        }
        int next = page;
        next++;
        model.addAttribute("next", next);
        model.addAttribute("page", page);
        Users user = usersRepository.findByLogin(principal.getName());

        Pageable pageReq = PageRequest.of(page - 1, inPage, Sort.by("createTime").descending());
        return "cabinet/links";
    }


    @PostMapping("/cabinet/uploads")
    public String submit(
            @RequestParam MultipartFile[] files, Model model, Principal principal) {
        Users user = usersRepository.findByLogin(principal.getName());


        return "redirect:/cabinet/files";
    }

    ///  Optional попытаться получить объект  из кэша
///Если кэш вернул null, то вызвать метод findOneById у объекта-делегата

    @GetMapping("/cabinet/file/{uuid}")
    public ResponseEntity<Resource> getFile(@PathVariable String uuid) throws FileNotFoundException {


        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/link/{uuid}")
    public ResponseEntity<Resource> getLink(@PathVariable String uuid) throws FileNotFoundException {
        System.out.println(uuid);

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @GetMapping("/cabinet/link/{uuid}")
    public String createLink(@PathVariable String uuid, Model model, Principal principal, HttpServletRequest request) {
        Users user = usersRepository.findByLogin(principal.getName());

        return "redirect:/cabinet/files";
    }

    String getBaseUrl(HttpServletRequest req) {
        return req.getScheme() + "://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
    }


    @RequestMapping(value = "/cabinet/resetPassword", method = RequestMethod.GET)
    public String ressetPassword(Model model, Principal principal) {
        String login = principal.getName();
        PasswordForm form = new PasswordForm(login);
        model.addAttribute("passForm", form);
        return "/cabinet/resetPassword";
    }

    @RequestMapping(value = "/cabinet/resetPassword", method = RequestMethod.POST)
    public String ressetingPassword(Model model, //
                                    @ModelAttribute("passForm") @Validated PasswordForm passForm, //
                                    BindingResult result, //
                                    final RedirectAttributes redirectAttributes) {
        // Validate result
        if (result.hasErrors()) {
            return "/cabinet/resetPassword";
        }
        String error = usersService.resetPassword(passForm.getLogin(), passForm.getPassword());
        if (error != null && !error.isEmpty()) {
            model.addAttribute("errorMessage", error);
            return "resetPassword";
        }
        return "redirect:/logout";
    }


}
