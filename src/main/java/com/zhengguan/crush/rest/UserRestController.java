package com.zhengguan.crush.rest;

import com.zhengguan.crush.entity.User;
import com.zhengguan.crush.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserRestController {

    @Autowired
    private UserService userService;

    // get all users
    @GetMapping("")
    public List<User> getUsers() {
        System.out.println("yes");
        return userService.findAll();
    }

    // get user with userID
    @GetMapping("/{userId}")
    public User getUser(@PathVariable int userId) {
        // check if th user id is valid
        User user = userService.findById(userId);
        if (user == null) {
            throw new UserNotFoundException("User ID not found - " + userId);
        }

        return userService.findById(userId);
    }

    // add a new user, return the update user object
    @PostMapping("/")
    public User saveUser(@RequestBody User user) {
        System.out.println("Yes");
        user.setId(0); // means null, so insert as a new user
        return userService.saveUser(user);
    }

    // update user
    @PutMapping("")
    public void updateUser(@RequestBody User user) {
        // check if the user exists by checking id
        User theUser = userService.findById(user.getId());
        if (theUser == null) {
            throw new UserNotFoundException("User not found");
        }

        userService.update(user);
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable int userId) {
        // check if th user id is valid
        User user = userService.findById(userId);
        if (user == null) {
            throw new UserNotFoundException("User ID not found - " + userId);
        }

        userService.deleteById(userId);
        return "Delete user with user id - " + userId;
    }

}