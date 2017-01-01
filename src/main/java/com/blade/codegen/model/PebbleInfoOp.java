package com.blade.codegen.model;

import com.mitchellbosecke.pebble.PebbleEngine;
import com.mitchellbosecke.pebble.template.PebbleTemplate;

import java.io.*;
import java.util.Map;

/**
 * 模板操作类
 * @ClassName: CodeGenerator
 * @author AndrewWen
 * @date 2013-1-14 下午5:25:30
 */
public class PebbleInfoOp {

	private static PebbleEngine engine = new PebbleEngine.Builder().build();

	public static void generatorCode(String templateFile, Map<String, Object> contextMap, String path, String fileName) {

		try {
			PebbleTemplate compiledTemplate = engine.getTemplate(templateFile);
			Writer writer = new StringWriter();
			compiledTemplate.evaluate(writer, contextMap);
			String output = writer.toString();

			//生成目录
			File pathTemp = new File(path);
			if (!pathTemp.exists()) {
				pathTemp.mkdirs();
			}

			writeFile(pathTemp + "/" + fileName, output);

		} catch (Exception e){
			System.out.println("文件生成失败：" + e.getMessage());
		}

	}

	/**
	 * 写入文件
	 * @Title: writeFile 
	 * @param filePathAndName
	 * @param fileContent
	 */
	public static void writeFile(String filePathAndName, String fileContent) {
		try {
			File f = new File(filePathAndName);
			if (!f.exists()) {
				f.createNewFile();
			}
			OutputStreamWriter write = new OutputStreamWriter(new FileOutputStream(f), "UTF-8");
			BufferedWriter writer = new BufferedWriter(write);
			writer.write(fileContent);
			writer.close();
		} catch (Exception e) {
			System.out.println("写文件内容操作出错");
			e.printStackTrace();
		}
	}

}
