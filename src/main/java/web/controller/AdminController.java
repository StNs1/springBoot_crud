package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class AdminController {

    private UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/admin")
    public String printUsers(@SessionAttribute("user") User user, ModelMap model) {
        User newUser = new User();
        newUser.setRoles(userService.getRoles(1L, 2L));
        model.addAttribute("newUser", newUser);
        List<User> list = userService.listUsers();
        model.addAttribute("list", list);
        model.addAttribute("currentUser", user);
        return "users";
    }

    @GetMapping("/admin/add")
    public String addUser(Model model) {
        User user = new User();
        user.setRoles(userService.getRoles(1L, 2L));
        model.addAttribute(user);
        return "add";
    }

    @PostMapping("/admin/add")
    public String addUser(@ModelAttribute("user") User user) {
        if (userService.getUserByEmail(user.getEmail()) == null) {
            userService.save(user);
        }
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete")
    public String deleteUser(User user) {
        userService.deleteUser(user.getId());
        return "redirect:/admin";
    }

    @GetMapping("/admin/edit")
    public String editUser(@RequestParam long id, Model model) {
        User user = userService.getUserById(id);
        user.setRoles(userService.getRoles(1L, 2L));
        model.addAttribute("user", user);
        return "edit";
    }

    @PostMapping("/admin/edit")
    public String editUser(User user) {
        userService.save(user);
        return "redirect:/admin";
    }
}
