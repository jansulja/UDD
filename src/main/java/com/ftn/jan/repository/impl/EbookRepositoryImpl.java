package com.ftn.jan.repository.impl;

import java.util.List;

import org.apache.lucene.search.MatchAllDocsQuery;
import org.springframework.stereotype.Repository;

import com.ftn.jan.ddm.searcher.InformationRetriever;
import com.ftn.jan.model.Ebook;
import com.ftn.jan.repository.EbookRepository;

@Repository
public class EbookRepositoryImpl implements EbookRepository {

	@Override
	public List<Ebook> findAll() {
		
		 return InformationRetriever.getData(new MatchAllDocsQuery());
	}

}
