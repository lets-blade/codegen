package com.blade.codegen.model;

/**
 * Created by biezhi on 2017/1/2.
 */
public class ProjectMeta {

    /**
     * 项目名
     */
    private String name;

    /**
     * 基础包名
     */
    private String pkgName;

    /**
     * 输出路径
     */
    private String outPath;

    /**
     * 数据库信息
     */
    private DBMeta dbMeta;

    /**
     * 是否生成驼峰式命名
     */
    private boolean hump;

    public String getName() {
        return name;
    }

    public ProjectMeta setName(String name) {
        this.name = name;
        return this;
    }

    public String getPkgName() {
        return pkgName;
    }

    public ProjectMeta setPkgName(String pkgName) {
        this.pkgName = pkgName;
        return this;
    }

    public String getOutPath() {
        return outPath;
    }

    public ProjectMeta setOutPath(String outPath) {
        this.outPath = outPath;
        return this;
    }

    public DBMeta getDbMeta() {
        return dbMeta;
    }

    public ProjectMeta setDbMeta(DBMeta dbMeta) {
        this.dbMeta = dbMeta;
        return this;
    }

    public boolean isHump() {
        return hump;
    }

    public ProjectMeta setHump(boolean hump) {
        this.hump = hump;
        return this;
    }
}
