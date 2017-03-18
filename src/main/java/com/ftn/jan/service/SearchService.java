package com.ftn.jan.service;

import java.util.List;

import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;

import com.ftn.jan.ddm.model.RequiredHighlight;
import com.ftn.jan.ddm.searcher.SearchField;
import com.ftn.jan.model.Ebook;
import com.ftn.jan.viewmodel.SearchViewModel;

public interface SearchService {

	
	public List<Ebook> search(SearchViewModel searchModel);
	public Occur getOccur(String value);
	void addToQuery(BooleanQuery bquery, SearchField f);
	void addToQuery(BooleanQuery bquery, List<RequiredHighlight> requiredHighlights,SearchField f);
	
	
}
