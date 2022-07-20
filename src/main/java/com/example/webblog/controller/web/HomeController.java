package com.example.webblog.controller.web;

import com.example.webblog.dto.CategoryDTO;
import com.example.webblog.dto.UserDTO;
import com.example.webblog.service.ICategoryService;
import com.example.webblog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;

@Controller(value = "homeontrollerOfWeb")
public class HomeController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICategoryService categoryService;

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
    public String signUp(@ModelAttribute UserDTO user,Model model,final RedirectAttributes redirectAttributes,
                         HttpServletRequest request) throws MessagingException, UnsupportedEncodingException {
        UserDTO dto = userService.findByUserName(user.getUserName());
        if(dto!=null){
            String UserNameInvalid = "tên đăng nhập đã tồn tại";
            redirectAttributes.addFlashAttribute("UserNameInvalid", UserNameInvalid);
            return "redirect:/dang-ki?errol";
        }
//        if(userService.findByEmail(user.getEmail()) != null){
//            String EmailInvalid = "email đã tồn tại";
//            redirectAttributes.addFlashAttribute("EmailInvalid", EmailInvalid);
//            return "redirect:/dang-ki?errol";
//        }
        userService.save(user, getSiteURL(request));
        model.addAttribute("user", user);
        return "register_success";
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }

    @GetMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (userService.verify(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    @GetMapping("bai-dang/viet-bai")
    public ModelAndView writingPage() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setListResult(categoryService.getAll());
        ModelAndView mav = new ModelAndView("web/writingpage");
        mav.addObject("Categories", categoryDTO.getListResult());
        return mav;

    }

}
