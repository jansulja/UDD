package com.ftn.jan.ddm.searcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.Query;

import com.ftn.jan.ddm.indexer.IndexManager;
import com.ftn.jan.model.Ebook;


public class InformationRetriever {
	
	
	public static List<Ebook> getData(Query query){
		if(query == null) return null;
		List<Document> docs = ResultRetriever.getResults(query);
		List<Ebook> results = new ArrayList<Ebook>();
		
		String temp;
		Ebook data;

		DirectoryReader reader;
		try {
			reader = DirectoryReader.open(IndexManager.getIndexer().getIndexDir());
			for(Document doc : docs){
				data = new Ebook();
				String[] allKeywords = doc.getValues("keyword");
				temp = "";
				for(String keyword : allKeywords){
					temp += keyword + ", ";
				}
				if(!temp.equals("")){
					temp = temp.substring(0, temp.length()-2);
				}
				data.setKeywords(temp);
				
				data.setTitle(doc.get("title"));
				data.setAuthor(doc.get("author"));
				
				
				temp = "";
				
				
				results.add(data);
			}
			reader.close();
			return results;
		} catch (IOException e) {
		}
		throw new IllegalArgumentException("U prosledjenom direktorijumu ne postoje indeksi ili je direktorijum zakljucan");
	}
	
	
	
	

}
