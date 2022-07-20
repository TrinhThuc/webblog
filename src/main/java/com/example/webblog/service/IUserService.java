package com.example.webblog.service;

import com.example.webblog.dto.PostDTO;
import com.example.webblog.dto.UserDTO;
import com.example.webblog.entity.UserEntity;
import org.springframework.data.domain.Pageable;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public interface IUserService {
    UserDTO save(UserDTO dto, String siteURL) throws UnsupportedEncodingException, MessagingException ;
    void delete(long[] ids);
    List<UserDTO> findAll(Pageable pageable);
    int getTotalItem();
    UserDTO findByUserName(String name);
    UserDTO findByEmail(String email);
    void sendVerificationEmail(UserDTO user, String siteURL) throws MessagingException, UnsupportedEncodingException;
    boolean verify(String verificationCode);
}
