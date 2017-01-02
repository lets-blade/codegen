# codegen

This project is based on the blade framework code generator, according to the database to generate Java classes, generating code Model and the Service.

## Usage

```java
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
    new CodeGenerator(projectMeta).generator();
}
```

