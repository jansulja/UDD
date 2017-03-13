package com.ftn.jan.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ftn.jan.model.Ebook;
import com.ftn.jan.service.EbookService;
import com.ftn.jan.util.StorageService;
import com.ftn.jan.viewmodel.EbookViewModel;

@RestController
public class EbookController {

	@Autowired
	private StorageService storageService;
	
	@Autowired
	private EbookService ebookService;
	
	@RequestMapping("/test")
	public String test() {
		return "hello ebook :DDD";
	}
	
	@RequestMapping(path="/insert",method=RequestMethod.POST)
	public void insert(@RequestBody EbookViewModel ebook,@RequestParam("file") MultipartFile file,
            RedirectAttributes redirectAttributes){
		
		storageService.store(file,ebook);
		
	}
	
	@RequestMapping(path="/ebooks",method=RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Ebook> list(){
		
		Ebook ebook = new Ebook();
		ebook.setTitle("Na drini cuprija.");
		ebook.setAuthor("Ivo andric");
		
		
		List<Ebook> ebooksmock = new ArrayList<>();
		ebooksmock.add(ebook);
		
		//return ebookService.findAll();
		return ebooksmock;
	}
	
}
