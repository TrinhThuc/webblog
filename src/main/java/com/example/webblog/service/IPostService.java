package com.example.webblog.service;



import com.example.webblog.dto.PostDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService {
	List<PostDTO> findAll(Pageable pageable);
	List<PostDTO> findAllByAuthor(String name);
	PostDTO findById(Long id);
	PostDTO save(PostDTO dto);
	void delete(long[] ids);
}
