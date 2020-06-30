package web.controller;

import com.google.gson.Gson;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
