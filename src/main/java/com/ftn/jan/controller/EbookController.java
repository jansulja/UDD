package com.ftn.jan.controller;

import java.util.List;

import org.apache.commons.logging.impl.Log4JLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ftn.jan.model.Ebook;
import com.ftn.jan.service.EbookService;
import com.ftn.jan.util.EbookPDFHandler;
import com.ftn.jan.util.FileSystemStorageService;
import com.ftn.jan.util.StorageService;
import com.ftn.jan.viewmodel.EbookViewModel;

import javassist.NotFoundException;

@RestController
@RequestMapping("ebook")
public class EbookController {

	@Autowired
	private StorageService storageService;

	@Autowired
	private EbookService ebookService;

	private static Log4JLogger logger = new Log4JLogger(EbookController.class.getName());
	
	
	@RequestMapping(path = "/insert", method = RequestMethod.POST,consumes = {"multipart/form-data"})
	public void insert(@RequestPart("ebook") EbookViewModel ebook,@RequestPart("file") MultipartFile file) {
		
		ebook.setEbookId(System.currentTimeMillis());
		storageService.store(file,ebook);
		

	}
	
	@RequestMapping(path = "/edit", method = RequestMethod.POST,consumes = {"multipart/form-data"})
	public void edit(@RequestPart("ebook") EbookViewModel ebook,@RequestPart(name="file",required=false) MultipartFile file) throws NotFoundException {

		if(file==null){
			Ebook ebookToUpdate = ebookService.findByEbookId(ebook.getEbookId());
			ebookService.updateBook(ebookToUpdate, ebook);
		}else{
			ebookService.delete(ebook.getEbookId());
			storageService.store(file,ebook);
		}
	}
	
	
	@RequestMapping(path = "/upload", method = RequestMethod.POST)
	public Ebook upload(@RequestPart(name="ebookId",required=false) Long ebookId, @RequestPart("file") MultipartFile file,
			RedirectAttributes redirectAttributes) {
		logger.info(ebookId);
		Ebook ebook = EbookPDFHandler.createEbookFromPDF(FileSystemStorageService.convert(file),ebookId);
		System.out.println(ebook);
		return ebook;

	}
	
	@DeleteMapping(value="delete/{ebookId}")
	public void delete(@PathVariable("ebookId") Long ebookId) throws NotFoundException{
		ebookService.delete(ebookId);
	};
	
	
	
	@RequestMapping(path = "/list", method = RequestMethod.GET)
	//@PreAuthorize("hasRole('ROLE_ADMIN')")
	public List<Ebook> list() {

		 return ebookService.findAll();
		
	}

	

}
