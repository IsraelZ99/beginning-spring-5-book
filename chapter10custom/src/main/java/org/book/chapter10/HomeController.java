package org.book.chapter10;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping(value = {"/login", "/"})
    public String login() {
        return "login";
    }

    @GetMapping("/default")
    public String defaultAfterLogin(HttpServletRequest request) {
        if (request.isUserInRole("ADMIN")) {
            return "redirect:/dashboard_admin";
        } else {
            return "redirect:/dashboard_user";
        }
    }

    @GetMapping("/dashboard_user")
    public String dashboardUser() {
        return "dashboard_user";
    }

    @GetMapping("/dashboard_admin")
    public String dashboardAdmin() {
        return "dashboard_admin";
    }

}
