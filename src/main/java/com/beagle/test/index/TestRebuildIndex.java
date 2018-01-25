package com.beagle.test.index;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexableField;
import org.apache.lucene.search.IndexSearcher;

import com.beagle.constant.StoreConstant;
import com.beagle.search.BdrtIndexSearcher;
import com.beagle.util.FileUtil;
import com.beagle.writer.BdrtIndexWriter;

public class TestRebuildIndex {

	private static final String INDEXPATH = "E:\\lucene\\weibo\\index";

	public static void main(String[] args) throws IOException {

		System.out.println("开始执行.......................");

		long currentTimeMillis = System.currentTimeMillis();
		
		IndexSearcher searcher = BdrtIndexSearcher.getSearcher(INDEXPATH);
		String tmpIndexDir = INDEXPATH+"_tmp";
		IndexWriter writer = BdrtIndexWriter.getWriter(tmpIndexDir);
		IndexReader reader = searcher.getIndexReader();
		int maxDoc = reader.maxDoc();
		
		Document doc;
		for (int i = 0; i < maxDoc; i++) {
			try {
				doc = searcher.doc(i);
				
				//模拟删除索引
				IndexableField field1 = doc.getField("accountName"); 
				IndexableField field2 = doc.getField("username"); 
				Field field_rev1 = new Field(field1.name(), field1.stringValue(), StoreConstant.STORE_NONE_INDEX);
				Field field_rev2 = new Field(field2.name(), field2.stringValue(), StoreConstant.STORE_INDEX);
				
				doc.removeField("accountName"); 
				doc.add(field_rev1);
			
				doc.removeField("username"); 
				doc.add(field_rev2);
				System.out.println(doc.toString());
				writer.addDocument(doc);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println("花费时间:"+(currentTimeMillis2-currentTimeMillis));
		System.out.println("索引构建成功.....................");
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		BdrtIndexSearcher.close();
		BdrtIndexWriter.close();

		boolean rebuildFile = rebuildFile(tmpIndexDir);
		if(rebuildFile){
			System.out.println("重建索引成功..................");
		}
	}

	private static boolean rebuildFile(String tmpIndexDir) {
		System.out.println("开始重命名文件.....................");
		long currentTimeMillis = System.currentTimeMillis();
		boolean renameDirectory = FileUtil.renameDirectory(INDEXPATH, INDEXPATH+"_bak_"+currentTimeMillis);
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		if(renameDirectory){
			return FileUtil.renameDirectory(tmpIndexDir, INDEXPATH);
		}else{
			renameDirectory = FileUtil.renameDirectory(INDEXPATH+"_bak_"+currentTimeMillis, INDEXPATH);
		}
		return renameDirectory;
	}
}
