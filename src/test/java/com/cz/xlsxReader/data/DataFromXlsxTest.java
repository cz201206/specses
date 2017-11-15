package com.cz.xlsxReader.data;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.cz.xlsxReader.data.DataFromXlsx;

public class DataFromXlsxTest {

	public static Logger logger = LogManager.getLogger(DataFromXlsxTest.class.getName());
	@Test
	public void allTest(){
		DataFromXlsx dataFromXlsx = new DataFromXlsx();
		List<List<String>> data = dataFromXlsx.all();
		logger.info(data);
	}
}
