package com.ftn.jan.ddm.util;

import org.apache.lucene.document.Document;

public class DocumentManager {

	
	public static void printDocument(Document doc){
		
		doc.forEach(System.out::println);
		
	}
	
	
	
}
