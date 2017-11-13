package com.cz.xlsxReader.util;

import java.io.File;
import java.net.URL;

/**
 *<ol>
 *<li>根据包名和文件名包装File对象</li>
 *<li>根据给定的磁盘路径包装File对象 </li>
 *</ol> 
 * @author cz
 *
 */
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
