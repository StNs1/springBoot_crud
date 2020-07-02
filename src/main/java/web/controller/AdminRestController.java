package web.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;


@RestController
@RequestMapping("/rest")
public class AdminRestController {

    private UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers() {
        Gson gson = new Gson();
        String json = gson.toJson(userService.listUsers());
        return json;
    }

    @PostMapping("admin/add")
    public User addUser(@RequestBody User user) {
        if (user.getEmail() != null && userService.getUserByEmail(user.getEmail()) == null) {
            userService.save(user);
        }
        return user;
    }
}
