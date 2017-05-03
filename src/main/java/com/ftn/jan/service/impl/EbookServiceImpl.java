package com.ftn.jan.service.impl;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
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
	
	private static Log4JLogger logger = new Log4JLogger(EbookServiceImpl.class.getName());
	
	@Override
	public List<Ebook> findAll() {
		return InformationRetriever.getData(new MatchAllDocsQuery());
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
	
		List<IndexableField> fields = generateFields(newEbook);
		IndexManager.getIndexer().updateDocument(doc,fields.stream().toArray(IndexableField[]::new));
		
		
	}

	private static List<IndexableField> generateFields(Ebook newEbook) {
	
		final Ebook ebook = newEbook;
		
		List<IndexableField> fields = new ArrayList<>();
		
		Arrays.stream(newEbook.getClass().getSuperclass().getDeclaredFields())
				
				.filter(f->!f.getName().equals("ebookId"))
				.filter(f->!f.getName().equals("highlight"))

				.forEach(field -> { fields.addAll(generateField(ebook,field));});
		
		return fields;
	}

	private static List<IndexableField> generateField(Ebook newEbook, Field field) {
		
		String name = field.getName();
		String value = get(newEbook, name).toString();
		
		List<IndexableField> fields = new ArrayList<>();
		if(name.equals("keywords")){
			String[] kws = value.trim().split(" ");
			for(String kw : kws){
				if(!kw.trim().equals("")){
					fields.add(new TextField("keyword", kw, Store.YES));
				}
			}
		}
		else if(name.equals("author") || name.equals("title")){
			fields.add( new TextField(name , value , Store.YES));
		}else{
			fields.add( new StringField(name,value,Store.YES));
		}
		return fields;
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

	@Override
	public FileSystemResource download(String filename) {
		File file = new File(ResourceBundle.getBundle("index").getString("docs") + File.separator + filename + ".pdf");
		
		logger.info(ResourceBundle.getBundle("index").getString("docs"));
		
		return new FileSystemResource(file); 
	}

	@Override
	public File downloadFile(String filename) {
		File file = new File(ResourceBundle.getBundle("index").getString("docs") + File.separator + filename + ".pdf");
		return file;
	}
	
	
	
}
