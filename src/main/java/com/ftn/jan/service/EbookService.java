package com.ftn.jan.service;

import java.util.List;

import com.ftn.jan.model.Ebook;
import com.ftn.jan.model.Language;

import javassist.NotFoundException;

public interface EbookService {

	public List<Ebook> findAll();
	public void insertBook(Ebook ebook);
	public Ebook findByTitle(String title);
	public List<Ebook> findByAuthor(String author);
	public List<Ebook> findByKeyword(String keyword);
	public List<Ebook> findByContent(String content);
	public List<Ebook> findByLanguage(Language language);
	public Ebook findByEbookId(Long ebookId);
	public void updateBook(Ebook oldEbook,Ebook newEbook);
	public void delete(Long ebookId) throws NotFoundException;
	
	
}
