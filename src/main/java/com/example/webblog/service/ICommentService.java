package com.example.webblog.service;

import com.example.webblog.dto.CommentDTO;
import com.example.webblog.entity.CommentEntity;

public interface ICommentService {
    CommentDTO save(CommentDTO dto);
    void delete(long[] ids);
}
