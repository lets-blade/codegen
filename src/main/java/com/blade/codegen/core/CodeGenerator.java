package com.blade.codegen.core;

import blade.kit.logging.Logger;
import blade.kit.logging.LoggerFactory;
import com.blade.codegen.model.*;
import com.blade.codegen.utils.FileUtil;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 代码生成类
 */
public class CodeGenerator {

	private Logger logger = LoggerFactory.getLogger(CodeGenerator.class);
	
	public static String prefix = "";

	private ProjectMeta projectMeta;
	private DBMeta dbMeta;

	private String rootPath;
	private String pkgPath;

	public CodeGenerator(ProjectMeta projectMeta){
		this.projectMeta = projectMeta;
		this.dbMeta = projectMeta.getDbMeta();
		this.rootPath = projectMeta.getOutPath() + File.separator + projectMeta.getName();
		this.pkgPath = rootPath + "/src/main/java/" + projectMeta.getPkgName().replace(".", "/");
		this.prefix = dbMeta.getPrefix();
	}

	public boolean generator(){

		try {
			Long start = System.currentTimeMillis();

			// 构建项目骨架
			this.genProjectFramework();

			// 生成配置文件
			this.genConfigFile();

			// 生成代码和前端代码
			String url = dbMeta.getUrl();
			String driver = dbMeta.getDriver();
			String user = dbMeta.getUser();
			String pass = dbMeta.getPass();
			// 获取数据库名
			String dbname = dbMeta.getDbname();

			// 获取数据库信息
			Database databaseBean = new DatabaseInfoOp(driver, url, user, pass, dbname).getDbInfo();

			// 获取该库所有表
			List<Table> tableList = databaseBean.getTableList();

			logger.info("---------------start---------------");

			for (Table table : tableList) {
				if(dbMeta.getTableName().equals("%") || dbMeta.getTableName().equals(table.getTableName())){
					this.genModel(table);
				}
			}
			logger.info("--------------- end time：" + (System.currentTimeMillis() - start) + "ms-----");
			logger.info("output path：" + rootPath);
			logger.info("project name：" + projectMeta.getName());
			logger.info("package name：" + projectMeta.getPkgName());
			logger.info("------------------------------------");
			return true;
		} catch (Exception e) {
			logger.error("", e);
		}
		return false;
	}

	/**
	 * 生成配置文件
	 */
	private void genConfigFile() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("projectname", projectMeta.getName());
		map.put("pkgname", projectMeta.getPkgName());
		map.put("dbmeta", dbMeta);

		String temResPath = CodeGenerator.class.getResource("/template/src/main/resources").getPath();

		PebbleInfoOp.generatorCode(CodeGenerator.class.getResource("/template/pom.xml.html").getPath(), map, rootPath, "pom.xml");
		PebbleInfoOp.generatorCode(temResPath + "/log4j.properties.html", map, rootPath + "/src/main/resources", "log4j.properties");
		PebbleInfoOp.generatorCode(temResPath + "/druid.properties.html", map, rootPath + "/src/main/resources", "druid.properties");

		String javaPath = CodeGenerator.class.getResource("/template/src/main/java").getPath();

		PebbleInfoOp.generatorCode(javaPath + "/Application.html", map, pkgPath, "Application.java");
		PebbleInfoOp.generatorCode(javaPath + "/config/DBConfig.html", map, pkgPath + "/config", "DBConfig.java");
		PebbleInfoOp.generatorCode(javaPath + "/config/TemplateConfig.html", map, pkgPath + "/config", "TemplateConfig.java");
		PebbleInfoOp.generatorCode(javaPath + "/exception/TipException.html", map, pkgPath + "/exception", "TipException.java");
		PebbleInfoOp.generatorCode(javaPath + "/controller/IndexController.html", map, pkgPath + "/controller", "IndexController.java");
	}

	private void genModel(Table table){
		String javaPath = CodeGenerator.class.getResource("/template/src/main/java").getPath();

		table.setPackageName(projectMeta.getPkgName());
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("table", table);
		PebbleInfoOp.generatorCode(javaPath + "/model/model.html", map, pkgPath + "/model", table.getClassName() + ".java");
		PebbleInfoOp.generatorCode(javaPath + "/service/service.html", map, pkgPath + "/service", table.getClassName() + "Service.java");
		PebbleInfoOp.generatorCode(javaPath + "/service/impl/serviceimpl.html", map, pkgPath + "/service/impl", table.getClassName() + "ServiceImpl.java");
		PebbleInfoOp.generatorCode(javaPath + "/controller/api/controller.html", map, pkgPath + "/controller/api", table.getClassName() + "Controller.java");
		logger.info("表：" + table.getTableName() + "成功");
	}

	/**
	 * 生成项目骨架
	 */
	private void genProjectFramework() {
		File root = new File(rootPath);
		if(root.exists()){
			root.delete();
		}
		root.mkdirs();

		new File(pkgPath).mkdirs();

		String javaPath = rootPath + "/src/main/java";
		new File(javaPath).mkdirs();

		String staticPath = rootPath + "/src/main/resources/static";
		new File(staticPath).mkdirs();

		String configPath = pkgPath + "/config";
		new File(configPath).mkdir();

		String controllerPath = pkgPath + "/controller";
		new File(controllerPath).mkdir();

		String servicePath = pkgPath + "/service";
		new File(servicePath).mkdir();

		String modelPath = pkgPath + "/model";
		new File(modelPath).mkdir();

		// 复制静态文件
		try {
			String staticDirPath = CodeGenerator.class.getResource("/template/src/main/resources/static").getPath();
			FileUtil.copyFolder(new File(staticDirPath), new File(rootPath + "/src/main/resources/static"));
		} catch (Exception e){
			logger.error("", e);
		}
	}

}
