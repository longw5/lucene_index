package com.beagle.test.search;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.queryparser.simple.SimpleQueryParser;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;

import com.beagle.search.BdrtIndexSearcher;

public class TestSimpleQuerySearch {

	private static final String INDEXPATH = "E:\\lucene\\weibo\\index";
	// private static final String INDEXPATH = "E:\\lucene\\weibo\\index_tmp";

	//实现简单检索关键词
	public static void main(String[] args) throws Exception {

		IndexSearcher searcher = BdrtIndexSearcher.getSearcher(INDEXPATH);

		StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
		
		SimpleQueryParser parser = new SimpleQueryParser(standardAnalyzer, "cardNo");
		parser.setDefaultOperator(Occur.MUST);
		Query parse = parser.parse("310112199408103914");

		TopDocs search = searcher.search(parse, 100);

		ScoreDoc[] scoreDocs = search.scoreDocs;

		for (int i = 0; i < scoreDocs.length; i++) {
			int doc = scoreDocs[i].doc;
			Document doc2 = searcher.doc(doc);
			System.out.println(doc2);
		}
		BdrtIndexSearcher.close();
	}
}
