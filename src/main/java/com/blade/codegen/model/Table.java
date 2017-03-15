package com.blade.codegen.model;

import com.blade.codegen.core.CodeGenerator;
import com.blade.codegen.utils.StringUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 表实体
 */
public class Table {

    /**
     * 数据库
     */
    private String tableSchem;

    /**
     * 表名
     */
    private String tableName;

    /**
     * 表注释
     */
    private String tableComment;

    /**
     * 主键名称
     */
    private String pkName;

    /**
     * 列
     */
    private List<Column> columnList = new ArrayList<Column>();

    /**
     * 包名
     */
    private String packageName;

    /**
     * 包路径
     */
    private String packagePath;

    /**
     * 类名
     */
    private String className;

    /**
     * 类名首字母小写
     */
    private String classNameFirstLower;

    /**
     * 是否是驼峰式命名
     */
    private boolean isHump;

    /**
     * 获得第一个主键
     *
     * @return
     * @Title: getFirstPrimaryKey
     */
    public Column getFirstPrimaryKey() {

        for (Column tempCul : this.getColumnList()) {
            if (tempCul.isPrimary()) {
                return tempCul;
            }
        }
        return null;

    }

    /**
     * 获得类小写头名字
     */
    public String getLowerDomainClassName() {
        return StringUtil.getClassLower(this.getClassName());
    }

    /************************* get set *************************/

    public String getPackagePath() {
        this.packagePath = this.getPackageName().replace(".", "/");
        return packagePath;
    }

    public void setPackagePath(String packagePath) {
        this.packagePath = packagePath;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getClassName() {
        if (CodeGenerator.prefix.equals("")) {
            // 直接输出
            this.className = StringUtil.getClassUpper(StringUtil.getDomainColumnName(this.tableName));
        } else {
            // 去除前缀
            if (this.tableName.startsWith(CodeGenerator.prefix)) {
                int pos = this.tableName.indexOf(CodeGenerator.prefix) + CodeGenerator.prefix.length();
                this.className = StringUtil.getClassUpper(StringUtil.getDomainColumnName(this.tableName.substring(pos, this.tableName.length())));
            } else if (this.tableName.indexOf("_") != -1) {
                this.className = StringUtil.getClassUpper(StringUtil
                        .getDomainColumnName(this.tableName.substring(this.tableName.indexOf("_") + 1, this.tableName.length())));
            }
        }

        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTableComment() {
        return tableComment;
    }

    public void setTableComment(String tableComment) {
        this.tableComment = tableComment;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<Column> getColumnList() {
        return columnList;
    }

    public void setColumnList(List<Column> columnList) {
        this.columnList = columnList;
    }

    public String getTableSchem() {
        return tableSchem;
    }

    public void setTableSchem(String tableSchem) {
        this.tableSchem = tableSchem;
    }

    public String getClassNameFirstLower() {
        this.classNameFirstLower = StringUtil.getClassLower(this.getClassName());
        return classNameFirstLower;
    }

    public void setClassNameFirstLower(String classNameFirstLower) {
        this.classNameFirstLower = classNameFirstLower;
    }

    public String getPkName() {
        return pkName;
    }

    public void setPkName(String pkName) {
        this.pkName = pkName;
    }

    public boolean isHump() {
        return isHump;
    }

    public void setHump(boolean hump) {
        isHump = hump;
    }

    @Override
    public String toString() {
        return "Table [" +
                "tableSchem='" + tableSchem + '\'' +
                ", tableName='" + tableName + '\'' +
                ", tableComment='" + tableComment + '\'' +
                ", pkName='" + pkName + '\'' +
                ", columnList=" + columnList +
                ", packageName='" + packageName + '\'' +
                ", packagePath='" + packagePath + '\'' +
                ", className='" + className + '\'' +
                ", classNameFirstLower='" + classNameFirstLower + '\'' +
                ", isHump=" + isHump +
                ']';
    }
}
