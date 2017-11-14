package com.cz.xlsxReader.util;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *<ol>
 *<li>根据包名和文件名包装File对象</li>
 *<li>根据给定的磁盘路径包装File对象 </li>
 *</ol> 
 * @author cz
 *
 */
public class FileHelper {
	private static Logger logger = LogManager.getLogger(FileHelper.class.getName());
	//获取类路径文件
	public static File fileInClassPath(String pkg,String name) {
		URL url = null;
		if(pkg.equals(""))
			url = FileHelper.class.getClassLoader().getResource("");
		else
			url = FileHelper.class.getClassLoader().getResource(pkg.replaceAll("\\.", "/")+"/");
		return new File(url.getPath()+name);
	}
	
	//获取给定路径下所有文件
	public static File[] files(String directoryName){
		
		File  directory = new File(directoryName);
		
		//如果路径存在则返回所有文件
		if(directory.isDirectory()){
			return directory.listFiles();
		}else{
			logger.error("非路径，请检查路径名："+directoryName);
			return null;
		}
		
	}
}
