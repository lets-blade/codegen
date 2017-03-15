package com.blade.codegen.utils;

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

/**
 * 字符串处理工具类
 *
 * @author Andrew.Wen
 * @ClassName: StringUtil
 * @date 2013-1-10 下午3:36:22
 */
public class StringUtil {

    /**
     * 获得类首字母小写
     *
     * @param className
     * @return
     */
    public static String getClassLower(String className) {
        return className.substring(0, 1).toLowerCase() + className.substring(1);
    }

    /**
     * 获得类首字母大写
     *
     * @param className
     * @return
     */
    public static String getClassUpper(String className) {
        return className.substring(0, 1).toUpperCase() + className.substring(1);
    }

    /**
     * 获得set 方法
     *
     * @param field 字段名称
     * @return
     * @Title: getSetMethod
     */
    public static String getSetMethod(String field) {
        String methodStr = "";

        methodStr = "set";

        if ((field == null) || (field == "")) {
            return "";
        } else {
            field = methodStr + field.substring(0, 1).toUpperCase()
                    + field.substring(1);

            return field;
        }
    }

    /**
     * 获得get 方法
     *
     * @param field 字段名称
     * @return
     * @Title: getGetMethod
     */
    public static String getGetMethod(String field) {
        String methodStr = "";

        methodStr = "get";

        if ((field == null) || (field == "")) {
            return "";
        } else {
            field = methodStr + field.substring(0, 1).toUpperCase() + field.substring(1);
            return field;
        }
    }

    /**
     * 去掉下划线，将下划线后首字母大写
     *
     * @param databaseColumn
     * @return
     * @Title: getDomainColumnName
     */
    public static String getDomainColumnName(String databaseColumn) {

        if ((databaseColumn == null) || (databaseColumn == "")) {
            return "";
        } else {

            int _postion = -1;

            while (databaseColumn.indexOf("_") > 0) {
                _postion = databaseColumn.indexOf("_");
                if (_postion < databaseColumn.length() - 1) {
                    databaseColumn = databaseColumn.substring(0, _postion)
                            + databaseColumn.substring(_postion + 1,
                            _postion + 2).toUpperCase()
                            + databaseColumn.substring(_postion + 2,
                            databaseColumn.length());
                } else {
                    databaseColumn = databaseColumn.replace("_", "");
                }
            }

        }

        return databaseColumn;

    }

    public static String getColumnType(int databaseType) {
        String colType = "";

        switch (databaseType) {
            case java.sql.Types.DECIMAL:
            case java.sql.Types.REAL:
                colType = "Float";
                break;

            case java.sql.Types.INTEGER:
            case java.sql.Types.BIGINT:
            case java.sql.Types.TINYINT:
                colType = "Integer";
                break;
            case java.sql.Types.VARCHAR:
            case java.sql.Types.CHAR:
            case java.sql.Types.LONGVARBINARY:
            case java.sql.Types.LONGVARCHAR:
                colType = "String";
                break;
            case java.sql.Types.BIT:
                colType = "Boolean";
                break;
            case java.sql.Types.DATE:
            case java.sql.Types.TIMESTAMP:
                colType = "Date";
                break;
            default:
                System.out.println("找不到数据类型：" + databaseType);
                break;
        }

        return colType;
    }

    /**
     * 从属性文件获取配置
     *
     * @param filename
     * @param key
     * @return
     * @throws FileNotFoundException
     */
    public synchronized static String getPropertyFromFile(String filename, String key) {

        String paodingAnalysisPath = System.getProperty("user.dir") + "\\" + filename;

        InputStream in1 = null;
        ResourceBundle rb = null;
        try {
            in1 = new BufferedInputStream(new FileInputStream(paodingAnalysisPath));

            rb = new PropertyResourceBundle(in1);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rb.getString(key).trim();
    }

    /**
     * @param obj
     * @return 如果obj为空或者为''返回true，否则返回false
     * @Description 判断对象是否为空
     */
    public static boolean isEmpty(Object obj) {
        if (obj == null) {
            return true;
        }
        // 字符串类型
        else if (obj instanceof String) {
            return obj.toString().trim().length() <= 0 ? true : false;
        }
        // list类型
        else if (obj instanceof List<?>) {
            return ((List<?>) obj).size() <= 0 ? true : false;
        }
        // 数组类型
        else if (obj.getClass().isArray()) {
            return Array.getLength(obj) <= 0 ? true : false;
        }
        // 集合类型
        else if (obj instanceof Collection) {
            return ((Collection<?>) obj).size() <= 0 ? true : false;
        }
        // map类型
        else if (obj instanceof Map) {
            return ((Map<?, ?>) obj).size() <= 0 ? true : false;
        }
        // 文件类型
        else if (obj instanceof File) {
            return ((File) obj).exists();
        }
        return false;
    }

    /**
     * @param obj
     * @return 如果obj不为空并且不为''返回true，否则返回false
     * @Description 判断对象是否不为空
     * @author mengLei
     * @date 2015-2-4 上午11:58:51
     */
    public static boolean isNotEmpty(Object obj) {
        return !isEmpty(obj);
    }

    /**
     * @param obj
     * @return 如果字符串为null、''或者"null"时返回true，否则返回false
     * @Description 判断字符串是否为null或者''或者为"null"
     * @author zhangyd-c
     * @date 2015年9月2日 下午5:20:37
     */
    public static boolean isNullStr(String obj) {
        if (isEmpty(obj) || "null".equals(obj.trim())) {
            return true;
        }
        return false;
    }

    /**
     * @param obj
     * @return
     * @Description 判断字符串是否不为空并且不为"null"
     * @author zhangyd-c
     * @date 2015年9月8日 上午10:40:51
     */
    public static boolean isNotStrNull(String obj) {
        if (isNotEmpty(obj) && !"null".equals(obj.trim())) {
            return true;
        }
        return false;
    }

    /**
     * @param str
     * @return
     * @Description 首字母小写
     * @author zhangyd-c
     * @date 2015年9月8日 上午10:53:58
     */
    public static String firstLetter2Lower(String str) {
        if (isNotEmpty(str)) {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        }
        return str;
    }

    /**
     * @param name
     * @return
     * @Description 首字母大写
     * @author zhangyd-c
     * @date 2015年9月8日 上午10:53:58
     */
    public static String firstLetter2UpperCase(String str) {
        if (isNotEmpty(str)) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return str;
    }

    /**
     * @param str    指定字符串
     * @param length 自定的字符串长度
     * @param symbol 补填的字符串
     * @return
     * @Description 自动在str后补充(length-str.length())个symbol<br>
     * 比如：str="11" , length = 5 , symbol="0" , 最后返回 : 11000
     * @author zhangyd-c
     * @date 2015年9月8日 上午10:57:03
     */
    public static String appendCharacterForLast(String str, int length, String symbol) {
        int strLen = str.length();
        if (strLen < length) {
            while (strLen < length) {
                StringBuffer sb = new StringBuffer();
                sb.append(str).append(symbol);// 左补0
                str = sb.toString();
                strLen = str.length();
            }
        }
        return str;
    }

    /**
     * @param isFormt 是否需要格式化。<br>
     *                此处格式化指：是否将uuid中的"-"去掉（true:格式化,false:不需要格式化）
     * @return
     * @Description 获取uuid
     * @author zhangyd-c
     * @date 2015年9月8日 上午11:22:57
     */
    public static String getUUID(boolean isFormt) {
        String uuid = UUID.randomUUID().toString();
        if (isFormt) {
            uuid = uuid.replaceAll("-", "");
        }
        return uuid;
    }

    /**
     * @param str : xx_xx
     * @return
     * @Description 首字母大写
     * @author zhangyd
     * @date 2015年12月18日 下午5:09:30
     */
    public static String initialtoUpper(String str) {
        if (null != str)
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        else
            return str;
    }

    /**
     * @param str : xx_xx
     * @return
     * @Description 首字母大写
     * @author zhangyd
     * @date 2015年12月18日 下午5:09:30
     */
    public static String splitAndtoUpper(String str, String splitStr) {
        String[] temps = str.split(splitStr);
        str = temps[0];
        for (int i = 1; i < temps.length; i++) {
            str += initialtoUpper(temps[i]);
        }
        return str;
    }

    /**
     * @param str : xx_xx
     * @return
     * @Description 首字母大写
     * @author zhangyd
     * @date 2015年12月18日 下午5:09:30
     */
    public static String splitAndInitialtoUpper(String str, String splitStr) {
        str = splitAndtoUpper(str, splitStr);
        return initialtoUpper(str);
    }
}
