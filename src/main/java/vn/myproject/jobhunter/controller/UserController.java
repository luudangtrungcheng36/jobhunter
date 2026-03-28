package vn.myproject.jobhunter.controller;

import org.springframework.web.bind.annotation.RestController;

import vn.myproject.jobhunter.domain.User;
import vn.myproject.jobhunter.service.UserService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String getMethodName() {
        return "success save user";
    }

    @GetMapping("/users/{id}")
    public User getUserPage(@PathVariable("id") long id) {
        return userService.getUser(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user) {
        User createdUser = userService.handleSaveUser(user);
        return createdUser;
    }

    @PutMapping("/users/{id}")
    public User updateUser(@RequestBody User user) {
        User updateUser = userService.handleUpdateUser(user);
        return updateUser;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(id);
    }

}
