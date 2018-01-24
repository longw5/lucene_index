package com.beagle.constant;

import org.apache.lucene.document.FieldType;
import org.apache.lucene.document.TextField;

public class StoreConstant {

	//存储  索引
	public static final FieldType STORE_INDEX = TextField.TYPE_STORED;
	//存储  不索引
	public static final FieldType STORE_NONE_INDEX = new FieldType();
	
	static{
		//初始化存储
		STORE_NONE_INDEX.setStored(true);
	}
}
