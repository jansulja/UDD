package com.ftn.jan.viewmodel;

import java.util.List;

import com.ftn.jan.ddm.searcher.SearchField;

public class SearchViewModel {

	private List<SearchField> fields;

	public List<SearchField> getFields() {
		return fields;
	}

	public void setFields(List<SearchField> fields) {
		this.fields = fields;
	}

	@Override
	public String toString() {
		return "SearchViewModel [fields=" + fields + "]";
	}
	
	

	
	
}
