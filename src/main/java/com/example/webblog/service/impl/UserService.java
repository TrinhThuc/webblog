package com.example.webblog.service.impl;

import com.example.webblog.converter.UserConverter;
import com.example.webblog.dto.PostDTO;
import com.example.webblog.dto.UserDTO;
import com.example.webblog.entity.CategoryEntity;
import com.example.webblog.entity.PostEntity;
import com.example.webblog.entity.RoleEntity;
import com.example.webblog.entity.UserEntity;
import com.example.webblog.respository.CategoryRepository;
import com.example.webblog.respository.RoleRepository;
import com.example.webblog.respository.UserRepository;
import com.example.webblog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserConverter userConverter;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDTO save(UserDTO dto) {
        List<RoleEntity> entities = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        if (dto.getId() != null) {
            for (String code : dto.getRoleCodes()) {
                entities.add(roleRepository.findRoleEntityByCode(code));
            }
            UserEntity oldUser = userRepository.getById(dto.getId());
            oldUser.setRoles(entities);
            userEntity = userConverter.toEntity(oldUser, dto);
        } else {
            userEntity = userConverter.toEntity(dto);
            entities.add(roleRepository.findRoleEntityByCode("ROLE_USER"));
            userEntity.setStatus(1);
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
        List<UserDTO> models= new ArrayList<>();
        List<UserEntity> entities = userRepository.findAll(pageable).getContent();
        for(UserEntity user : entities){
            UserDTO userDTO = userConverter.toDto(user);
            models.add(userDTO);
        }
        return  models;
    }

    @Override
    public int getTotalItem() {
        return  (int)userRepository.count();
    }

    @Override
    public UserDTO findByUserName(String name) {
        UserEntity user = userRepository.findOneByUserNameAndStatus(name, 1);
        if(user==null){
            return null;
        }
        return userConverter.toDto(user);
    }
}
