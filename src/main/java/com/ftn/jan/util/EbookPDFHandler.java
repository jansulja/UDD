package com.ftn.jan.util;

import java.io.File;

import org.apache.lucene.document.Document;

import com.ftn.jan.ddm.IncompleteIndexDocumentException;
import com.ftn.jan.ddm.indexer.handler.PDFHandler;
import com.ftn.jan.model.Ebook;

public class EbookPDFHandler {

	public static Ebook createEbookFromPDF(File file){
		
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
		
		String[] allKeywords = document.getValues("keyword");
		String 	temp = "";
		for(String keyword : allKeywords){
			temp += keyword + " ";
		}
		
		ebook.setKeywords(temp);
			
		return ebook;
		
	}
	
	
}
