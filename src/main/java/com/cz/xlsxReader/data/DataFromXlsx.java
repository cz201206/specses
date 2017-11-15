package com.cz.xlsxReader.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.cz.xlsxReader.util.FileHelper;
import com.cz.xlsxReader.util.XlsxHelper;
import com.cz.xlsxReader.util.XmlHelper;

public class DataFromXlsx {
	private int fieldCount;
	public DataFromXlsx() {
		//需要获取字段量
		fieldCount = Integer.valueOf(XmlHelper.value(XmlHelper.getElementByName("xlsxes"), "fieldCount"));
	}
	public List<List<String> > all(){
		
		List<List<String> > rows = new ArrayList<List<String> >();
		//总行数 ，需要
		int rowCount = 0;
		
		//载入xlsx文件
		File xlsx = FileHelper.fileInClassPath("com.cz.xlsxReader.res", "image.xlsx");
		//取得全部sheet
		XlsxHelper.workbook(xlsx);
		//取得指定sheet
		Sheet sheet = XlsxHelper.sheet(0);
		//取得总行数
		rowCount = sheet.getLastRowNum();
		for (int i = 0; i < rowCount; i++) {
			//获取sheet中所有行中数据，int参数分别 为rowIndex和fieldCount
			List<String> row = XlsxHelper.row(sheet, i,fieldCount);
			//将一行加入到总容器中
			rows.add(row);
		}
		
		return rows;
	}
}
