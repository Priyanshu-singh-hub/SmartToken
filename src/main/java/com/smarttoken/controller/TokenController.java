package com.smarttoken.controller;

import com.smarttoken.entity.Token;
import com.smarttoken.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class TokenController {

    @Autowired
    private TokenRepository tokenRepository;

    @GetMapping("/book")
    public String bookPage(Model model) {
        model.addAttribute("token", new Token());
        return "book";
    }

    @PostMapping("/save")
    public String saveToken(@ModelAttribute Token token, Model model) {
        Long count = tokenRepository.count();
        token.setTokenNumber((int) (count + 1));

        tokenRepository.save(token);

        model.addAttribute("tokenNumber", token.getTokenNumber());

        return "success";
    }

    @GetMapping("/delete/{id}")
    public String deleteToken(@PathVariable Long id) {
        tokenRepository.deleteById(id);
        return "redirect:/admin";
    }
}