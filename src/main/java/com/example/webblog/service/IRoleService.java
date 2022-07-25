package com.example.webblog.service;

import com.example.webblog.dto.RoleDTO;

import java.util.List;

public interface IRoleService {
    RoleDTO save(RoleDTO dto);
    void delete(long[] ids);
    List<RoleDTO> findAll();
}
