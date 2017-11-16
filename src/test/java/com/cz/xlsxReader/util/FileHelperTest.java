package com.cz.xlsxReader.util;
import static com.cz.xlsxReader.util.FileHelper.fileInClassPath;
import static com.cz.xlsxReader.util.FileHelper.files;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
public class FileHelperTest {
	private static Logger logger = LogManager.getLogger(FileHelperTest.class.getName());
	@Test 
	public void fileInClassPathTest(){
		File conf = fileInClassPath("com.cz.xlsxReader.res.ftl","ftl.ftl");
		System.out.println(conf);
		System.out.println(conf.getParent());
	}
	@Test 
	public void filesTest(){
		File[] files = files("F:/QQ/智能家庭产品参数表汇总");
		//遍历所有文件
		for (int i = 0; i < files.length; i++) {
			System.out.println(i+1+"."+files[i].getName());
		}

		logger.info("文件总量："+files.length);
		
		
	}
}
