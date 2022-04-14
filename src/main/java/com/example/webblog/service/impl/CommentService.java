package com.example.webblog.service.impl;

import com.example.webblog.converter.CommentConverter;
import com.example.webblog.dto.CommentDTO;
import com.example.webblog.entity.CommentEntity;
import com.example.webblog.entity.RoleEntity;
import com.example.webblog.respository.CommentRepository;
import com.example.webblog.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService implements ICommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    CommentConverter commentConverter;

    @Override
    @Transactional
    public CommentDTO save(CommentDTO dto) {
        CommentEntity comment = new CommentEntity();
        if(dto.getId() != null){
            CommentEntity oleComment = commentRepository.getById(dto.getId());
            comment = commentConverter.toEntity(dto, oleComment);
        }else
            comment = commentConverter.toEntity(dto);
        return commentConverter.toDto(commentRepository.save(comment));
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for(long id : ids)
            commentRepository.deleteById(id);
    }
}
