package com.ftn.jan.controller;

import java.util.List;

import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ftn.jan.model.Ebook;
import com.ftn.jan.service.SearchService;
import com.ftn.jan.viewmodel.SearchViewModel;

@RestController
@RequestMapping("search")
public class SearchController {

	private static Log4JLogger logger = new Log4JLogger(SearchController.class.getName());
	
	@Autowired
	private SearchService searchService;
	
	@PostMapping
	public List<Ebook> search(@RequestBody SearchViewModel searchModel){
		
		logger.info(searchModel);
		
		searchService.search(searchModel);
		
		return null;
	}
	
}
