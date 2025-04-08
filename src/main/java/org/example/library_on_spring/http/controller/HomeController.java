package org.example.library_on_spring.http.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web/home")
public class HomeController {

    @GetMapping/*("/web/home")*/
    public String home() {
        return "home";
    }
}
