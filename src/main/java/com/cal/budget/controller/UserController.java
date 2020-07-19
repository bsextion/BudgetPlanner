//package com.cal.budget.controller;
//
//import com.cal.budget.dao.User;
//import com.cal.budget.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Optional;
//
//@RestController
//@RequestMapping(value = "/user")
//public class UserController {
//    private final UserService userService;
//    public UserController(@Autowired UserService userService) {
//        this.userService = userService;
//    }
//
//    @RequestMapping(value = "/", produces = "application/json", method = RequestMethod.GET)
//    public Iterable<User> getAllUsers() {
//        return userService.getUsers();
//    }
//
//    @RequestMapping(value = "/{userId}", produces = "application/json", method = RequestMethod.GET)
//    public User getByUserId(@PathVariable("userId") String userId) {
//        return userService.getUser(userId);
//    }
//    @RequestMapping(value = "/add", produces = "application/json", method = RequestMethod.POST)
//    public User createUser(@RequestBody User user) {
//         userService.addUser(user);return user;
//    }
//
//    @RequestMapping(value = "/{userId}", produces = "application/json", method = RequestMethod.PUT)
//    public User updateUser(@RequestBody User user, @PathVariable("userId") String userId) {
//        user.setId(userId);
//       userService.addUser(user);
//       return user;
//    }
//
//    @RequestMapping(value = "/{userId}", produces = "application/json", method = RequestMethod.DELETE)
//    public String updateUser(@PathVariable("userId") String userId) {
//        userService.removeUser(userId);
//        return userId;
//    }
//
//}
