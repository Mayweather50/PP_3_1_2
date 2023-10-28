package springboot.Project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.Project.model.User;
import springboot.Project.service.UserService;


import java.util.List;


@Controller
public class UserController {

    @Autowired
   private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/index")
    public String getAllUsers(Model model) {
        List<User> result = userService.getAllUsers();
        model.addAttribute("users",result);
        return "index";
    }

    @GetMapping("/edit")
    public String edit(@RequestParam(value = "id", required = true) Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "edit";
    }



    @GetMapping("/new")
    public String newUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "new";
    }


    @PostMapping("/create")
    public String createUser(@ModelAttribute("user")User user) {
        userService.addUser(user);
        return "redirect:/index";


    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") User user, @RequestParam("id") Long id) {
        userService.updateUser(user, id);
        return "redirect:/index";
    }


    @DeleteMapping("/delete")
    public String deleteUser(@RequestParam("id") Long id) {
        userService.removeUser(id);
        return "redirect:/index";
    }




}
