package com.example.l07_20210535_gtics.controller;

import com.example.l07_20210535_gtics.entity.Roles;
import com.example.l07_20210535_gtics.entity.Users;
import com.example.l07_20210535_gtics.repository.RolRepository;
import com.example.l07_20210535_gtics.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LoginController {
    final UsersRepository usersRepository;
    final RolRepository rolRepository;

    public LoginController(UsersRepository usersRepository, RolRepository rolRepository) {
        this.usersRepository = usersRepository;
        this.rolRepository = rolRepository;
    }

    @GetMapping("/teatroPucpLogin")
    public String teatroPucpLogin() {

        return "login";
    }

    @GetMapping("/registroUsuario")
    public String registroUsuario() {
        return "registroUsuario";
    }

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/crearUsuario")
    public String crearUsuario(Model model, Users user) {
        String encryptedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encryptedPassword);
        Optional<Roles> optionalAZRol = rolRepository.findById(9);
        user.setRoles(optionalAZRol.get());
        user.setActivo(true);
        usersRepository.save(user);
        return "redirect:/teatroPucpLogin";
    }
}
