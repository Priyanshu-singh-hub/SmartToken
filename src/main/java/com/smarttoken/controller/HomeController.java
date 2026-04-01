package com.smarttoken.controller;

import com.smarttoken.entity.Token;
import com.smarttoken.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

    @Autowired
    private TokenRepository tokenRepository;

    @GetMapping("/")
    public String homePage() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username,
                              @RequestParam String password,
                              Model model) {

        if ("admin".equals(username) && "admin123".equals(password)) {
            return "redirect:/admin";
        } else {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }
    }

    @PostMapping("/bookToken")
    public String bookToken(@RequestParam String name,
                            @RequestParam String mobile,
                            Model model) {

        Token token = new Token();
        token.setName(name);
        token.setMobile(mobile);

        Long count = tokenRepository.count();
        token.setTokenNumber((int) (count + 1));

        tokenRepository.save(token);

        model.addAttribute("message", "Token Booked Successfully!");
        model.addAttribute("tokenId", token.getId());

        return "success";
    }

    @GetMapping("/admin")
    public String adminPage(Model model) {
        model.addAttribute("tokens", tokenRepository.findAll());
        return "admin";
    }

    @GetMapping("/edit/{id}")
    public String editToken(@PathVariable Long id, Model model) {
        Token token = tokenRepository.findById(id).orElse(null);
        model.addAttribute("token", token);
        return "edit";
    }

    @PostMapping("/updateToken")
    public String updateToken(@RequestParam Long id,
                              @RequestParam String name,
                              @RequestParam String mobile,
                              @RequestParam Integer tokenNumber) {

        Token token = tokenRepository.findById(id).orElse(null);

        if (token != null) {
            token.setName(name);
            token.setMobile(mobile);
            token.setTokenNumber(tokenNumber);

            tokenRepository.save(token);
        }

        return "redirect:/admin";
    }
}