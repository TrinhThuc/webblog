package com.example.webblog.api.admin;

import com.example.webblog.dto.PostDTO;
import com.example.webblog.dto.UserDTO;
import com.example.webblog.service.IPostService;
import com.example.webblog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserAPI {
    @Autowired
    private IUserService userService;

    @PostMapping("api/user")
    public UserDTO createPost(@RequestBody UserDTO userDTO){
        return userService.save(userDTO);
    }
//
    @PutMapping("api/user/{id}")
    public UserDTO updatePost(@RequestBody UserDTO userDTO, @PathVariable("id")long id, HttpServletRequest request){
        userDTO.setId(id);
        return userService.save(userDTO);
    }

    @DeleteMapping("api/user")
    public void deletePost(@RequestBody long[] ids) {
        userService.delete(ids);
    }
}
