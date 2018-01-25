package com.beagle.test.index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.IndexSearcher;

import com.beagle.search.BdrtIndexSearcher;

public class TestBatchIndex {

	private static final String INDEXPATH = "E:\\lucene\\weibo\\index";

	/**
	 * 批量建添加docs
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		System.out.println("开始执行.......................");

		IndexSearcher searcher = BdrtIndexSearcher.getSearcher(INDEXPATH);
		
		int record = 10000;
		int limit = 100;
		
		List<Document> docs = new ArrayList<Document>();
		
		for (int i = record; i <= record+limit; i++) {
			docs.add(searcher.doc(i));
		}
		
		int ii = 0;
		
		for (Document document : docs) {
			ii++;
			System.out.println(ii+"     "+document);
		}
	}
}
