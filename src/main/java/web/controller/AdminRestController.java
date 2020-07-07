package web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/rest")
public class AdminRestController {

    private UserService userService;

    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        return ResponseEntity.ok(userService.listUsers());
    }

    @PostMapping("admin/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        if (user.getEmail() != null && userService.getUserByEmail(user.getEmail()) == null) {
            return ResponseEntity.ok(userService.save(user));
        }
        return (ResponseEntity<User>) ResponseEntity.badRequest();
    }

    @PostMapping("admin/edit")
    public ResponseEntity<User> editUserGet(@RequestBody User user) {
        user = userService.getUserById(user.getId());
        user.setRoles(userService.getRoles(1L, 2L));
        return ResponseEntity.ok(user);
    }

    @PostMapping("admin/delete")
    public ResponseEntity<User> deleteUserGet(@RequestBody User user) {
        user = userService.getUserById(user.getId());
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("admin/delete")
    public ResponseEntity<List<User>> deleteUser(@RequestBody User user) {
        userService.deleteUser(user.getId());
        return ResponseEntity.ok(userService.listUsers());
    }

    @PutMapping("admin/edit")
    public ResponseEntity<List<User>> editUserPUT(@RequestBody User user) {
        userService.save(user);
        return ResponseEntity.ok(userService.listUsers());
    }
}
