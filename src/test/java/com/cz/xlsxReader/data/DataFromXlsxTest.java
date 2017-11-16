package com.cz.xlsxReader.data;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.cz.xlsxReader.data.DataFromXlsx;
import com.cz.xlsxReader.pojo.Specs;
import com.cz.xlsxReader.util.FileHelper;

public class DataFromXlsxTest {

	public static Logger logger = LogManager.getLogger(DataFromXlsxTest.class.getName());
	@Test
	public void allTest(){
		DataFromXlsx dataFromXlsx = new DataFromXlsx();
		List<List<String>> data = dataFromXlsx.all();
		logger.info(data);
	}
	@Test
	public void specsTest(){

		//载入xlsx文件
		File xlsx = FileHelper.fileInClassPath("com.cz.xlsxReader.res", "image.xlsx");
		DataFromXlsx dataFromXlsx = new DataFromXlsx();
		List<Specs> specses = dataFromXlsx.specs(xlsx);
		logger.info(specses);
	}
}
