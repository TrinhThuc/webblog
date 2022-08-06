package com.example.webblog.api.web;

import com.example.webblog.dto.PostDTO;
import com.example.webblog.dto.UserDTO;
import com.example.webblog.service.IPostService;
import com.example.webblog.service.IUserService;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@RestController(value = "ApiOfUser")
public class UserAPI {
    @Autowired
    private IUserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PutMapping("api/user")
    public UserDTO updateUser(@RequestBody UserDTO userDTO, HttpServletRequest request,final RedirectAttributes redirectAttributes){
        UserDTO dto = userService.findByUserName(userDTO.getUserName());
        Boolean check = passwordEncoder.matches(userDTO.getPassword(), dto.getPassword());
        if(check){
            userDTO.setId(dto.getId());
            userDTO.setPassword(userDTO.getNewPassword());
            return  userService.save(userDTO);
        }
        return null;
    }

}
