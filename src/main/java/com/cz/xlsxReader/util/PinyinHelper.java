package com.cz.xlsxReader.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;

public class PinyinHelper {
	private static Logger logger = LogManager.getLogger(PinyinHelper.class.getName());

	//对单个汉字提取拼音
	public static String convertHanzi2Pinyin(char hanzi){

		String regExp="^[\u4E00-\u9FFF]+$";
		Pattern pattern=Pattern.compile(regExp);
		Matcher matcher=pattern.matcher(hanzi+"");
		if(matcher.find()){
			
		}else{
			//logger.error("传入的并非汉字");
			return hanzi+"";
		}
		//格式化对象
		HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();
		 outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		 String[] res;
		 StringBuffer sb=new StringBuffer();
		 try {
			 res = net.sourceforge.pinyin4j.PinyinHelper.toHanyuPinyinStringArray(hanzi,outputFormat);
			 sb.append(res[0]);//对于多音字，只用第一个拼音
		 } catch (Exception e) {
			 e.printStackTrace();
			 return "";
		 }
		 return sb.toString();	
	}
	public static String hanzisToPinyins(String hanzis){
		
		StringBuffer pinyins = new StringBuffer();
		for (char hanzi : hanzis.toCharArray()) {
			pinyins.append(convertHanzi2Pinyin(hanzi));
		}
		return pinyins.toString();
		
	}
}
