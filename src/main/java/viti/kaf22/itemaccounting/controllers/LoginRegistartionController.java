package viti.kaf22.itemaccounting.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import viti.kaf22.itemaccounting.models.User;
import viti.kaf22.itemaccounting.repositories.impl.DatabaseUserServiceImpl;

import javax.validation.Valid;

@Controller

public class LoginRegistartionController {
    @Autowired
    ApplicationEventPublisher eventPublisher;
    @Autowired
    DatabaseUserServiceImpl userService;


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView registerUserAccount(

            @ModelAttribute("user") @Valid User accountDto,
            BindingResult result,
            WebRequest request,
            Errors errors) {

        User registered = new User();
        if (!result.hasErrors()) {
            registered = createUserAccount(accountDto, result);
        }
        if (registered == null) {
            result.rejectValue("email", "message.regError");
        }
        if (result.hasErrors()) {
            return new ModelAndView("registration", "user", accountDto);
        }
        else {
            return new ModelAndView("login.html", "register", true);
        }
    }



    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registerPage(Model model) {

        model.addAttribute("user", new User());
        return "registration";
    }

    // Login form with error
    @RequestMapping("/login-error.html")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login.html";
    }
    @RequestMapping("/login.html")
    public String login(Model model) {
        return "login.html";
    }

    @ExceptionHandler(Throwable.class)
    public String exception(final Throwable throwable, final Model model) {
        String errorMessage = (throwable != null ? throwable.getMessage() : "Unknown error");
        model.addAttribute("errorMessage", errorMessage);
        return "error";
    }

    @RequestMapping("/settings")
    public String getSettingsPage(Model model) {
        if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) model.addAttribute("user", userService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName()));
        return "settings";
    }


    private User createUserAccount(User accountDto, BindingResult result) {
        User registered = null;

        try {
            registered = userService.registerNewUserAccount(accountDto);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return registered;
    }
}


