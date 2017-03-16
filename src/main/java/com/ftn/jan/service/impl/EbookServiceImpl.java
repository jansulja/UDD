package com.ftn.jan.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.jan.ddm.indexer.IndexManager;
import com.ftn.jan.ddm.searcher.InformationRetriever;
import com.ftn.jan.ddm.searcher.ResultRetriever;
import com.ftn.jan.model.Category;
import com.ftn.jan.model.Ebook;
import com.ftn.jan.model.Language;
import com.ftn.jan.repository.EbookRepository;
import com.ftn.jan.service.EbookService;

import javassist.NotFoundException;

@Service
public class EbookServiceImpl implements EbookService {

	@Autowired
	private EbookRepository ebookRepository;
	
	@Override
	public List<Ebook> findAll() {
		// TODO Auto-generated method stub
		//return ebookRepository.findAll();
		return InformationRetriever.getData(new MatchAllDocsQuery());
	}

	@Override
	public void insertBook(Ebook ebook) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Ebook findByTitle(String title) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ebook> findByAuthor(String author) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ebook> findByKeyword(String keyword) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ebook> findByContent(String content) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ebook> findByLanguage(Language language) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ebook findByEbookId(Long ebookId) {

		Query q = new TermQuery(new Term("ebookId", ebookId.toString()));
		
		return InformationRetriever.getData(q).get(0);
	}

	@Override
	public void updateBook(Ebook oldEbook, Ebook newEbook) {

		Query q = new TermQuery(new Term("ebookId", oldEbook.getEbookId().toString()));
		Document doc = ResultRetriever.getResults(q).get(0);
				
		List<IndexableField> fields = generateFields(newEbook);//{new TextField("title", newEbook.getTitle(), Store.YES)};
		fields.stream().peek(System.out::println);
		
		
		IndexManager.getIndexer().updateDocument(doc,fields.stream().toArray(IndexableField[]::new));
		
		
	}

	private static List<IndexableField> generateFields(Ebook newEbook) {
	
		final Ebook ebook = newEbook;
		
		List<IndexableField> fields = new ArrayList<>();
		
		Arrays.stream(newEbook.getClass().getSuperclass().getDeclaredFields())
				
				.filter(f->!f.getName().equals("ebookId"))

				.forEach(field -> { fields.add(generateField(ebook,field));});
		
		return fields;
	}

	private static IndexableField generateField(Ebook newEbook, Field field) {
		
		String name = field.getName();
		String value = get(newEbook, name).toString();
		
		IndexableField f = new TextField(name , value , Store.YES);
		return f;
	}

	public static void main(String[] args){
		generateFields(null);
	}
	
	
	private static Object get(Object object, String fieldName) {
	    Class<?> clazz = object.getClass();
	    Object value = null;
	    while (clazz != null) {
	        try {
	            Field field = clazz.getDeclaredField(fieldName);
	            field.setAccessible(true);
	            
	            if(field.getName().equals("category")){
	            	Category c = (Category) field.get(object);
	            	value = c.getName();
	            }else if(field.getName().equals("language")){
	            	Language l = (Language) field.get(object);
	            	value = l.getName();
	            }else{
	            	value = field.get(object);
	            }
	            
	            
	            break;
	        } catch (NoSuchFieldException e) {
	            clazz = clazz.getSuperclass();
	        } catch (Exception e) {
	            throw new IllegalStateException(e);
	        }
	    }
	    return value;
	}

	@Override
	public void delete(Long ebookId) throws NotFoundException {
		// TODO Auto-generated method stub
		Query q = new TermQuery(new Term("ebookId", ebookId.toString()));
		List<Document> doc = ResultRetriever.getResults(q);
		IndexManager.getIndexer().deleteDocuments(q);
		
		if(doc.isEmpty()){
			throw new NotFoundException("Ebook requested for deletion not found.");
		}
		
	}
	
	
	
}
