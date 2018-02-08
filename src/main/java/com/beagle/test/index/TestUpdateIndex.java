package com.beagle.test.index;

import java.io.IOException;
import java.util.function.Consumer;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.MatchAllDocsQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopFieldDocs;

import com.beagle.search.BdrtIndexSearcher;
import com.beagle.writer.BdrtIndexWriter;

public class TestUpdateIndex {

	private static final String INDEXPATH = "E:\\lucene\\weibo\\index";
	
	private static final Query MATCHALL = new MatchAllDocsQuery();
	
	public static void main(String[] args) throws Exception {
		
		IndexSearcher indexSearcher = BdrtIndexSearcher.getSearcher(INDEXPATH);
		IndexWriter indexWriter = BdrtIndexWriter.getWriter(INDEXPATH);
		int count = indexSearcher.count(MATCHALL);
		System.out.println(count);
		
		for (int i = 0; i < count; i++) {
			Thread.sleep(2000);

			Document doc = indexSearcher.doc(i);
			System.out.println();
			System.out.println("******************************************************************************");
			
			DirectoryReader directoryReader = DirectoryReader.open(indexWriter); 

//			long tryDeleteDocument = indexWriter.tryDeleteDocument(directoryReader, i);
//			System.out.println("tryDeleteDocument : " + i + " : " + tryDeleteDocument);
			doc.forEach((Consumer<? super IndexableField>) new Consumer<IndexableField>() {
				@Override
				public void accept(IndexableField field) {
					System.out.println(field.name() + "   " + field.stringValue() + "    " + field.fieldType());
				}
			});
			indexWriter.addDocument(doc);
			indexWriter.commit();
		}
		BdrtIndexSearcher.close();
	}
}
