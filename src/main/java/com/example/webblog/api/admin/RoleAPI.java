package com.example.webblog.api.admin;

import com.example.webblog.dto.CategoryDTO;
import com.example.webblog.dto.RoleDTO;
import com.example.webblog.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RoleAPI {
    @Autowired
    IRoleService roleService;

    @PostMapping("api/role")
    public RoleDTO createRole(@RequestBody RoleDTO roleDTO){
        return roleService.save(roleDTO);
    }

    @PutMapping("api/role/{id}")
    public RoleDTO updateRole(@RequestBody RoleDTO roleDTO, @PathVariable("id")long id){
        roleDTO.setId(id);
        return roleService.save(roleDTO);
    }

    @DeleteMapping("api/role")
    public void deleteRole(@RequestBody long[] ids) {
        roleService.delete(ids);
    }
}
