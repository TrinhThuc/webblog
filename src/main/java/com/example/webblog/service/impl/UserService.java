package com.example.webblog.service.impl;

import com.example.webblog.converter.PostConverter;
import com.example.webblog.converter.UserConverter;
import com.example.webblog.dto.PostDTO;
import com.example.webblog.dto.UserDTO;
import com.example.webblog.entity.CategoryEntity;
import com.example.webblog.entity.PostEntity;
import com.example.webblog.entity.RoleEntity;
import com.example.webblog.entity.UserEntity;
import com.example.webblog.respository.CategoryRepository;
import com.example.webblog.respository.PostRepository;
import com.example.webblog.respository.RoleRepository;
import com.example.webblog.respository.UserRepository;
import com.example.webblog.service.IUserService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PostRepository postRepository;

    @Autowired
    PostConverter postConverter;

    @Autowired
    UserConverter userConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JavaMailSender mailSender;

    @Override
    @Transactional
    public UserDTO save(UserDTO dto, String siteURL) throws UnsupportedEncodingException, MessagingException {
        List<RoleEntity> entities = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        String randomCode = RandomString.make(64);
        dto.setVerificationCode(randomCode);
        dto.setEnabled(false);
        userEntity = userConverter.toEntity(dto);
        entities.add(roleRepository.findRoleEntityByCode("ROLE_USER"));
        userEntity.setStatus(1);
        userEntity.setRoles(entities);
        sendVerificationEmail(dto, siteURL);
        return userConverter.toDto(userRepository.save(userEntity));
    }

    @Override
    @Transactional
    public UserDTO save(UserDTO dto) {
        UserEntity userEntity = new UserEntity();
        List<RoleEntity> entities = new ArrayList<>();
        if (dto.getPassword() != null) {
            dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        if (dto.getRoleIds() != null) {
            for (Long roleId : dto.getRoleIds())
                entities.add(roleRepository.findRoleEntityById(roleId));
        }
        if(dto.getId() != null){
            UserEntity oldUser = userRepository.findOneById(dto.getId());
            if(entities != null)
            oldUser.setRoles(entities);
            userEntity = userConverter.toEntity(oldUser, dto);
        }else{
            userEntity = userConverter.toEntity(dto);
            userEntity.setRoles(entities);
        }

        return userConverter.toDto(userRepository.save(userEntity));
    }


    @Override
    @Transactional
    public void delete(long[] ids) {
        UserEntity userEntity = new UserEntity();
        for (long id : ids) {
            userEntity = userRepository.getById(id);
            userEntity.setStatus(0);
            userRepository.save(userEntity);
        }
    }

    @Override
    public List<UserDTO> findAll(Pageable pageable) {
        List<UserDTO> models = new ArrayList<>();
        List<UserEntity> entities = userRepository.findAll(pageable).getContent();
        for (UserEntity user : entities) {
            UserDTO userDTO = userConverter.toDto(user);
            List<PostEntity> posts = postRepository.findAllByCreatedBy(user.getUserName());
            userDTO.setPostEntities(posts);
            models.add(userDTO);
        }

        return models;
    }

    @Override
    public int getTotalItem() {
        return (int) userRepository.count();
    }

    @Override
    public UserDTO findByUserName(String name) {
        UserEntity user = userRepository.findOneByUserNameAndStatusAndEnabled(name, 1, true);
        if (user == null) {
            return null;
        }
        return userConverter.toDto(user);
    }

    @Override
    public UserDTO findByEmail(String email) {
        UserEntity user = userRepository.findByEmailAndStatus(email, 1);
        if (user == null) {
            return null;
        }
        return userConverter.toDto(user);
    }


    @Override
    public void sendVerificationEmail(UserDTO user, String siteURL)
            throws MessagingException, UnsupportedEncodingException {
        String toAddress = user.getEmail();
        String fromAddress = "trinhthuc432@gmail.com";
        String senderName = "MeooBlog";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", user.getFullname());
        String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);

        helper.setText(content, true);

        mailSender.send(message);

    }

    @Override
    public boolean verify(String verificationCode) {
        UserEntity user = userRepository.findByVerificationCode(verificationCode);
        if (user == null || user.isEnabled()) {
            return false;
        } else {
            user.setVerificationCode(null);
            user.setEnabled(true);
            userRepository.save(user);
            return true;
        }
    }


}
