package com.blade.codegen.model;

/**
 * Created by biezhi on 2017/1/2.
 */
public class DBMeta {

    private String dbname;
    private String driver;
    private String url;
    private String user;
    private String pass;
    private String tableName;
    private String prefix = "";

    public String getDriver() {
        return driver;
    }

    public DBMeta setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    public String getUrl() {
        return url;
    }

    public DBMeta setUrl(String url) {
        this.url = url;
        return this;
    }

    public String getUser() {
        return user;
    }

    public DBMeta setUser(String user) {
        this.user = user;
        return this;
    }

    public String getPass() {
        return pass;
    }

    public DBMeta setPass(String pass) {
        this.pass = pass;
        return this;
    }

    public String getDbname() {
        return dbname;
    }

    public DBMeta setDbname(String dbname) {
        this.dbname = dbname;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public DBMeta setTableName(String tableName) {
        this.tableName = tableName;
        return this;
    }

    public String getPrefix() {
        return prefix;
    }

    public DBMeta setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }
}
