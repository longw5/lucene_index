package com.beagle.test;

import java.io.IOException;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.simple.SimpleQueryParser;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.FieldValueQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.Sort;
import org.apache.lucene.search.SortField;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.TopFieldDocs;

import com.beagle.search.BdrtIndexSearcher;

public class TestSearchIndex2 {

	private static final String INDEXPATH = "E:\\lucene\\weibo\\index";
	// private static final String INDEXPATH = "E:\\lucene\\weibo\\index_tmp";

	//实现简单检索关键词
	public static void main(String[] args) throws Exception {

		IndexSearcher searcher = BdrtIndexSearcher.getSearcher(INDEXPATH);

		StandardAnalyzer standardAnalyzer = new StandardAnalyzer();
		
		SimpleQueryParser parser = new SimpleQueryParser(standardAnalyzer, "accountName");
		parser.setDefaultOperator(Occur.MUST);
		Query parse = parser.parse("xjch_521@126.com");

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
