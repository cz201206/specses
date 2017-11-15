package com.cz.xlsxReader.data;

import static com.cz.xlsxReader.util.FileHelper.files;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cz.xlsxReader.util.XlsxHelper;
import com.cz.xlsxReader.util.XmlHelper;

public class DataOfFileNames {
	//指数计数
	int index = 0;
	private static Logger logger = LogManager.getLogger(DataOfFileNames.class.getName());
	//和原谅保持一致 用于解析xslx文件使用
	public List<String> fileNames(){
		List<String> fileNames = new ArrayList<String>();
		//根据文件中的“xlsx文件所在位置”获取该文件夹下所有文件名
		File[] files = files(
				XmlHelper.value(XmlHelper.getElementById("xlsxDirectory"), "name")
				);
		for (File file : files) {
			fileNames.add(file.getName());
		}
		return fileNames;
	}
	
	//仅保留参数之前的文字 ，用于计算接收文件总量及查看图片提取情况
	public List<String> fileNamesSubStringVersion(){
		List<String> fileNames = new ArrayList<String>();
		//根据文件中的“xlsx文件所在位置”获取该文件夹下所有文件名
		File[] files = files(
				XmlHelper.value(XmlHelper.getElementById("xlsxDirectory"), "name")
				);
		for (File file : files) {
			fileNames.add(StringUtils.substringBefore(file.getName(),"参数"));
		}
		return fileNames;
	}
	
	//仅保留参数之前的文字且去重 ,用于输出 【标题】 使用
	public Set<String> fileNamesSubStringUniqueVersion(){
		Set<String> fileNames = new LinkedHashSet<String>();
		//根据文件中的“xlsx文件所在位置”获取该文件夹下所有文件名
		File[] files = files(
				XmlHelper.value(XmlHelper.getElementById("xlsxDirectory"), "name")
				);
		for (File file : files) {

			++index;
			String name = StringUtils.substringBefore(file.getName(),"参数");
			if(fileNames.contains(name))logger.warn("有一个重复的位于："+index);
			fileNames.add(name);
		}
		return fileNames;
	}
	//仅保留参数之前的文字+去重+去掉空格及其他字符，用于输出 【生成图片名称和图片链接时使用】 使用
	public Set<String> fileNamesSubStringUniqueDSpaceVersion(){
		Set<String> fileNames = new LinkedHashSet<String>();
		//根据文件中的“xlsx文件所在位置”获取该文件夹下所有文件名
		File[] files = files(
				XmlHelper.value(XmlHelper.getElementById("xlsxDirectory"), "name")
				);
		for (File file : files) {
			String fileName = StringUtils.substringBefore(file.getName(),"参数");
			fileName = StringUtils.replace(fileName," ","");//去掉空格
			fileName = StringUtils.replace(fileName,"°","");//去掉
			fileName = StringUtils.replace(fileName,"-","");//去掉
			fileName = StringUtils.replace(fileName,"（","");//去掉
			fileName = StringUtils.replace(fileName,"）","");//去掉
			fileName = StringUtils.replace(fileName,".","");//去掉
			fileNames.add(fileName);
		}
		return fileNames;
	}
}
