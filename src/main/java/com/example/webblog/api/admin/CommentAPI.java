package com.example.webblog.api.admin;

import com.example.webblog.dto.CommentDTO;
import com.example.webblog.dto.RoleDTO;
import com.example.webblog.entity.PostEntity;
import com.example.webblog.entity.UserEntity;
import com.example.webblog.respository.PostRepository;
import com.example.webblog.respository.UserRepository;
import com.example.webblog.service.ICommentService;
import com.example.webblog.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.xml.stream.events.Comment;

@RestController
public class CommentAPI {
    @Autowired
    ICommentService commentService;

    @Autowired
    PostRepository postRepository;

    @Autowired
    UserRepository userRepository;

    @PostMapping("api/comment")
    public CommentDTO createComment(@RequestBody CommentDTO commentDTO,@RequestParam(name = "postid") long postid,
                                    @RequestParam(name = "userid") long userid){
        PostEntity post = postRepository.getById(postid);
        UserEntity user = userRepository.getById(userid);
        commentDTO.setPostid(post.getId());
        commentDTO.setUsername(user.getUserName());
        return commentService.save(commentDTO);
    }

    @PutMapping("api/comment/{id}")
    public CommentDTO updateComment(@RequestBody CommentDTO commentDTO, @PathVariable("id")long id,
                                    @RequestParam(name = "postid") long postid,
                                    @RequestParam(name = "userid") long userid){
        commentDTO.setPostid(postid);
        commentDTO.setUsername(userRepository.getById(userid).getUserName());
        commentDTO.setId(id);
        return commentService.save(commentDTO);
    }

    @DeleteMapping("api/comment")
    public void deleteComment(@RequestBody long[] ids) {
        commentService.delete(ids);
    }
}
