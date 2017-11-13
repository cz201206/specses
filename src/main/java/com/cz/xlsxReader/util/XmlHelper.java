package com.cz.xlsxReader.util;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlHelper {
	
	//xmlSax方式阅读器
	static private SAXReader saxReader = new SAXReader();
	static private Document document = null;
	
	//加载xml文件
	static{
		try {
			document = saxReader.read(new File("D:/java/project/eclipse/xlsxReader/src/main/java/com/cz/xlsxReader/conf.xml"));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
	}
	//1.根据ID获取元素信息
	public static Element getElementById(String id){
		return (Element) document.selectSingleNode("//*[@id='"+id+"']");
	}
	//2.根据name获取元素信息
	public static Element getElementByName(String name){
		return (Element) document.selectSingleNode("//*[@name='"+name+"']");
	}
	//3.获取元素名称相同的所有元素
	public static List<Element> getElementsByElementName(String eleName){
		return (List<Element>) document.selectNodes("//"+eleName);
	}
	//获取元素的某个属性
	public static String value(Element ele ,String peropertyName){
		return ele.attribute(peropertyName).getStringValue();
	}
	
}
