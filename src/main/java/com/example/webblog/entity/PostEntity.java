package com.example.webblog.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "post")
public class PostEntity extends BaseEntity{
	
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "thumbnail")
	private String thumbnail;
	
	@Column(name = "shortdescription", columnDefinition = "TEXT")
	private String shortDescription; 
	
	@Column(name = "content", columnDefinition = "TEXT")
	private String content;

	@Column(name = "view")
	private Long view;

	@Column(name = "isActive")
	private boolean isActive;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private CategoryEntity category;


}
