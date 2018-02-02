package com.cz.xlsxReader;

import com.cz.xlsxReader.service.DataGenerator;
import com.cz.xlsxReader.util.XmlHelper;

/**
 * Hello world!
 *
 */
public class App 
{
	public static boolean isGenerateAllFile;//是否全部生成文件
	public static boolean isGenerateNav;//是否生成导航文件
	public static boolean isGenerateSearch;//是否生成搜索文件
	public static boolean isGenerateData;//是否生成数据文件
	public static boolean isImageData;//是否生成数据文件
	static{
    	isGenerateAllFile = Boolean.valueOf(XmlHelper.value(XmlHelper.getElementById("isGenerateAllFile"),"value"));
    	isGenerateNav = Boolean.valueOf(XmlHelper.value(XmlHelper.getElementById("isGenerateNav"),"value"));
    	isGenerateSearch = Boolean.valueOf(XmlHelper.value(XmlHelper.getElementById("isGenerateSearch"),"value"));
    	isGenerateData = Boolean.valueOf(XmlHelper.value(XmlHelper.getElementById("isGenerateData"),"value"));
    	isImageData = Boolean.valueOf(XmlHelper.value(XmlHelper.getElementById("isImageData"),"value"));
	}
	public static void main( String[] args )
    {
		String productCategory = "2TVBox";//1PhonePad 2TVBox 3smartDevice
        DataGenerator generator = new DataGenerator();
        generator.generate(productCategory);
    }
   
}
