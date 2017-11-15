package com.cz.xlsxReader.service;

import static com.cz.xlsxReader.util.FileHelper.files;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import com.cz.xlsxReader.data.DataOfFileNames;
import com.cz.xlsxReader.util.FileHelper;
import com.cz.xlsxReader.util.XlsxHelper;
import com.cz.xlsxReader.util.XmlHelper;

public class ExtractImage {
	private static Logger logger = LogManager.getLogger(ExtractImage.class.getName());
	
	@Test
	public void extract(){
		//检查
		//获取产品名
		List<String> fileNames = new DataOfFileNames().fileNamesSubStringVersion();
		for (String string : fileNames) {
			System.out.println(string);
		}
		System.out.println("总数："+fileNames.size());
		
		//操作
		//获取所有文件，con.xml配置xlsx文件所在位置
		File[] files = files(XmlHelper.value(XmlHelper.getElementById("xlsxDirectory"), "name"));
		//遍历所有文件
		for (int i = 0; i < files.length; i++) {
			System.out.println(i+1+"."+files[i].getName());
		}

		logger.info("文件总量："+files.length);
		//提取所有图片
		for (File file : files) {
			Workbook workbook = XlsxHelper.workbook(file);
			XlsxHelper.imagesTo("e:/imgFromXlsx",file.getName());
		}
		
	}
}
