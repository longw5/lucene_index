package com.beagle.test;

import java.io.IOException;
import java.util.function.Consumer;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.search.IndexSearcher;

import com.beagle.search.BdrtIndexSearcher;

public class TestSearchIndex {

	private static final String INDEXPATH = "E:\\lucene\\weibo\\index";
//	private static final String INDEXPATH = "E:\\lucene\\weibo\\index_tmp";

	public static void main(String[] args) {

		IndexSearcher searcher = BdrtIndexSearcher.getSearcher(INDEXPATH);
		IndexReader reader = searcher.getIndexReader();
		int maxDoc = reader.maxDoc();
		System.out.println(maxDoc);
		for (int i = 0; i < maxDoc; i++) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			Document doc;
			try {
				doc = searcher.doc(i);
				
				System.out.println();
				System.out.println("******************************************************************************");
				
				doc.forEach((Consumer<? super IndexableField>) new Consumer<IndexableField>() {

					@Override
					public void accept(IndexableField field) {
						System.out.println(field.name() + "   " + field.stringValue() + "    " + field.fieldType());
					}
				});

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		BdrtIndexSearcher.close();
	}
}
