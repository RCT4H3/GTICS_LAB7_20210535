package com.example.l07_20210535_gtics.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClienteController {
    @GetMapping("/Cliente")
    public String index() {
        return "homeCliente";
    }
}