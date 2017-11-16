package com.cz.xlsxReader.util;

import org.junit.Test;

public class StringHelperTest {
	@Test
	public void deleteCharsTest(){
		String str = StringHelper.deleteChars("AirPOP Light 360°贴合防雾霾(口罩)参数及竞品对比表V01", " ()°");
		System.out.println(str);
	}
}
