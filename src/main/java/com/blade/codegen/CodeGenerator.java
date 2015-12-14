package com.blade.codegen;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.blade.codegen.model.Database;
import com.blade.codegen.model.Table;

import blade.kit.log.Logger;

/**
 * 代码生成类
 */
public class CodeGenerator {

	private Logger logger = Logger.getLogger(CodeGenerator.class);
	
	//是否去掉模块前面的编号
	public static String prefix = "";
	
	public boolean generator(final String classDriver, final  String url, final String username, final String password, 
			final String classPackage, final String codePath) {

		String sourcePath = codePath + File.separator + "src/";
		Long start = System.currentTimeMillis();
		try {
			// 获取数据库名
			String schema = url.substring(url.lastIndexOf("/") + 1);
			
			// 获取数据库信息
			Database databaseBean = new DatabaseInfoOp(classDriver, url, username, password, schema).getDbInfo();
			
			// 获取该库所有表
			List<Table> tableList = databaseBean.getTableList();
			
			logger.info("---------------start---------------");
			
			/**
			 * 遍历生成代码
			 */
			for (Table table : tableList) {

				table.setPackageName(classPackage);
				
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("table", table);
				
				System.out.println(table);
				
				VelocityInfoOp.generatorCode("model.vm", map, sourcePath + table.getPackagePath() + "/model", table.getClassName() + ".java");
				VelocityInfoOp.generatorCode("service.vm", map, sourcePath + table.getPackagePath() + "/service", table.getClassName() + "Service.java");
				VelocityInfoOp.generatorCode("serviceimpl.vm", map, sourcePath + table.getPackagePath() + "/service/impl", table.getClassName() + "ServiceImpl.java");
				
				logger.info("表：" + table.getTableName() + "成功");
			}

			logger.info("--------------- end time：" + (System.currentTimeMillis() - start) + "ms-----");
			logger.info("代码路径：" + codePath);
			logger.info("包：" + classPackage);
			logger.info("------------------------------------");
			return true;
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return false;
	}
	
}
