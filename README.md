# codegen

This project is based on the blade framework code generator, according to the database to generate Java classes, generating code Model and the Service.

## Usage

```java
public static void main(String[] args) throws IOException {
	String classDriver = "com.mysql.jdbc.Driver";
	String url = "jdbc:mysql://127.0.0.1:3306/hello";
	String username = "root";
	String password = "root";
	String classPackage = "com.xxx";
	String outPath = "E:/";
	// Table prefix, no is not set
	CodeGenerator.prefix = "i_";
	boolean flag = new CodeGenerator().generator(classDriver, url, username, password, classPackage, outPath);
	if(flag){
		Runtime.getRuntime().exec("cmd.exe /c start " + outPath + "/src");
	}
}
```

