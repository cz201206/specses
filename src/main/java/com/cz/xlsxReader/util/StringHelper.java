package com.cz.xlsxReader.util;

import org.apache.commons.lang3.StringUtils;

public class StringHelper {
	
	//删除某些字符
	public static String deleteChars(String str,String chars){
		for (char c : chars.toCharArray()) {
			str = StringUtils.replace(str,c+"","");
		}
		
		return str;
	}
}
