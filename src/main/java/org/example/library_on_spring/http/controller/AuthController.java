package org.example.library_on_spring.http.controller;

import jakarta.validation.Valid;
import jakarta.validation.groups.Default;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.library_on_spring.dto.AdminReadDto;
import org.example.library_on_spring.dto.RegistrationRequest;
import org.example.library_on_spring.service.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/web/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('SUPERUSER')")
    public ResponseEntity<List<AdminReadDto>> findAll() {
        List<AdminReadDto> admins = authService.findAll();
        return ResponseEntity.ok(admins);
    }
//    @PostMapping
//    public ResponseEntity<AdminReadDto> create(@RequestBody @Validated({Default.class}) RegistrationRequest registrationRequest) {
//        AdminReadDto createdAdmin = authService.create(registrationRequest);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createdAdmin);
//    }

    @PostMapping("/registration")
    @PreAuthorize("hasRole('SUPERUSER')")
    public String register(
            @Valid RegistrationRequest request,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes,
            @AuthenticationPrincipal UserDetails userDetails
    ) {
        if (bindingResult.hasErrors()) {
            bindingResult.getFieldErrors().forEach(error ->
                    log.error("Validation error: {}", error.getDefaultMessage()));
            redirectAttributes.addFlashAttribute("error", "Некорректные данные");
            return "redirect:/registration";
        }

        boolean isSuperuser = userDetails.getAuthorities().stream()
                .anyMatch(auth -> auth.getAuthority().equals("ROLE_SUPERUSER"));

        if (!isSuperuser) {
            redirectAttributes.addFlashAttribute("error", "Доступ запрещён");
            return "redirect:/web/auth/login";
        }

        authService.registerAdmin(request);
        redirectAttributes.addFlashAttribute("success", "Админ зарегистрирован");
        return "redirect:/registration";
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('SUPERUSER')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        authService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}