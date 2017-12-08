package com.cz.xlsxReader.service;

import static com.cz.xlsxReader.util.FileHelper.fileInClassPath;
import static com.cz.xlsxReader.util.FileHelper.files;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

import com.cz.xlsxReader.App;
import com.cz.xlsxReader.data.DataFromXlsx;
import com.cz.xlsxReader.data.DataOfFileNames;
import com.cz.xlsxReader.pojo.Product;
import com.cz.xlsxReader.pojo.Specs;
import com.cz.xlsxReader.util.FtlHelper;
import com.cz.xlsxReader.util.PinyinHelper;
import com.cz.xlsxReader.util.StringHelper;
import com.cz.xlsxReader.util.XlsxHelper;
import com.cz.xlsxReader.util.XmlHelper;

public class DataGenerator {
	private static Logger logger = LogManager.getLogger(DataGenerator.class.getName());
	private String xlsxDirectory;
	private String image1Directory;
	private List<Product> products = new ArrayList<Product>();
	public DataGenerator() {
		//读取classpath/conf中xlsx文件所在位置和图片存储位置
		xlsxDirectory = XmlHelper.value(XmlHelper.getElementById("xlsxDirectory"), "value");
		image1Directory = XmlHelper.value(XmlHelper.getElementById("image1Directory"), "value");
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
		List<File> files = files(xlsxDirectory);
		//遍历所有文件
		for (int i = 0; i < files.size(); i++) {
			System.out.println(i+1+"."+files.get(i).getName());
		}

		logger.info("文件总量："+files.size());
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
	public void generate(){
		//获取所有文件，con.xml配置xlsx文件所在位置
		List<File> files = files(xlsxDirectory);
		//遍历所有文件
		for (int i = 0; i < files.size(); i++) {
			Product product  = new Product();
			//xlsx文件名
			String fileName = files.get(i).getName();product.fileName = fileName;
			//xlsx文件对象
			Workbook workbook = XlsxHelper.workbook(files.get(i));
			
			//准备生成html数据
			DataFromXlsx dataFromXlsx = new DataFromXlsx();
			List<Specs> specses = dataFromXlsx.specs(files.get(i));
			File ftlDir = new File(fileInClassPath("com.cz.xlsxReader.res.ftl","ftl.ftl").getParent());
			String ftlName = "data.ftl";
			String outputDir = XmlHelper.value(XmlHelper.getElementById("outputDir"), "value");
			String name = "nav.html";
			Map<String,Object> root = new LinkedHashMap<String,Object>();
			root.put("metaVersion", "元信息-版本");
			root.put("specses", specses);
			
			//名字处理：去除参数之后的文字
			fileName = StringUtils.substringBefore(files.get(i).getName(),"参数");product.title = fileName;root.put("title", fileName);
			//名字处理：汉字转拼音首字母
			fileName = PinyinHelper.initial(fileName);
			//名字处理：去除特殊字符
			fileName = StringHelper.deleteChars(fileName, " ()（）°.:-");
			//名字处理：全部小写
			fileName = fileName.toLowerCase();product.name = fileName;products.add(product);
			
			//4.生成html数据
			root.put("name", fileName);
			name = fileName+".html";
			//是否生成data数据
			if(App.isGenerateAllFile&&App.isGenerateData)
			FtlHelper.createFileFromFtl(ftlDir, ftlName, outputDir, name, root);
			//1.提取第一张图片
			if(App.isGenerateAllFile&&App.isImageData)
			XlsxHelper.imageNTo(image1Directory,fileName,1);
		}
		
		//打印产品详情
		for (Product product : products) {
			System.out.println(product);
		}
		
		File ftlDir = new File(fileInClassPath("com.cz.xlsxReader.res.ftl","ftl.ftl").getParent());
		String ftlName = "nav.ftl";
		String outputDir = XmlHelper.value(XmlHelper.getElementById("outputDir"), "value");
		String name = "nav.html";
		Map<String,Object> root = new LinkedHashMap<String,Object>();
		
		root.put("metaVersion", "元信息-版本");
		root.put("products", products);
		//2.生成nav数据
		//是否生成nav数据
		if(App.isGenerateAllFile&&App.isGenerateNav)
		FtlHelper.createFileFromFtl(ftlDir, ftlName, outputDir, name, root);
		
		//3.生成search数据
		ftlName = "search.ftl";
		name = "search.json";
		//是否生成search数据
		if(App.isGenerateAllFile&&App.isGenerateSearch){
			FtlHelper.createFileFromFtl(ftlDir, ftlName, outputDir, name, root);
			System.out.println("输出文件夹："+outputDir);
		}
		
		logger.info("文件总量："+files.size());
		
	}
	
}
