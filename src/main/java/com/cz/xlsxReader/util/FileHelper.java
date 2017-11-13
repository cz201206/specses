package com.cz.xlsxReader.util;

import java.io.File;
import java.net.URL;


public class FileHelper {
	//获取类路径文件
	public static File fileInClassPath(String pkg,String name) {
		URL url = null;
		if(pkg.equals(""))
			url = FileHelper.class.getClassLoader().getResource("");
		else
			url = FileHelper.class.getClassLoader().getResource(pkg.replaceAll("\\.", "/")+"/");
		return new File(url.getPath()+name);
	}
}
