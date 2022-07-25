package com.example.webblog.service.impl;

import com.example.webblog.converter.RoleConverter;
import com.example.webblog.dto.RoleDTO;
import com.example.webblog.entity.RoleEntity;
import com.example.webblog.respository.RoleRepository;
import com.example.webblog.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RoleService implements IRoleService {
    @Autowired
    RoleRepository roleRepository;

    @Autowired
    RoleConverter roleConverter;

    @Override
    @Transactional
    public RoleDTO save(RoleDTO dto) {
        RoleEntity role = new RoleEntity();
        if(dto.getId() != null){
            RoleEntity oleRole = roleRepository.getById(dto.getId());
            role = roleConverter.toEntity(dto, oleRole);
        }else
            role = roleConverter.toEntity(dto);
        return roleConverter.toDto(roleRepository.save(role));
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for(long id : ids){
            roleRepository.deleteById(id);
        }
    }

    @Override
    @Transactional
    public List<RoleDTO> findAll() {
        List<RoleDTO> roleDTOS = new ArrayList<>();
        for(RoleEntity role : roleRepository.findAll())
            roleDTOS.add(roleConverter.toDto(role));
        return roleDTOS;
    }
}
