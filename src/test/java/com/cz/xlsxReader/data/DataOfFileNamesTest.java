package com.cz.xlsxReader.data;

import java.util.List;
import java.util.Set;

import org.junit.Test;

public class DataOfFileNamesTest {
	@Test
	public void fileNamesTest(){
		List<String> fileNames = new DataOfFileNames().fileNames();
		for (String string : fileNames) {
			System.out.println(string);
		}
	}
	//去掉参数后边的汉字
	@Test
	public void fileNamesSubStringVersionTest(){
		List<String> fileNames = new DataOfFileNames().fileNamesSubStringVersion();
		for (String string : fileNames) {
			System.out.println(string);
		}
		System.out.println("总数："+fileNames.size());
	}
	//去掉参数后边的汉字且去重
	@Test
	public void fileNamesSubStringUniqueVersionTest(){
		Set<String> fileNames = new DataOfFileNames().fileNamesSubStringUniqueVersion();
		for (String string : fileNames) {
			System.out.println(string);
		}
		System.out.println("总数："+fileNames.size());
	}
	//去掉参数后边的汉字+去重+去除特殊字符
	@Test
	public void fileNamesSubStringUniqueDSpaceVersionTest(){
		Set<String> fileNames = new DataOfFileNames().fileNamesSubStringUniqueDSpaceVersion();
		for (String string : fileNames) {
			System.out.println(string);
		}
		System.out.println("总数："+fileNames.size());
	}
}
