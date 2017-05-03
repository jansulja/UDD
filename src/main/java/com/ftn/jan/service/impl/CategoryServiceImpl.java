package com.ftn.jan.service.impl;

import java.util.List;

import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ftn.jan.ddm.indexer.IndexManager;
import com.ftn.jan.ddm.searcher.ResultRetriever;
import com.ftn.jan.jpa.repository.CategoryRepository;
import com.ftn.jan.jpa.repository.UserRepository;
import com.ftn.jan.model.Category;
import com.ftn.jan.repository.EbookRepository;
import com.ftn.jan.service.CategoryService;
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private EbookRepository ebookRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public List<Category> findAll() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	@Override
	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryRepository.save(category);
	}

	@Override
	public void update(Category category) {

		String oldName = categoryRepository.getOne(category.getCategoryId()).getName();
		
		QueryParser qp = new QueryParser(Version.LUCENE_4_9,"category",new WhitespaceAnalyzer(Version.LUCENE_4_9));
		List<Document> doc = null;
		try {
			doc = ResultRetriever.getResults(qp.parse("category:"+oldName));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		IndexableField field = new StringField("category", category.getName(), Store.YES);
		doc.stream().forEach(d -> IndexManager.getIndexer().updateDocument(d, field));
		
		categoryRepository.save(category);
		
		
	}

	@Override
	public void remove(Integer categoryId) {
		
		String categoryName = categoryRepository.findOne(categoryId).getName();
		QueryParser qp = new QueryParser(Version.LUCENE_4_9,"category",new WhitespaceAnalyzer(Version.LUCENE_4_9));	
		try {
			IndexManager.getIndexer().deleteDocuments(qp.parse("category:"+categoryName));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		userRepository.findByCategoryCategoryId(categoryId)
		.stream()
		.forEach(u->userRepository.delete(u.getUserId()));
		
		categoryRepository.delete(categoryId);
	}

}
