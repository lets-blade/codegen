package com.blade.codegen;

import com.blade.codegen.core.CodeGenerator;
import com.blade.codegen.model.DBMeta;
import com.blade.codegen.model.ProjectMeta;

import java.io.IOException;

public class Application {

    public static void main(String[] args) throws IOException {
        DBMeta dbMeta = new DBMeta()
                .setDriver("com.mysql.jdbc.Driver")
                .setUrl("jdbc:mysql://127.0.0.1:3306/demo")
                .setDbname("demo")
                .setUser("root")
                .setPass("123456")
                .setTableName("%");
        //.setPrefix("ss_");

        ProjectMeta projectMeta = new ProjectMeta()
                .setName("demo")
                .setPkgName("com.demo")
                .setHump(true)
                .setOutPath("/Users/biezhi/workspace/temp")
                .setDbMeta(dbMeta);

        new CodeGenerator(projectMeta).generator();
    }

}
