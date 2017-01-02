package com.blade.codegen;

import java.io.IOException;

import com.blade.codegen.core.CodeGenerator;
import com.blade.codegen.model.DBMeta;
import com.blade.codegen.model.ProjectMeta;

public class Application {

	public static void main(String[] args) throws IOException {
		DBMeta dbMeta = new DBMeta()
				.setDriver("com.mysql.jdbc.Driver")
				.setUrl("jdbc:mysql://127.0.0.1:3306/demo1")
				.setDbname("demo1")
				.setUser("root")
				.setPass("123456")
				.setTableName("%")
				.setPrefix("t_");

		ProjectMeta projectMeta = new ProjectMeta()
				.setName("demo1")
				.setPkgName("com.demo1")
				.setOutPath("/Users/biezhi/workspace/temp")
				.setDbMeta(dbMeta);

		new CodeGenerator(projectMeta).generator();
	}
	
}
