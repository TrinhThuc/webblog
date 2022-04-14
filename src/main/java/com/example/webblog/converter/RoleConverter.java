package com.example.webblog.converter;

import com.example.webblog.dto.RoleDTO;
import com.example.webblog.entity.RoleEntity;
import com.example.webblog.entity.UserEntity;
import com.example.webblog.respository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleConverter {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    public RoleDTO toDto(RoleEntity entity) {
        RoleDTO result = modelMapper.map(entity, RoleDTO.class);
        return result;
    }

    public RoleEntity toEntity(RoleDTO dto) {
        RoleEntity result = new RoleEntity();
        result = modelMapper.map(dto, RoleEntity.class);
        return result;
    }

    public RoleEntity toEntity(RoleDTO dto, RoleEntity entity){
        entity.setCode(dto.getCode());
        entity.setName(dto.getName());
        return entity;
    }
}
