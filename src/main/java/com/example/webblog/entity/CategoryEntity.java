package com.example.webblog.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "category")
public class CategoryEntity extends BaseEntity{
	@Column(name = "name")
	private String name;
	
	@Column(name = "code")
	private String code;
	
//	@OneToMany(mappedBy = "category")
//	private List<PostEntity> news = new ArrayList<>();

}
