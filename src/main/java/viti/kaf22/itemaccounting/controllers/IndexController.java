package viti.kaf22.itemaccounting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import viti.kaf22.itemaccounting.models.User;
import viti.kaf22.itemaccounting.repositories.impl.DatabaseUserServiceImpl;

import javax.transaction.Transactional;
import java.util.Map;

@Controller

public class IndexController {
    @Autowired
    private DatabaseUserServiceImpl userService;
    @Transactional
    @RequestMapping(value = {"/","/dashboard"})
    public String welcome(Map<String, Object> model) {
        if (SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
            System.out.println(userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()).getRole());
        User user = userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        model.put("user", user);
        return "dashboard";
    }
    @Transactional
    @RequestMapping("/table")
    public String tableRequest(Map<String, Object> model) {
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) model.put("user", userService.getByEmail(  SecurityContextHolder.getContext().getAuthentication().getName()));
        return "table";
    }
    @Transactional
    @RequestMapping("/create")
    public String iconsRequest(Map<String, Object> model) {
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) model.put("user", userService.getByEmail(  SecurityContextHolder.getContext().getAuthentication().getName()));
        return "create";
    }
    @Transactional
    @RequestMapping("/newobject")
    public String newObject(Map<String, Object> model) {
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) model.put("user", userService.getByEmail(  SecurityContextHolder.getContext().getAuthentication().getName()));
        return "newobject";
    }
    @Transactional
    @RequestMapping("/all")
    public String allRequest(Map<String, Object> model) {
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) model.put("user", userService.getByEmail(  SecurityContextHolder.getContext().getAuthentication().getName()));
        return "start";
    }
    @Transactional
    @RequestMapping("/user")
    public String userRequest(Map<String, Object> model) {
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) model.put("user", userService.getByEmail(  SecurityContextHolder.getContext().getAuthentication().getName()));
        return "user";
    }
    

}
