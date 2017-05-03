package com.ftn.jan.service;

import java.io.File;
import java.util.List;

import org.springframework.core.io.FileSystemResource;

import com.ftn.jan.model.Ebook;
import com.ftn.jan.model.Language;

import javassist.NotFoundException;

public interface EbookService {

	public List<Ebook> findAll();
	public Ebook findByEbookId(Long ebookId);
	public void updateBook(Ebook oldEbook,Ebook newEbook);
	public void delete(Long ebookId) throws NotFoundException;
	public FileSystemResource download(String filename);
	public File downloadFile(String filename);
	
	
}
