# codegen

This project is based on the blade framework code generator, according to the database to generate Java classes, generating code Model and the Service.

## Usage

```java
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
            .setHump(true)
            .setOutPath("/Users/biezhi/workspace/temp")
            .setDbMeta(dbMeta);

    new CodeGenerator(projectMeta).generator();
}
```

