package com.example.webblog.dto;

import com.example.webblog.entity.PostEntity;
import com.example.webblog.entity.UserEntity;

public class CommentDTO extends AbstractDTO<CommentDTO>{
    private String content;
    private String username;
    private Long postid;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Long getPostid() {
        return postid;
    }

    public void setPostid(Long postid) {
        this.postid = postid;
    }
}
