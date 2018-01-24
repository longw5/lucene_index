package com.beagle.util;

import java.io.File;

public class FileUtil {

  public static boolean renameDirectory(String fromDir, String toDir) {

    File from = new File(fromDir);
    File to = new File(toDir);

    if (!from.exists() || !from.isDirectory()) {
      System.out.println("Directory does not exist: " + fromDir);
      return false;
    }

    //Rename
    if (from.renameTo(to))
    	return true;
    else
    	return false;
  }

  public static void main(String[] args) {
    FileUtil fileutil = new FileUtil();
    fileutil.renameDirectory("E:\\lucene\\weibo\\index_tmp", "E:\\lucene\\weibo\\index_bak");
  }
}