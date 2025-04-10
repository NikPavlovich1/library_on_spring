package org.example.library_on_spring.http.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/web/home")
public class HomeController {

    @GetMapping
    public String home(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        model.addAttribute("isSuperuser", userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_SUPERUSER")));
        return "home";
    }
}
