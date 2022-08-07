package com.example.webblog.controller.admin;

import com.example.webblog.dto.CategoryDTO;
import com.example.webblog.dto.PostDTO;
import com.example.webblog.dto.RoleDTO;
import com.example.webblog.dto.UserDTO;
import com.example.webblog.entity.RoleEntity;
import com.example.webblog.service.ICategoryService;
import com.example.webblog.service.IPostService;
import com.example.webblog.service.IRoleService;
import com.example.webblog.service.IUserService;
import com.example.webblog.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller(value = "homecontrollerOfAdmin")
public class HomeController {

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @Autowired
    private IPostService postService;

    @Autowired
    private ICategoryService categoryService;

    @RequestMapping(value = "/quan-tri/trang-chu", method = RequestMethod.GET)
    public ModelAndView homePage() {
        ModelAndView mav = new ModelAndView("admin/home");
        return mav;
    }

    @RequestMapping(value = "/quan-tri/trang-chu/nguoi-dung", method = RequestMethod.GET)
    public ModelAndView listUser(@RequestParam("page") int page,
                                 @RequestParam("limit") int limit) {
        UserDTO model = new UserDTO();
        model.setPage(page);
        model.setLimit(limit);
        ModelAndView mav = new ModelAndView("admin/user_manage");
        Pageable pageable = PageRequest.of(page-1,limit);
        List<UserDTO> ListResult = userService.findAll(pageable);
        Integer i= (page-1)*limit +1;
        for(UserDTO userDTO : ListResult){
            userDTO.setStt(i);
            i++;
        }
        model.setListResult(ListResult);
        model.setTotalItem(userService.getTotalItem());
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
        List<RoleDTO> roles = roleService.findAll();
        mav.addObject("roles",roles);
        mav.addObject("model",model);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/role", method = RequestMethod.GET)
    public ModelAndView listRole() {
        List<RoleDTO> roles = roleService.findAll();
        ModelAndView mav = new ModelAndView("admin/role_manage");
        mav.addObject("roles",roles);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/bai-viet", method = RequestMethod.GET)
    public ModelAndView listPost(@RequestParam("page") int page,
                                 @RequestParam("limit") int limit) {
        PostDTO model = new PostDTO();
        model.setPage(page);
        model.setLimit(limit);
        Pageable pageable = PageRequest.of(page-1,limit, Sort.by(Sort.Direction.DESC, "createdDate"));
        List<PostDTO> Posts = postService.findAll(pageable);
        Integer i= (page-1)*limit +1;
        for(PostDTO postDTO : Posts){
            postDTO.setStt(i);
            i++;
        }
        model.setListResult(Posts);
        model.setTotalItem(postService.getTotalItem());
        model.setTotalPage((int) Math.ceil((double) model.getTotalItem() / model.getLimit()));
        ModelAndView mav = new ModelAndView("admin/posts-manage");
        mav.addObject("model", model);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/the-loai", method = RequestMethod.GET)
    public ModelAndView listCategory() {
        List<CategoryDTO> listCategory = categoryService.getAll();
        ModelAndView mav = new ModelAndView("admin/category_manage");
        mav.addObject("categoris", listCategory);
        return mav;
    }

    @RequestMapping(value = "/quan-tri/bai-viet/{id}", method = RequestMethod.GET)
    public ModelAndView postDetail(@PathVariable(name = "id")Long id ) {
        PostDTO postDTO = postService.findById(id);
        UserDTO author = userService.findByUserName(postDTO.getCreatedBy());
        ModelAndView mav = new ModelAndView("admin/write_manage");
        mav.addObject("post", postDTO);
        mav.addObject("author", author);
        return mav;
    }



}
