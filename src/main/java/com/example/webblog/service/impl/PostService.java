package com.example.webblog.service.impl;



import com.example.webblog.converter.PostConverter;
import com.example.webblog.dto.PostDTO;
import com.example.webblog.entity.CategoryEntity;
import com.example.webblog.entity.PostEntity;
import com.example.webblog.respository.CategoryRepository;
import com.example.webblog.respository.PostRepository;
import com.example.webblog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
public class PostService implements IPostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PostConverter postConverter;

	@Override
	@Transactional
	public PostDTO save(PostDTO dto) {
		CategoryEntity category = categoryRepository.findOneByCode(dto.getCategoryCode());
		PostEntity postEntity = new PostEntity();
		if (dto.getId() != null) {
			PostEntity oldNew = postRepository.getById(dto.getId());
			oldNew.setCategory(category);
			postEntity = postConverter.toEntity(oldNew, dto);
		} else {
			postEntity = postConverter.toEntity(dto);
			postEntity.setCategory(category);
		}
		return postConverter.toDto(postRepository.save(postEntity));
	}

	@Override
	@Transactional
	public void delete(long[] ids) {
		for(long id : ids) {
			postRepository.deleteById(id);
		}
	}


}