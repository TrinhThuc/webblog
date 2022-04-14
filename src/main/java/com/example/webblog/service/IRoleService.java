package com.example.webblog.service;

import com.example.webblog.dto.RoleDTO;

public interface IRoleService {
    RoleDTO save(RoleDTO dto);
    void delete(long[] ids);
}
