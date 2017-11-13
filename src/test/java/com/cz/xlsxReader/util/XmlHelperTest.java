package com.cz.xlsxReader.util;

import java.util.List;

import org.dom4j.Attribute;
import org.dom4j.Element;
import org.junit.Test;

import static com.cz.xlsxReader.util.XmlHelper.*;
public class XmlHelperTest {
	//显示一个元素的信息
		public static void showElementInfo(String id){
			Element ele = getElementById(id);
			List<Attribute> atts = ele.attributes();
			for (Attribute attribute : atts) {
				System.out.println(attribute.getName()+":"+attribute.getStringValue());
			}
		}
		
		@Test
		public void showElementInfoTest(){
			showElementInfo("xlsxDirectory");
		}
}
