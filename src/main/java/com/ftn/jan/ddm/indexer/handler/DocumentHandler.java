package com.ftn.jan.ddm.indexer.handler;

import java.io.File;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexableField;

import com.ftn.jan.ddm.IncompleteIndexDocumentException;
import com.ftn.jan.viewmodel.EbookViewModel;


public abstract class DocumentHandler {
	public abstract Document getDocument(File file) throws IncompleteIndexDocumentException;

	public abstract Document getDocument(File file, EbookViewModel ebook) throws IncompleteIndexDocumentException;
	
}
