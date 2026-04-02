package com.smarttoken.controller;

import com.smarttoken.entity.Token;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TokenController {

    @GetMapping("/book")
    public String bookPage(Model model) {
        model.addAttribute("token", new Token());
        return "book";
    }

    @PostMapping("/save")
    public String saveToken(@ModelAttribute Token token, Model model) {
        token.setTokenNumber(1); // temporary fixed token number
        model.addAttribute("tokenNumber", token.getTokenNumber());
        return "success";
    }

    @GetMapping("/delete/{id}")
    public String deleteToken(@PathVariable Long id) {
        return "redirect:/admin";
    }
}