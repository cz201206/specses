package com.cz.xlsxReader.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

public class FtlHelper {
	// 5.根据模板生成新文件
	public static void createFileFromFtl(File ftlDir, String ftlName, File file, Map root) {
		Configuration config = null;
		Template ftl = null;
		Writer writer = null;
		FileOutputStream out = null;

		try {
			config = new Configuration(new Version("2.3.22"));
			config.setDefaultEncoding("UTF-8");
			config.setDirectoryForTemplateLoading(ftlDir);
			ftl = config.getTemplate(ftlName);
			if (!file.exists())
				file.createNewFile();
			out = new FileOutputStream(file);
			writer = new OutputStreamWriter(out, "UTF-8");

			ftl.process(root, writer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("from " + FileHelper.class.getName() + " 异常");
		} finally {

			if (null != writer) {
				try {
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (null != out) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	// 5.根据模板生成新文件
	public static void createFileFromFtl(File ftlDir, String ftlName, String outputDir, String name, Map root) {
		Configuration config = null;
		Template ftl = null;
		Writer writer = null;
		FileOutputStream out = null;

		try {
			config = new Configuration(new Version("2.3.22"));
			config.setDefaultEncoding("UTF-8");
			config.setDirectoryForTemplateLoading(ftlDir);
			ftl = config.getTemplate(ftlName);
			out = new FileOutputStream(new File(outputDir +"/"+ name));
			writer = new OutputStreamWriter(out, "UTF-8");

			ftl.process(root, writer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("from " + FileHelper.class.getName() + " 异常");
		} finally {

			if (null != writer) {
				try {
					writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

			if (null != out) {
				try {
					out.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}

	// 5.根据模板生成新文件
	public static void createFileFromFtlToConsole(File ftlDir, String ftlName, Map root) {
		Configuration config = null;
		Template ftl = null;
		Writer writer = null;

		try {
			config = new Configuration(new Version("2.3.22"));
			config.setDefaultEncoding("UTF-8");
			config.setDirectoryForTemplateLoading(ftlDir);
			ftl = config.getTemplate(ftlName);
			writer = new OutputStreamWriter(System.out, "UTF-8");

			ftl.process(root, writer);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("from " + FileHelper.class.getName() + " 异常");
		} finally {

			if (null != writer) {
				try {
					// writer.close();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		}
	}
}
