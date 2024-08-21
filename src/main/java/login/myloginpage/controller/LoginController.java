package login.myloginpage.controller;

import login.myloginpage.domain.login;
import login.myloginpage.services.loginServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class LoginController {
    @Autowired
    private loginServices userservice;

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("registerRequest",new login());
        return "register_page";
    }

    @GetMapping("/login")
    public ModelAndView login(){
        ModelAndView mav = new ModelAndView("login");
        mav.addObject("user", new login());
        return mav;
    }

    @PostMapping("/register")
    public String register(@ModelAttribute login usersModel){
        System.out.println("register request: " + usersModel);

        login registeredUser= loginServices.registerUser(usersModel.getUsername(), usersModel.getPassword());
        return registeredUser == null ? "error_page" : "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute("user") login user){
        login logs = new login();
        login oathUser = userservice.login(logs.getPassword(), logs.getUsername());

        if(Objects.nonNull(oathUser)){
            ModelAndView mav = new ModelAndView("login");
            mav.addObject("user", logs.getUsername());
            return "redirect:/";

        }else {
            ModelAndView mav = new ModelAndView("login");
            mav.addObject("user", logs.getUsername());
            return "home";
        }
    }



}
