package com.example.webblog.service;



import com.example.webblog.dto.PostDTO;

public interface IPostService {
	PostDTO save(PostDTO dto);
	void delete(long[] ids);
}
