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
    public void addUser(@RequestBody String user) {
        User userFromJson = getUser(user);
        userService.save(userFromJson);
    }

    private User getUser(String user) {
        Gson gson = new Gson();
        User userFromJson = gson.fromJson(user, User.class);
        return userFromJson;
    }
}
