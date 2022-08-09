package com.example.webblog.service;



import com.example.webblog.dto.PostDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IPostService {
	List<PostDTO> findAll(Pageable pageable);
	List<PostDTO> findAllByAuthor(String name);
	List<PostDTO> findAllByCategory(String category, Pageable pageable);
	List<PostDTO> findAllByTab(String tab, Pageable pageable);
	List<PostDTO> findAllByActive(boolean isActive, Pageable pageable);
	PostDTO findById(Long id);
	int getTotalItemWithCategory_Name(String name);
	int getTotalItemWithTab(String tab);
	int getTotalItemWithActive(boolean isActive);
	int getTotalItem();
	PostDTO save(PostDTO dto);
	void delete(long[] ids);
}
