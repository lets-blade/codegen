package com.blade.codegen.run;

import java.io.IOException;

import com.blade.codegen.CodeGenerator;

public class Gen {

	public static void main(String[] args) throws IOException {
		String classDriver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://127.0.0.1:3306/blog";
		String username = "root";
		String password = "root";
		String classPackage = "com.xxx";
		String outPath = "E:/";
		
		boolean flag = new CodeGenerator().generator(classDriver, url, username, password, classPackage, outPath);
		if(flag){
			Runtime.getRuntime().exec("cmd.exe /c start " + outPath + "/src");
		}
	}
	
}
