package com.example.webblog.service;

import com.example.webblog.dto.PostDTO;
import com.example.webblog.dto.UserDTO;

public interface IUserService {
    UserDTO save(UserDTO dto);
    void delete(long[] ids);
}
