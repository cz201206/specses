package com.cz.xlsxReader.service;

import static com.cz.xlsxReader.util.FileHelper.files;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import com.cz.xlsxReader.data.DataOfFileNames;
import com.cz.xlsxReader.util.FileHelper;
import com.cz.xlsxReader.util.PinyinHelper;
import com.cz.xlsxReader.util.StringHelper;
import com.cz.xlsxReader.util.XlsxHelper;
import com.cz.xlsxReader.util.XmlHelper;

public class ExtractImage {
	private static Logger logger = LogManager.getLogger(ExtractImage.class.getName());
	private String xlsxDirectory;
	private String image1Directory;
	public ExtractImage() {
		//读取classpath/conf中xlsx文件所在位置和图片存储位置
		xlsxDirectory = XmlHelper.value(XmlHelper.getElementById("xlsxDirectory"), "name");
		image1Directory = XmlHelper.value(XmlHelper.getElementById("image1Directory"), "name");
	}
	
	//根据提取出的第一张照片来检查哪个文件中没有图片
	@Test
	public void checkImageLost(){
		File dir = new File("E:/imgFromXlsx/single");
		File[] files = dir.listFiles();
		List<String> list = new ArrayList<String>();
		for (File file : files) {
			list.add(StringUtils.substringBefore(file.getName(),"1.png"));
		}
		//遍历少的集合
		/*for (String string : list) {
			System.out.println(string);
		}*/
		List<String> fileNames = new DataOfFileNames().fileNamesSubStringVersion();
		for (String fileName : fileNames) {
			if(!list.contains(fileName))System.out.println(fileName);
		}
	}
	
	@Test
	public void extractAll(){
		//检查
		//获取产品名
		List<String> fileNames = new DataOfFileNames().fileNamesSubStringVersion();
		for (String string : fileNames) {
			System.out.println(string);
		}
		System.out.println("总数："+fileNames.size());
		
		//操作
		//获取所有文件，con.xml配置xlsx文件所在位置
		File[] files = files(xlsxDirectory);
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
	/**
	 * 第个xslx文件仅提取一张图片
	 * 
	 * 2017年11月16日 上午10:34:09
	 */
	@Test
	public void extract1(){
		//检查
		//获取产品名
		List<String> fileNames = new DataOfFileNames().fileNamesSubStringVersion();
		for (String string : fileNames) {
			System.out.println(string);
		}
		System.out.println("总数："+fileNames.size());
		
		//操作
		//获取所有文件，con.xml配置xlsx文件所在位置
		File[] files = files(xlsxDirectory);
		//遍历所有文件
		for (int i = 0; i < files.length; i++) {
			//xlsx文件名
			String fileName = files[i].getName();
			//打印文件名称
			System.out.println(i+1+"."+fileName);
			//xlsx文件对象
			Workbook workbook = XlsxHelper.workbook(files[i]);
			
			//名字处理：去除参数之后的文字
			fileName = StringUtils.substringBefore(files[i].getName(),"参数");
			//名字处理：汉字转拼音首字母
			fileName = PinyinHelper.initial(fileName);
			//名字处理：去除特殊字符
			fileName = StringHelper.deleteChars(fileName, " ()（）°.:-");
			//名字处理：全部小写
			fileName = fileName.toLowerCase();

			//打印文件名称
			System.out.println(i+1+"."+fileName);
			//提取第一张图片
			XlsxHelper.imageNTo(image1Directory,fileName,1);
		}
		
		logger.info("文件总量："+files.length);
		
	}
	
}
