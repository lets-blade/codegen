package com.blade.codegen;

import java.io.IOException;

import com.blade.codegen.core.CodeGenerator;
import com.blade.codegen.model.DBMeta;
import com.blade.codegen.model.ProjectMeta;

public class Application {

	public static void main(String[] args) throws IOException {

		DBMeta dbMeta = new DBMeta();
		dbMeta.setDriver("com.mysql.jdbc.Driver");
		dbMeta.setUrl("jdbc:mysql://127.0.0.1:3306/demo1");
		dbMeta.setDbname("demo1");
		dbMeta.setUser("root");
		dbMeta.setPass("123456");

		ProjectMeta projectMeta = new ProjectMeta();
		projectMeta.setName("demo1");
		projectMeta.setPkgName("com.demo1");
		projectMeta.setOutPath("/Users/biezhi/workspace/temp");
		projectMeta.setDbMeta(dbMeta);

		// 表前缀，没有则不设置
		CodeGenerator.prefix = "t_";
		boolean flag = new CodeGenerator(projectMeta).generator();
		if(flag){
//			Runtime.getRuntime().exec("cmd.exe /c start " + projectMeta.getOutPath());
		}
	}
	
}
