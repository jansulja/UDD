package com.ftn.jan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.jan.jpa.repository.LanguageRepository;
import com.ftn.jan.model.Language;
import com.ftn.jan.service.LanguageService;
@Service
public class LanguageServiceImpl implements LanguageService {

	@Autowired
	private LanguageRepository languageRepository;
	
	public List<Language> findAll() {
		// TODO Auto-generated method stub
		return languageRepository.findAll();
	}

}
