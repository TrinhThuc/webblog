package com.example.webblog.converter;

import com.example.webblog.dto.UserDTO;
import com.example.webblog.entity.RoleEntity;
import com.example.webblog.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserConverter {
    @Autowired
    ModelMapper modelMapper;

    public UserDTO toDto(UserEntity entity) {
        List<RoleEntity> roleEntityList = entity.getRoles();
        List<String> roles= new ArrayList<>();
        for(RoleEntity role : roleEntityList){
            roles.add(role.getCode());
        }
        UserDTO result = new UserDTO();
        result = modelMapper.map(entity, UserDTO.class);
        result.setRoleCodes(roles);
        return result;
    }

    public UserEntity toEntity(UserDTO dto) {
        UserEntity result = modelMapper.map(dto, UserEntity.class);
        return result;
    }

    public UserEntity toEntity(UserEntity entity, UserDTO dto){
        entity.setUserName(dto.getUserName());
        entity.setPassword(dto.getPassword());
        entity.setFullname(dto.getFullname());
        entity.setStatus(dto.getStatus());
        entity.setEmail(dto.getEmail());
        entity.setAvatar(dto.getAvatar());
        return entity;
    }

}
