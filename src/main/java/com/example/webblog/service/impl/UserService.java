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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Override
    @Transactional
    public UserDTO save(UserDTO dto) {
        List<RoleEntity> entities = new ArrayList<>();
        UserEntity userEntity = new UserEntity();
        for(String code : dto.getRoleCodes()){
            entities.add(roleRepository.findRoleEntityByCode(code));
        }
        if(dto.getId() != null){
            UserEntity oldUser = userRepository.getById(dto.getId());
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
        for(long id : ids){
            userEntity = userRepository.getById(id);
            userEntity.setStatus(0);
            userRepository.save(userEntity);
        }
    }
}
