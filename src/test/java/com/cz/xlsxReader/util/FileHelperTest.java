package com.cz.xlsxReader.util;
import static com.cz.xlsxReader.util.FileHelper.fileInClassPath;

import java.io.File;

import org.junit.Test;
public class FileHelperTest {
	@Test 
	public void fileInClassPathTest(){
		File conf = fileInClassPath("com.cz.xlsxReader","conf.xml");
		System.out.println(conf);
	}
}
