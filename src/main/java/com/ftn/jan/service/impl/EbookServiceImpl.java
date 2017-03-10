package com.ftn.jan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.jan.model.Ebook;
import com.ftn.jan.model.Language;
import com.ftn.jan.repository.EbookRepository;
import com.ftn.jan.service.EbookService;

@Service
public class EbookServiceImpl implements EbookService {

	@Autowired
	private EbookRepository ebookRepository;
	
	@Override
	public List<Ebook> findAll() {
		// TODO Auto-generated method stub
		return ebookRepository.findAll();
	}

	@Override
	public void insertBook(Ebook ebook) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ebook findByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ebook> findByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ebook> findByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ebook> findByContent(String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ebook> findByLanguage(Language language) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
