package com.ftn.jan.ddm.indexer;

public class IndexManager {
	
	private static UDDIndexer indexer = new UDDIndexer(false);
	
	public static UDDIndexer getIndexer(){
		if(indexer == null){
			indexer = new UDDIndexer(false);
		}
		return indexer;
	}
	
	public static void restart(){
		indexer.restart();
	}

}
