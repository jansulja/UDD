package com.ftn.jan.ddm.analyzer;

import java.io.Reader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.Tokenizer;
import org.apache.lucene.analysis.Analyzer.TokenStreamComponents;
import org.apache.lucene.analysis.standard.StandardTokenizer;
import org.apache.lucene.util.Version;

public class EmptyAnalyzer extends Analyzer {

	Version version;
	
	public EmptyAnalyzer(Version version) {
		super();
		this.version = version;
	}
	
	public EmptyAnalyzer(){
		super();
		this.version = Version.LUCENE_4_9;
	}
	
	@Override
	protected TokenStreamComponents createComponents(String fieldName, Reader reader) {
		Tokenizer source = new StandardTokenizer(version,reader);
		return new TokenStreamComponents(source, source);
	}

}
