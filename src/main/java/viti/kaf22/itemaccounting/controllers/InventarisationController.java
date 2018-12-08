package viti.kaf22.itemaccounting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import viti.kaf22.itemaccounting.repositories.impl.DatabaseUserServiceImpl;

import javax.transaction.Transactional;

@Controller
@Transactional

public class InventarisationController {
    @Autowired
    DatabaseUserServiceImpl userService;


    @RequestMapping(value = "/start", method = {RequestMethod.GET})
    public String startMethod(Model model){

        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated())
            model.addAttribute("user", userService.getByEmail(  SecurityContextHolder.getContext().getAuthentication().getName()));


        return "start";

    }



}
