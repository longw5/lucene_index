package com.beagle.writer;

import java.io.IOException;
import java.nio.file.FileSystems;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.FSDirectory;

public class BdrtIndexWriter {

	private static FSDirectory fsDirectory;
	private static IndexWriter writer;
	
	public static IndexWriter getWriter(String indexPath){
		return init(indexPath);
	}
	
	private static IndexWriter init(String indexPath) {
		try {
			fsDirectory = FSDirectory.open(FileSystems.getDefault().getPath(indexPath));
			StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
			IndexWriterConfig indexWriterConfig = new IndexWriterConfig(standardAnalyzer);
			writer = new IndexWriter(fsDirectory, indexWriterConfig);
			return writer;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close() {
		
		try {
			writer.close();
		} catch (IOException e) {
			writer = null;
			e.printStackTrace();
		}
		try {
			fsDirectory.close();
		} catch (IOException e) {
			fsDirectory=null;
			e.printStackTrace();
		}
	}
}
