package com.example.webblog.service;

import com.example.webblog.dto.PostDTO;
import com.example.webblog.dto.UserDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IUserService {
    UserDTO save(UserDTO dto);
    void delete(long[] ids);
    List<UserDTO> findAll(Pageable pageable);
    int getTotalItem();
    UserDTO findByUserName(String name);
}
