package com.cz.xlsxReader.util;

import static com.cz.xlsxReader.util.FileHelper.fileInClassPath;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

public class FtlHelperTest {
	@Test
	public void createFileFromFtlToConsoleTest(){
		File ftlDir = new File(fileInClassPath("com.cz.xlsxReader.res.ftl","ftl.ftl").getParent());
		String ftlName = "ftl.ftl";
		Map<String,String> root = new LinkedHashMap<String,String>();
		root.put("metaVersion", "元信息-版本");
		FtlHelper.createFileFromFtlToConsole(ftlDir, ftlName, root);
	}
	@Test
	public void createFileFromFtlTest(){
		File ftlDir = new File(fileInClassPath("com.cz.xlsxReader.res.ftl","ftl.ftl").getParent());
		String ftlName = "ftl.ftl";
		String outputDir = XmlHelper.value(XmlHelper.getElementById("outputDir"), "value");
		String name = "nav.html";
		Map<String,String> root = new LinkedHashMap<String,String>();
		
		root.put("metaVersion", "元信息-版本");
		FtlHelper.createFileFromFtl(ftlDir, ftlName, outputDir, name, root);
		System.out.println("输出文件夹："+outputDir);
	}
}
