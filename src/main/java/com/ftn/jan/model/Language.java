package com.ftn.jan.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Language {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer languageId;
	@Column(length=30)
	private String name;
	
//	@OneToMany
//	private Set<Ebook> ebooks;
	
	
//	@JsonIgnore
//	public Set<Ebook> getEbooks() {
//		return ebooks;
//	}
//	public void setEbooks(Set<Ebook> ebooks) {
//		this.ebooks = ebooks;
//	}
	public Integer getLanguageId() {
		return languageId;
	}
	public void setLanguageId(Integer languageId) {
		this.languageId = languageId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Language [languageId=" + languageId + ", name=" + name + "]";
	}
	
	
	
}
