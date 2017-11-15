package com.cz.xlsxReader.util;

import static com.cz.xlsxReader.util.FileHelper.files;

import java.io.File;

import org.junit.Test;

import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinHelperTest {
	@Test
	public void convertHanzi2PinyinTest(){
		String pinyin = PinyinHelper.convertHanzi2Pinyin('a');
		System.out.println(pinyin);
	}
	@Test
	public void hanzisToPinyinsTest(){
		String pinyins = PinyinHelper.hanzisToPinyins("（中华人民共和国");
		System.out.println(pinyins);
	}
	@Test
	public void xlsxFilesNameToPinyin(){
		File[] files = files("F:/QQ/智能家庭产品参数表汇总");
		//遍历所有文件
		for (int i = 0; i < files.length; i++) {
			System.out.println(
					PinyinHelper.hanzisToPinyins(i+1+"."+files[i].getName())
					);
		}
	}
	@Test
	public void xlsxFilesNameToPinyin2() throws BadHanyuPinyinOutputFormatCombination{
		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		File[] files = files("F:/QQ/智能家庭产品参数表汇总");
		//遍历所有文件
		for (int i = 0; i < files.length; i++) {
			String pinyins = net.sourceforge.pinyin4j.PinyinHelper.toHanYuPinyinString("."+files[i].getName(),outputFormat, "", false);
			System.out.println(i+1+"."+pinyins);
		}
	}
}
