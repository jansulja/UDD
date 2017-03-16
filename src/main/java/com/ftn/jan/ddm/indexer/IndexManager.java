package com.ftn.jan.ddm.indexer;

public class IndexManager {
	
	private static boolean restartIndex = false;
	private static UDDIndexer indexer = new UDDIndexer(restartIndex);
	
	public static UDDIndexer getIndexer(){
		if(indexer == null){
			indexer = new UDDIndexer(restartIndex);
		}
		return indexer;
	}
	
	public static void restart(){
		indexer.restart();
	}

}
