package com.ftn.jan.util;

import java.io.File;

import org.apache.lucene.document.Document;
import org.springframework.web.multipart.MultipartFile;

import com.ftn.jan.ddm.IncompleteIndexDocumentException;
import com.ftn.jan.ddm.indexer.handler.PDFHandler;
import com.ftn.jan.model.Ebook;

public class EbookPDFHandler {

	public static Ebook createEbookFromPDF(File file,Long ebookId){
		
		PDFHandler pdfHandler = new PDFHandler();
		Document document = null;
		try {
			document = pdfHandler.getDocument(file);
		} catch (IncompleteIndexDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Ebook ebook = new Ebook();
		ebook.setAuthor(document.get("author"));
		ebook.setTitle(document.get("title"));
		if(ebookId!=null){
			ebook.setEbookId(ebookId);
		}else{
			ebook.setEbookId(System.currentTimeMillis());
		}
		String[] allKeywords = document.getValues("keyword");
		String 	temp = "";
		for(String keyword : allKeywords){
			temp += keyword + " ";
		}
		
		ebook.setKeywords(temp);
			
		return ebook;
		
	}
	
public static Ebook createEbookFromPDF(MultipartFile file,Long ebookId){
		
		PDFHandler pdfHandler = new PDFHandler();
		Document document = null;
		try {
			document = pdfHandler.getDocument(file);
		} catch (IncompleteIndexDocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Ebook ebook = new Ebook();
		ebook.setAuthor(document.get("author"));
		ebook.setTitle(document.get("title"));
		if(ebookId!=null){
			ebook.setEbookId(ebookId);
		}else{
			ebook.setEbookId(System.currentTimeMillis());
		}
		String[] allKeywords = document.getValues("keyword");
		String 	temp = "";
		for(String keyword : allKeywords){
			temp += keyword + " ";
		}
		
		ebook.setKeywords(temp);
			
		return ebook;
		
	}
	
	
}
