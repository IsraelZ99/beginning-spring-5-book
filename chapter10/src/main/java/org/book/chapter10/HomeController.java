package org.book.chapter10;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard";
    }

}
