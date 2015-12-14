# codegen

这个项目是基于blade框架的代码生成器，根据数据库生成Java类，生成Model和Service代码。

## 使用方法

```java
public static void main(String[] args) throws IOException {
	String classDriver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://127.0.0.1:3306/hello";
	String username = "root";
	String password = "root";
	String classPackage = "com.xxx";
	String outPath = "E:/";
	// 表前缀，没有则不设置
	CodeGenerator.prefix = "i_";
	boolean flag = new CodeGenerator().generator(classDriver, url, username, password, classPackage, outPath);
	if(flag){
		Runtime.getRuntime().exec("cmd.exe /c start " + outPath + "/src");
	}
}
```

