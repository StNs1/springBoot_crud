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

    @PostMapping("admin/edit")
    public User editUserGet(@RequestBody User user) {
        user = userService.getUserById(user.getId());
        user.setRoles(userService.getRoles(1L, 2L));
        return user;
    }

    @PostMapping("admin/delete")
    public User deleteUserGet(@RequestBody User user) {
        user = userService.getUserById(user.getId());
        return user;
    }

    @DeleteMapping("admin/delete")
    public String deleteUser(@RequestBody User user) {
        userService.deleteUser(user.getId());
        Gson gson = new Gson();
        String json = gson.toJson(userService.listUsers());
        return json;
    }

    @PutMapping("admin/edit")
    public String editUserPUT(@RequestBody User user) {
        userService.save(user);
        Gson gson = new Gson();
        String json = gson.toJson(userService.listUsers());
        return json;
    }
}
