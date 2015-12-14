package com.blade.codegen.run;

import java.io.IOException;

import com.blade.codegen.CodeGenerator;

public class Gen {

	public static void main(String[] args) throws IOException {
		String classDriver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/film";
		String username = "root";
		String password = "root";
		String classPackage = "me.biezhi.film";
		String outPath = "E:/";
		// 表前缀，没有则不设置
		CodeGenerator.prefix = "t_";
		boolean flag = new CodeGenerator().generator(classDriver, url, username, password, classPackage, outPath);
		if(flag){
			Runtime.getRuntime().exec("cmd.exe /c start " + outPath + "/src");
		}
	}
	
}
