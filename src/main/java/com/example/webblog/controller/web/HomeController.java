package com.example.webblog.controller.web;

import com.example.webblog.dto.CategoryDTO;
import com.example.webblog.dto.MyUser;
import com.example.webblog.dto.PostDTO;
import com.example.webblog.dto.UserDTO;
import com.example.webblog.service.ICategoryService;
import com.example.webblog.service.IPostService;
import com.example.webblog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.Authentication;
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
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller(value = "homeontrollerOfWeb")
public class HomeController {

    @Autowired
    private IUserService userService;

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IPostService postService;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping(value = {"/trang-chu", "/", ""})
    public ModelAndView homePage(Principal principal) {
        Pageable pageable = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<PostDTO> listTrending = postService.findAll(pageable);
        List<PostDTO> newPost = postService.findAll(PageRequest.of(0,6,Sort.by(Sort.Direction.DESC, "createdDate")));
        List<PostDTO> listPopular = postService.findAll(pageable);
        ModelAndView mav = new ModelAndView("web/homepage");
        mav.addObject("listTrending", listTrending);
        mav.addObject("newPost", newPost);
        mav.addObject("listPopular", listPopular);
        if(principal != null) {
            MyUser user = (MyUser) ((Authentication) principal).getPrincipal();
            mav.addObject("userInf", user);
        }

        return mav;
    }

    @RequestMapping(value = "/dang-nhap", method = RequestMethod.GET)
    public ModelAndView loginPage() {
        ModelAndView mav = new ModelAndView("login");
        return mav;
    }

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

    @GetMapping("/bai-dang/viet-bai")
    public ModelAndView writingPage() {
        CategoryDTO categoryDTO = new CategoryDTO();
        categoryDTO.setListResult(categoryService.getAll());
        ModelAndView mav = new ModelAndView("web/writingpage");
        mav.addObject("Categories", categoryDTO.getListResult());
        return mav;

    }

    @GetMapping("/bai-dang/{id}")
    public ModelAndView PageDetail(@PathVariable(name= "id") Long id, Principal principal) {
        PostDTO post = postService.findById(id);
        ModelAndView mav = new ModelAndView("web/readingpage");
        mav.addObject("post", post);
        if(principal != null) {
            MyUser user = (MyUser) ((Authentication) principal).getPrincipal();
            mav.addObject("userInf", user);
        }
        return mav;

    }

    @GetMapping("/nguoi-dung/{username}")
    public ModelAndView personalPage(@PathVariable(name= "username") String username, Principal principal,
                                     @RequestParam(name = "tab", required = false) String tab) {
        ModelAndView mav = new ModelAndView("web/personal_page");
        if(principal != null) {
            MyUser user = (MyUser) ((Authentication) principal).getPrincipal();
            mav.addObject("userInf", user);
        }
        List<PostDTO> postDTOList = postService.findAllByAuthor(username);
        List<PostDTO> listIsActive = new ArrayList<>();
        List<PostDTO> listInActive = new ArrayList<>();
        for(PostDTO dto :postDTOList){
            if(dto.isActive())
                listIsActive.add(dto);
            else
                listInActive.add(dto);
        }
        String savP = "savedPost";
        if(savP.equals(tab))
            mav.addObject("listInActive", listInActive);
        else
            mav.addObject("listIsActive", listIsActive);
        return mav;
    }

    @GetMapping("/danh-sach/{category}")
    public ModelAndView listPost(@PathVariable(name = "category") String category ,Principal principal,
                                 @RequestParam("page") int page,
                                 @RequestParam("limit") int limit) {
        PostDTO model = new PostDTO();
        model.setPage(page);
        model.setLimit(limit);
        Pageable pageable = PageRequest.of(page-1, limit, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<PostDTO> dtos = postService.findAllByCategory(category, pageable);
        Integer i= (page-1)*limit +1;
        for(PostDTO postDTO : dtos){
            postDTO.setStt(i);
            i++;
        }
        model.setListResult(dtos);
        model.setTotalItem(postService.getTotalItemWithCategory_Name(category));
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
        ModelAndView mav = new ModelAndView("web/list_page");
        if(principal != null) {
            MyUser user = (MyUser) ((Authentication) principal).getPrincipal();
            mav.addObject("userInf", user);
        }
        return mav;

    }

}
