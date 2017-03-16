package com.ftn.jan.ddm.searcher;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ftn.jan.ddm.indexer.IndexManager;
import com.ftn.jan.jpa.repository.CategoryRepository;
import com.ftn.jan.jpa.repository.LanguageRepository;
import com.ftn.jan.model.Ebook;

@Component
public class InformationRetriever {
	
	private static CategoryRepository categoryRepository;
	private static LanguageRepository languageRepository;
	
	@Autowired
	private CategoryRepository categoryRepository0;
	
	@Autowired
	private LanguageRepository languageRepository0;
	
	@PostConstruct
	public void initStaticRepo(){
		categoryRepository = this.categoryRepository0;
		languageRepository = this.languageRepository0;
	}
	
	
	public static List<Ebook> getData(Query query){
		if(query == null) return null;
		List<Document> docs = ResultRetriever.getResults(query);
		List<Ebook> results = new ArrayList<Ebook>();
		
		String temp;
		Ebook data;

		//DirectoryReader reader;
		//reader = DirectoryReader.open(IndexManager.getIndexer().getIndexDir());
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
			data.setPublicationYear(Integer.valueOf(doc.get("publicationYear")));
			data.setFilename(doc.get("filename"));
			data.setMime(doc.get("mime"));
			data.setCategory(categoryRepository.findByName(doc.get("category")));
			data.setLanguage(languageRepository.findByName(doc.get("language")));
			
			
			
			temp = "";
			
			
			results.add(data);
		}
		//reader.close();
		return results;
		//throw new IllegalArgumentException("U prosledjenom direktorijumu ne postoje indeksi ili je direktorijum zakljucan");
	}
	
	
	
	

}
