package com.cz.xlsxReader.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.PictureData;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Shape;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellReference;

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
	/**
	 * 遍历表格
	 */
	public static List<List<List<String>>> iterate(Workbook wb){
		List<List<List<String>>> wbData = new ArrayList<List<List<String>>>();
		for (Sheet sheet : wb ) {
			List<List<String>> sheetData = new ArrayList<List<String>>();
	        for (Row row : sheet) {
	        	List<String> rowData = new ArrayList<String>();
	            for (Cell cell : row) {
	            	rowData.add(cell+"");
	            }//一行数据获取完成
	            sheetData.add(rowData);
	        }//一个表的数据完成
	        wbData.add(sheetData);
	    }
		return wbData;
	}
	/**
	 * 根据单元格数据类型进行数据处理
	 */
	public static void doByDataType(Sheet sheet){
		DataFormatter formatter = new DataFormatter();
		for (Row row : sheet) {
	        for (Cell cell : row) {
	            CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
	            System.out.print(cellRef.formatAsString());
	            System.out.print(" - ");

	            // get the text that appears in the cell by getting the cell value and applying any data formats (Date, 0.00, 1.23e9, $1.23, etc)
	            String text = formatter.formatCellValue(cell);
	            System.out.println(text);

	            // Alternatively, get the value and format it yourself
	            switch (cell.getCellTypeEnum()) {
	                case STRING:
	                    System.out.println(cell.getRichStringCellValue().getString());
	                    break;
	                case NUMERIC:
	                    if (DateUtil.isCellDateFormatted(cell)) {
	                        System.out.println(cell.getDateCellValue());
	                    } else {
	                    	BigDecimal bd = new BigDecimal(cell.getNumericCellValue());
	                        System.out.println(bd.toPlainString());
	                    }
	                    break;
	                case BOOLEAN:
	                    System.out.println(cell.getBooleanCellValue());
	                    break;
	                case FORMULA:
	                    System.out.println(cell.getCellFormula());
	                    break;
	                case BLANK:
	                    System.out.println();
	                    break;
	                default:
	                    System.out.println();
	            }
	        }
	    }
	}
	/**
	 * 获取一行的数据
	 */
	public static List<String> row(Sheet sheet,int ...rowIndex_fieldCount){
		
		//返回值容器
		List<String> list = new ArrayList<String>();
		
		Row row = sheet.getRow(rowIndex_fieldCount[0]);
		for(int i = 0;i< rowIndex_fieldCount[1];i++){
			if(null==row)continue;
			Cell cell = row.getCell(i);
			list.add(cell+"");
		}
		
		return list;
	}
	/**
	 * 
	 * @param dir 图片存储位置
	 * @param name 图片统一名称
	 * @return
	 * 2017年11月16日 上午10:22:37
	 */
	public static List imagesTo(String dir,String name){
		//所有图片文件
		List list = workbook.getAllPictures();
		//指数变量
		int i=0;
	    for (Iterator it = list.iterator(); it.hasNext(); ) {
	    	//取得图片数据
	        PictureData pict = (PictureData)it.next();
	        //取得图片扩展名
	        String ext = pict.suggestFileExtension();
	        //取得图片二进制数据
	        byte[] data = pict.getData();

	        //图片完整路径
	        String path = null;
	        FileOutputStream out;
	        //构造图片名称
	        if (ext.equals("jpeg")){
	        	path =  dir+"/"+name+(++i)+".jpg";
	        }else if(ext.equals("png")){
	        	path =  dir+"/"+name+(++i)+".png";
	        }
	        //定入文件
			try {
				out = new FileOutputStream(path);
				out.write(data);
		        out.close();
			} catch (Exception e) {
				logger.error("写入或者关闭图片文件失败");
				e.printStackTrace();
			}
	    }
		return list;
	}
	/**
	 * 
	 * @param dir 图片存储位置
	 * @param name 图片统一名称
	 * @param n 提取n个图片
	 * @return
	 * 2017年11月16日 上午10:21:52
	 */
	public static List imageNTo(String dir,String name,int n){
		//所有图片文件
		List list = workbook.getAllPictures();
		//指数变量
		int i=0;
		for (Iterator it = list.iterator(); it.hasNext(); ) {
			//取得图片数据
			PictureData pict = (PictureData)it.next();
			//取得图片扩展名
			String ext = pict.suggestFileExtension();
			//取得图片二进制数据
			byte[] data = pict.getData();
			
			//图片完整路径
			String path = null;
			FileOutputStream out;
			//构造图片名称
			if (ext.equals("jpeg")){
				path =  dir+"/"+name+".jpg";
			}else if(ext.equals("png")){
				path =  dir+"/"+name+".png";
			}
			//定入文件
			try {
				out = new FileOutputStream(path);
				out.write(data);
				out.close();
			} catch (Exception e) {
				logger.error("写入或者关闭图片文件失败");
				e.printStackTrace();
			}
			//提取达到N张后即返回
			if(i==n)return list;
		}
		return list;
	}
}
