package com.example.webblog.api.admin;

import com.example.webblog.dto.PostDTO;
import com.example.webblog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostAPI {
    @Autowired
    private IPostService postService;

    @PostMapping("api/post")
    public PostDTO createPost(@RequestBody PostDTO postDTO){
        return postService.save(postDTO);
    }

    @PutMapping("api/post/{id}")
    public PostDTO updatePost(@RequestBody PostDTO postDTO, @PathVariable("id")long id){
        postDTO.setId(id);
        return postService.save(postDTO);
    }

    @PutMapping("api/admin/post/active")
    public void activePost(@RequestBody PostDTO postDTO){
        for(Long id : postDTO.getIds()){
            PostDTO dto = postService.findById(id);
            dto.setActive(true);
            postService.save(dto);
        }
    }

    @PutMapping("api/post/remove")
    public void removePost(@RequestBody PostDTO postDTO){
        for(Long id : postDTO.getIds()){
            PostDTO dto = postService.findById(id);
            dto.setActive(false);
            postService.save(dto);
        }
    }


    @DeleteMapping("api/post")
    public void deletePost(@RequestBody long[] ids) {
        postService.delete(ids);
    }
}
