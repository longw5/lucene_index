package com.beagle.search;

import java.io.IOException;
import java.nio.file.FileSystems;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.store.FSDirectory;

public class BdrtIndexSearcher {

	private static FSDirectory fsDirectory;
	private static DirectoryReader reader;
	private static IndexSearcher searcher;
	
	public static IndexSearcher getSearcher(String indexPath){
		return init(indexPath);
	}
	
	private static IndexSearcher init(String indexPath) {
		try {
			fsDirectory = FSDirectory.open(FileSystems.getDefault().getPath(indexPath));
			reader = DirectoryReader.open(fsDirectory);
			searcher = new IndexSearcher(reader);
			return searcher;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static void close() {
		try {
			reader.close();
		} catch (IOException e) {
			reader = null;
			e.printStackTrace();
		}
		try {
			fsDirectory.close();
		} catch (IOException e) {
			fsDirectory = null;
			e.printStackTrace();
		}
	}
}
