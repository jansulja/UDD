package com.ftn.jan.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.ManyToOne;

@Entity
//@Document(indexName = "repository",type="ebook")
public class Ebook {

	@javax.persistence.Id
	@org.springframework.data.annotation.Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long ebookId;

	@Column(length = 80, nullable = false)
	private String title;

	@Column(length = 120)
	private String author;
	@Column(length = 120)
	private String keywords;

	private Integer publicationYear;
	@Column(length = 200, nullable = false)
	private String filename;
	@Column(length = 100)
	private String mime;
	
	@ManyToOne(optional=false)
	private Language language;
	
	@ManyToOne(optional=false)
	private Category category;
	
	private String highlight;
	
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Language getLanguage() {
		return language;
	}
	public void setLanguage(Language language) {
		this.language = language;
	}
	public Long getEbookId() {
		return ebookId;
	}
	public void setEbookId(Long ebookId) {
		this.ebookId = ebookId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public Integer getPublicationYear() {
		return publicationYear;
	}
	public void setPublicationYear(Integer publicationYear) {
		this.publicationYear = publicationYear;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getMime() {
		return mime;
	}
	public void setMime(String mime) {
		this.mime = mime;
	}
	
	public String getHighlight() {
		return highlight;
	}
	public void setHighlight(String highlight) {
		this.highlight = highlight;
	}
	@Override
	public String toString() {
		return "Ebook [ebookId=" + ebookId + ", title=" + title + ", author=" + author + ", keywords=" + keywords
				+ ", publicationYear=" + publicationYear + ", filename=" + filename + ", mime=" + mime + ", language="
				+ language + ", category=" + category + ", highlight=" + highlight + "]";
	}


	
	
	
}
