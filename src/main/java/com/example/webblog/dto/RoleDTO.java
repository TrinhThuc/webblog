package com.example.webblog.dto;

import com.example.webblog.entity.UserEntity;

import java.util.ArrayList;
import java.util.List;

public class RoleDTO extends AbstractDTO<RoleDTO>{
    private String code;
    private String name;
    private List<String> usernames = new ArrayList<>();
    private List<UserDTO> users = new ArrayList<>();

    public List<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(List<String> usernames) {
        this.usernames = usernames;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
