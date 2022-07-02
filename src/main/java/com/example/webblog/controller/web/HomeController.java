package com.example.webblog.controller.web;

import com.example.webblog.dto.UserDTO;
import com.example.webblog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller(value = "homeontrollerOfWeb")
public class HomeController {

    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = {"/trang-chu", "/", ""})
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("web/homepage");
        return mav;
    }

    @RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

//    @RequestMapping(value = "/dang-ki", method = RequestMethod.GET)
//    public ModelAndView signUpPage( ) {
//        ModelAndView mav = new ModelAndView("registration");
//        return mav;
//    }
//
//    @RequestMapping(value = "/dang-ki", method = RequestMethod.POST)
//    public ModelAndView signUpPage(@ModelAttribute UserDTO user ) {
//        UserDTO dto = userService.findByUserName(user.getUserName());
//        if(dto != null){
//            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
//            userService.save(dto);
//        }
//        ModelAndView mav = new ModelAndView("redirect:/dang-ki?success");
//        mav.addObject("user", user);
//        return mav;
//    }
    @GetMapping("/dang-ki")
    public String signUp(Model model){
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping("/dang-ki")
    public String signUp(@ModelAttribute UserDTO user,Model model,final RedirectAttributes redirectAttributes){
        UserDTO dto = userService.findByUserName(user.getUserName());
        if(dto!=null){
            String message = "tên đăng nhập đã tồn tại";
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/dang-ki?errol";
        }
        userService.save(user);
        model.addAttribute("user", user);
        return "redirect:/dang-ki?success";
    }

}
