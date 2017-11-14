package com.cz.xlsxReader.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class XlsxHelper {
	
	//记录日志
	private static Logger logger = LogManager.getLogger(XlsxHelper.class.getName());
	
	private static Workbook workbook;
	private static Sheet sheet;
	//所有sheet页
	public static Workbook workbook(File xlsx){	
		try {
			workbook = WorkbookFactory.create(xlsx);
		} catch (Exception e) {
			logger.error("打开xlsx文件出错");
			e.printStackTrace();
		}
		return workbook;
	}
	//单个sheet页
	public static Sheet sheet(int index){
		sheet = workbook.getSheetAt(index);
		return sheet;
	}
	public static List<String> row(Sheet sheet,int ...rowIndex_fieldCount){
		
		//返回值容器
		List<String> list = new ArrayList<String>();
		
		Row row = sheet.getRow(rowIndex_fieldCount[0]);
		for(int i = 0;i< rowIndex_fieldCount[1];i++){
			Cell cell = row.getCell(i);
			list.add(cell+"");
		}
		
		return list;
	}
}
