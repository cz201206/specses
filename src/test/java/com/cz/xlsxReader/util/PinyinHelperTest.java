package com.cz.xlsxReader.util;

import org.junit.Test;

public class PinyinHelperTest {
	@Test
	public void convertHanzi2PinyinTest(){
		String pinyin = PinyinHelper.convertHanzi2Pinyin('闫');
		System.out.println(pinyin);
	}
	@Test
	public void hanzisToPinyinsTest(){
		String pinyins = PinyinHelper.hanzisToPinyins("绿滤s路");
		System.out.println(pinyins);
	}
	@Test
	public void hanzis2PinyinsTest(){
		String pinyins = PinyinHelper.hanzis2Pinyins("（绿滤s路");
		System.out.println(pinyins);
	}
	@Test
	public void initialTest(){
		String pinyins = PinyinHelper.initial("（绿滤s路");
		System.out.println(pinyins);
	}
	 
}
