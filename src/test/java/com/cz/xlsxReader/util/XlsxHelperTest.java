package com.cz.xlsxReader.util;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class XlsxHelperTest {
	public static String pkg = "com.cz.xlsxReader.res",name = "image.xlsx";

	@Test
	public void iterateTest(){
		Workbook workbook = XlsxHelper.workbook(FileHelper.fileInClassPath(pkg,name ));
		List<List<List<String>>> wbData = XlsxHelper.iterate(workbook);
		List<List<String>> sheet1Data = wbData.get(0);
		for (List<String> rowData : sheet1Data) {
			System.out.println(rowData);
		}
	}
	@Test
	public void doByDataTypeTest(){
		Workbook workbook = XlsxHelper.workbook(FileHelper.fileInClassPath(pkg, name));
		
		XlsxHelper.doByDataType(workbook.getSheetAt(0));
	}
	@Test
	public void imagesTest(){
		Workbook workbook = XlsxHelper.workbook(FileHelper.fileInClassPath(pkg, name));
		
		XlsxHelper.imagesTo("e:/","xlsx1");
	}
}
