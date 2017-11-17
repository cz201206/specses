package com.cz.xlsxReader.data;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.cz.xlsxReader.pojo.Specs;
import com.cz.xlsxReader.util.FileHelper;
import com.cz.xlsxReader.util.XlsxHelper;
import com.cz.xlsxReader.util.XmlHelper;

public class DataFromXlsx {
	private static Logger logger = LogManager.getLogger(DataFromXlsx.class.getName());
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
	public List<List<String> > all(File file){
		
		List<List<String> > rows = new ArrayList<List<String> >();
		//总行数 ，需要
		int rowCount = 0;
		
		//载入xlsx文件
		File xlsx = file;
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
	/**
	 * 
	 * @param file xlsx文件对象
	 * @return name-title 
	 * 2017年11月17日 上午10:04:51
	 */
	public List<Specs> specs(File file){
		//区别参数表中两列还是三列式的参数形式
		boolean is2Field = true;
		List<Specs> specses = new ArrayList<Specs>();
		//总行数 ，需要
		int rowCount = 0;
		
		//载入xlsx文件
		File xlsx = file;
		//取得全部sheet
		XlsxHelper.workbook(xlsx);
		//取得指定sheet
		Sheet sheet = XlsxHelper.sheet(0);
		//判断是两列还是三列形式的参数表默认是两列，三列则返回假
		if(!check(sheet)){
			is2Field = false;
			logger.info("三列式:"+file.getName());
		}
		//取得总行数
		rowCount = sheet.getLastRowNum();
		for (int i = 0; i < rowCount; i++) {
			Specs specs = new Specs();
			//获取sheet中所有行中数据，int参数分别 为rowIndex和fieldCount
			List<String> row = XlsxHelper.row(sheet, i,fieldCount);
			if(row.size()<2)continue;
			//区分两列还是三列的参数表	
			if(is2Field){
				specs.key = row.get(0);
				specs.value = row.get(1);
			}else{
				specs.key = row.get(1);
				specs.value = row.get(2);
			}
			//将一行加入到总容器中
			specses.add(specs);
		}
		
		return specses;
	}
	private boolean check(Sheet sheet){
		/*List<Row> rows = new ArrayList<Row>();
		Set<>*/
		int rowsNum = 3;
		for (int i = 0; i < rowsNum; i++) {
			Object cellValue = sheet.getRow(i).getCell(0,MissingCellPolicy.RETURN_BLANK_AS_NULL);
			if(null==cellValue){
				return false;
			}else{
				System.out.println(cellValue);
			}
		}
		return true;
		
	}
}
