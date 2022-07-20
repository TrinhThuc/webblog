package com.example.webblog.converter;

import com.example.webblog.dto.CategoryDTO;
import com.example.webblog.dto.CommentDTO;
import com.example.webblog.entity.CategoryEntity;
import com.example.webblog.entity.CommentEntity;
import com.example.webblog.respository.PostRepository;
import com.example.webblog.respository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentConverter {
    @Autowired
    ModelMapper modelMapper;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PostRepository postRepository;

    public CommentDTO toDto(CommentEntity entity) {
        CommentDTO result = new CommentDTO();
        result = modelMapper.map(entity, CommentDTO.class);
        result.setPostid(entity.getId());
        result.setUsername(entity.getUser().getUserName());
        return result;
    }

    public CommentEntity toEntity(CommentDTO dto) {
        CommentEntity result = modelMapper.map(dto, CommentEntity.class);
        result.setPost(postRepository.getById(dto.getPostid()));
        result.setUser(userRepository.findOneByUserNameAndStatusAndEnabled(dto.getUsername(), 1, true));
        return result;
    }

    public CommentEntity toEntity(CommentDTO dto, CommentEntity entity){
        entity.setContent(dto.getContent());
        entity.setPost(postRepository.getById(dto.getPostid()));
        entity.setUser(userRepository.findOneByUserNameAndStatusAndEnabled(dto.getUsername(), 1, true));
        return entity;
    }
}
