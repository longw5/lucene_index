package com.beagle.test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;

import com.beagle.constant.StoreConstant;
import com.beagle.writer.BdrtIndexWriter;

public class TestWriterIndex {
	
	private static final String INDEXPATH = "E:\\lucene\\weibo\\index";
	private static final String SRCPATH = "E:\\lucene\\weibo\\test";
	
	public static void main(String[] args) {
		
		long currentTimeMillis = System.currentTimeMillis();
		
		IndexWriter writer = BdrtIndexWriter.getWriter(INDEXPATH);
		if(writer == null)
			return;
		
		try {
			//新建索引
			writer.deleteAll();
			File file = new File(SRCPATH);
			
			if(file.isDirectory()){
				
				File[] listFiles = file.listFiles();
				for (int i = 0; i < listFiles.length; i++) {

					File f = listFiles[i];
					FileInputStream fis = new FileInputStream(f);
					BufferedReader br = new BufferedReader(new InputStreamReader(fis, "gbk"));
					String s = "";
					while ((s = br.readLine()) != null) {
						try {
							Document doc = new Document();
							List<String> list = Arrays.asList(s.split("----"));
							System.out.println(list);
							doc.add(new Field("accountName", list.get(0), StoreConstant.STORE_INDEX));
							doc.add(new Field("accountpassWd", list.get(1), StoreConstant.STORE_NONE_INDEX));
							doc.add(new Field("cardName", list.get(2), StoreConstant.STORE_NONE_INDEX));
							doc.add(new Field("cardNo", list.get(3), StoreConstant.STORE_INDEX));
							doc.add(new Field("username", list.get(4), StoreConstant.STORE_NONE_INDEX));
							doc.add(new Field("telNo", list.get(5), StoreConstant.STORE_NONE_INDEX));
							doc.add(new Field("email", list.get(6), StoreConstant.STORE_NONE_INDEX));
							System.out.println();
							writer.addDocument(doc);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (Exception e) {
			try {
				writer.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		try {
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		long currentTimeMillis2 = System.currentTimeMillis();
		System.out.println("花费时间:"+(currentTimeMillis2-currentTimeMillis));
		BdrtIndexWriter.close();
	}
}
