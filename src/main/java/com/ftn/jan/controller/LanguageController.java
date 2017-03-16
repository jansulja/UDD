package com.ftn.jan.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.jan.model.Language;
import com.ftn.jan.service.LanguageService;

@RestController
@RequestMapping("language")
public class LanguageController {

	@Autowired
	private LanguageService languageService;
	
	@GetMapping
	public List<Language> list(){
		
		return languageService.findAll();
		
	}
	
}
