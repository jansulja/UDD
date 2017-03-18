package com.ftn.jan.service.impl;

import java.util.List;

import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.Query;
import org.springframework.stereotype.Service;

import com.ftn.jan.ddm.model.SearchType;
import com.ftn.jan.ddm.query.QueryBuilder;
import com.ftn.jan.ddm.searcher.InformationRetriever;
import com.ftn.jan.ddm.searcher.SearchField;
import com.ftn.jan.model.Ebook;
import com.ftn.jan.service.SearchService;
import com.ftn.jan.viewmodel.SearchViewModel;


@Service
public class SearchServiceImpl implements SearchService {


	private static Log4JLogger logger = new Log4JLogger(SearchServiceImpl.class.getName());
	
	@Override
	public List<Ebook> search(SearchViewModel searchModel) {
		
		BooleanQuery bquery = new BooleanQuery();
		
		searchModel.getFields()
		.stream()
		.forEach(f ->  addToQuery(bquery,f));

		logger.info(InformationRetriever.getData(bquery));
		
		return null;
	}
	
	@Override
	public void addToQuery(BooleanQuery bquery, SearchField f) {
		
		if(!(f.getValue() == null || f.getValue().equals(""))){
			Query query = null;
			try {
				query = QueryBuilder.buildQuery(SearchType.getType(f.getType()), f.getField(), f.getValue());
			} catch (IllegalArgumentException | ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bquery.add(query, getOccur(f.getOccur()));
		}
		
	}

	@Override
	public Occur getOccur(String value) {
		if(value.equals("MUST")){
			return Occur.MUST;
		}else if(value.equals("MUST NOT")){
			return Occur.MUST_NOT;
		}else{
			return Occur.SHOULD;
		}
	}

}
