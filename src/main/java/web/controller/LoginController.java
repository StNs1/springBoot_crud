package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import web.model.User;
import web.service.UserService;

@Controller
public class LoginController {

    private UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLogin(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "login";
    }

    @GetMapping("/user")
    public String printWelcome(@SessionAttribute("user") User user, Model model) {
        model.addAttribute("user", user);
        return "user";
    }
}
