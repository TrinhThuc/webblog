package com.example.webblog.service.impl;



import com.example.webblog.converter.PostConverter;
import com.example.webblog.converter.UserConverter;
import com.example.webblog.dto.PostDTO;
import com.example.webblog.dto.RoleDTO;
import com.example.webblog.entity.CategoryEntity;
import com.example.webblog.entity.PostEntity;
import com.example.webblog.entity.RoleEntity;
import com.example.webblog.entity.UserEntity;
import com.example.webblog.respository.CategoryRepository;
import com.example.webblog.respository.PostRepository;
import com.example.webblog.respository.UserRepository;
import com.example.webblog.service.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


@Service
public class PostService implements IPostService {
	
	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostConverter postConverter;

	@Autowired
	private UserConverter userConverter;

	@Override
	@Transactional
	public List<PostDTO> findAll(Pageable pageable) {
		List<PostDTO> postDTOS = new ArrayList<>();
		for(PostEntity post : postRepository.findAll(pageable).getContent()){
			PostDTO postDTO = postConverter.toDto(post);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if(post.getCreatedDate() != null)
			postDTO.setDateString(dateFormat.format(post.getCreatedDate()));
			UserEntity user = userRepository.findOneByUserNameAndStatusAndEnabled(post.getCreatedBy(),1 ,true);
			if(user != null)
			postDTO.setAuthor(userConverter.toDto(user));
			postDTOS.add(postDTO);
		}

		return postDTOS;
	}

	@Override
	public List<PostDTO> findAllByAuthor(String name) {
		List<PostDTO> postDTOS = new ArrayList<>();
		for(PostEntity post : postRepository.findAllByCreatedByOrderByCreatedDate(name)){
			PostDTO postDTO = postConverter.toDto(post);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if(post.getCreatedDate() != null)
				postDTO.setDateString(dateFormat.format(post.getCreatedDate()));
			UserEntity user = userRepository.findOneByUserNameAndStatusAndEnabled(post.getCreatedBy(),1 ,true);
			if(user != null)
				postDTO.setAuthor(userConverter.toDto(user));
			postDTOS.add(postDTO);
		}
		return postDTOS;
	}

	@Override
	public List<PostDTO> findAllByCategory(String category, Pageable pageable) {
		List<PostDTO> postDTOS = new ArrayList<>();
		List<PostEntity> postEntities = postRepository.findAllByCategory_CodeAndActiveIsTrue(category, pageable).getContent();
		for(PostEntity post : postEntities){
			PostDTO postDTO = postConverter.toDto(post);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if(post.getCreatedDate() != null)
				postDTO.setDateString(dateFormat.format(post.getCreatedDate()));
			UserEntity user = userRepository.findOneByUserNameAndStatusAndEnabled(post.getCreatedBy(),1 ,true);
			if(user != null)
				postDTO.setAuthor(userConverter.toDto(user));
			postDTOS.add(postDTO);
		}
		return postDTOS;
	}

	@Override
	public List<PostDTO> findAllByTab(String tab, Pageable pageable) {
		List<PostDTO> postDTOS = new ArrayList<>();
		List<PostEntity> postEntities = postRepository.findAllByTab(tab,true, pageable).getContent();
		for(PostEntity post : postEntities){
			PostDTO postDTO = postConverter.toDto(post);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if(post.getCreatedDate() != null)
				postDTO.setDateString(dateFormat.format(post.getCreatedDate()));
			UserEntity user = userRepository.findOneByUserNameAndStatusAndEnabled(post.getCreatedBy(),1 ,true);
			if(user != null)
				postDTO.setAuthor(userConverter.toDto(user));
			postDTOS.add(postDTO);
		}
		return postDTOS;
	}

	@Override
	public List<PostDTO> findAllByActive(boolean isActive, Pageable pageable) {
		List<PostDTO> postDTOS = new ArrayList<>();
		List<PostEntity> postEntities = postRepository.findAllByActiveIsTrue( pageable).getContent();
		for(PostEntity post : postEntities){
			PostDTO postDTO = postConverter.toDto(post);
			DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			if(post.getCreatedDate() != null)
				postDTO.setDateString(dateFormat.format(post.getCreatedDate()));
			UserEntity user = userRepository.findOneByUserNameAndStatusAndEnabled(post.getCreatedBy(),1 ,true);
			if(user != null)
				postDTO.setAuthor(userConverter.toDto(user));
			postDTOS.add(postDTO);
		}
		return postDTOS;
	}



	@Override
	@Transactional
	public PostDTO findById(Long id) {
		PostEntity postEntity = postRepository.findPostEntitiesById(id);
		PostDTO postDTO = postConverter.toDto(postEntity);
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		if(postEntity.getCreatedDate() != null)
			postDTO.setDateString(dateFormat.format(postEntity.getCreatedDate()));
		UserEntity user = userRepository.findOneByUserNameAndStatusAndEnabled(postDTO.getCreatedBy(),1 ,true);
		if(user != null)
			postDTO.setAuthor(userConverter.toDto(user));
		return postDTO;
	}

	@Override
	public int getTotalItemWithCategory_Name(String name) {
		return postRepository.findAllByCategory_CodeAndActiveIsTrue(name).size();
	}

	@Override
	public int getTotalItemWithTab(String tab) {
		return postRepository.findAllByTab(tab, true).size();
	}

	@Override
	public int getTotalItemWithActive(boolean isActive) {
		return postRepository.findAllByActiveIsTrue().size();
	}

	@Override
	public int getTotalItem() {
		return (int) postRepository.count();
	}

	@Override
	@Transactional
	public PostDTO save(PostDTO dto) {
		CategoryEntity category = categoryRepository.findCategoryEntitiesById(dto.getCategoryId());
		Long id = dto.getCategoryId();
		PostEntity postEntity = new PostEntity();
		if (dto.getId() != null) {
			PostEntity oldNew = postRepository.findPostEntitiesById(dto.getId());
			oldNew.setCategory(category);
			postEntity = postConverter.toEntity(oldNew, dto);

		} else {
			postEntity = postConverter.toEntity(dto);
			postEntity.setCategory(category);
			postEntity.setActive(false);
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