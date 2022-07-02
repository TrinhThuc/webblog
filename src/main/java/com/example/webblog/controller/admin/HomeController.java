package com.example.webblog.controller.admin;

import com.example.webblog.dto.UserDTO;
import com.example.webblog.entity.RoleEntity;
import com.example.webblog.service.IUserService;
import com.example.webblog.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
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
        ModelAndView mav = new ModelAndView("admin/admin");
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
        mav.addObject("model",model);
        return mav;
    }
}
