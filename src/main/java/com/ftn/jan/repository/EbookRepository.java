package com.ftn.jan.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ftn.jan.model.Ebook;

public interface EbookRepository {

	public List<Ebook> findAll();
	
}
