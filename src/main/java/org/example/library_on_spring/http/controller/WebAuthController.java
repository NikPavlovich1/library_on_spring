package org.example.library_on_spring.http.controller;

import lombok.RequiredArgsConstructor;
import org.example.library_on_spring.dto.RegisterAdminRequest;
import org.example.library_on_spring.service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/web/auth")
@RequiredArgsConstructor
public class WebAuthController {
    private final AuthService authService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/registration")
    public String registerPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String registerAdmin(@ModelAttribute RegisterAdminRequest request, RedirectAttributes redirectAttrs) {
        try {
            authService.registerAdmin(request);
            redirectAttrs.addFlashAttribute("success", "Регистрация успешна!");
            return "redirect:/web/auth/login";
        } catch (Exception e) {
            redirectAttrs.addFlashAttribute("error", "Ошибка: " + e.getMessage());
            return "redirect:/web/auth/registration";
        }
    }
}
