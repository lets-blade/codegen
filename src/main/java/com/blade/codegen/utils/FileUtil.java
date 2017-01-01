package com.blade.codegen.utils;

import java.io.*;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Description 文件工具类
 */
public class FileUtil {

	/** b单位的1GB大小 */
	private static final double SIZE_B_TO_GB = 1 * 1024 * 1024 * 1024;

	/** b单位的1M大小 */
	private static final double SIZE_B_TO_M = 1 * 1024 * 1024;

	/** b单位的1KB大小 */
	private static final double SIZE_B_TO_KB = 1 * 1024;

	private static String copyNameRegex = "(\\s+\\(\\d+\\)$)"; // 获取重命名的正则表达式

	private static String[] pictureSuffixs = { ".jpg", ".jpeg", ".png", ".gif", ".bmp", ".rar", ".zip" };// 图片后缀

	/**
	 * @Description 关闭资源(多关闭)
	 * @author zhangyd
	 * @date 2015年12月9日 下午1:39:12
	 * @param closeables
	 */
	public static void close(Closeable... closeables) {
		if (StringUtil.isNotEmpty(closeables)) {
			for (Closeable closeable : closeables) {
				try {
					if (StringUtil.isNotEmpty(closeable)) {
						closeable.close();
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * @Description 获取文件的标准路径
	 * @author zhangyd
	 * @date 2015年12月9日 下午1:39:06
	 * @param file
	 * @return
	 */
	public static String getStandardPath(File file) {
		return file.getAbsolutePath().replaceAll("/", File.separator);
	}

	/**
	 * @Description 获取文件前缀
	 * @author zhangyd
	 * @date 2015年12月9日 下午1:35:56
	 * @param file
	 * @return
	 */
	public static String getPrefix(File file) {
		return getPrefix(file.getName());
	}

	/**
	 * @Description 获取文件前缀
	 * @author zhangyd
	 * @date 2015年12月9日 下午1:35:56
	 * @param fileName
	 * @return
	 */
	public static String getPrefix(String fileName) {
		int idx = fileName.lastIndexOf(".");
		idx = idx == -1 ? fileName.length() : idx;
		return fileName.substring(0, idx);
	}

	/**
	 * @Description 获取文件后缀
	 * @author zhangyd
	 * @date 2015年12月1日 下午5:07:43
	 * @param file
	 * @return
	 */
	public static String getSuffix(File file) {
		return getSuffix(file.getName());
	}

	/**
	 * @Description 获取文件后缀(加点)
	 * @author zhangyd
	 * @date 2015年12月1日 下午5:07:43
	 * @param fileName
	 * @return
	 */
	public static String getSuffix(String fileName) {
		int idx = fileName.lastIndexOf(".");
		idx = idx == -1 ? fileName.length() : idx;
		return fileName.substring(idx);
	}

	/**
	 * @Description 获取文件后缀(不加点)
	 * @author zhangyd
	 * @date 2015年12月1日 下午5:07:43
	 * @param fileName
	 * @return
	 */
	public static String getSuffixNoPoint(String fileName) {
		int idx = fileName.lastIndexOf(".");
		idx = idx == -1 ? fileName.length() : idx;
		return fileName.substring(idx + 1);
	}

	/**
	 * @Description 获取重命名的数字
	 * @author zhangyd
	 * @date 2015年12月9日 下午1:39:36
	 * @param copyName
	 * @return
	 */
	public static int getCopyNameNum(String copyName) {
		Pattern p = Pattern.compile(copyNameRegex);
		Matcher m = p.matcher(copyName);
		if (m.find()) {
			return Integer.valueOf(m.group().replaceAll("[\\s+\\(|\\)]", ""));
		}
		return -1;

	}

	/**
	 * @Description 判断文件是否为图片格式
	 * @author zhangyd-c
	 * @date 2015年10月9日 下午5:21:21
	 * @param suffix
	 * @return
	 */
	public static boolean isPicture(String suffix) {
		if (StringUtil.isNullStr(suffix)) {
			return false;
		}
		return Arrays.asList(pictureSuffixs).contains(suffix.toLowerCase());
	}

	/**
	 * @Description 删除文件
	 * @author zhangyd-c
	 * @date 2015年10月9日 下午6:09:37
	 * @param filePath
	 */
	public static void deleteFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
	 * @Description 字节b转换千字节KB
	 * @author zhangyd
	 * @date 2015年12月1日 下午3:00:38
	 * @param size
	 * @return
	 */
	public static double getSizeKB(long size) {
		return Double.parseDouble(String.valueOf(size)) / SIZE_B_TO_KB;
	}

	/**
	 * @Description 字节b转换兆字节M
	 * @author zhangyd
	 * @date 2015年12月1日 下午3:00:38
	 * @param size
	 * @return
	 */
	public static double getSizeMB(long size) {
		return Double.parseDouble(String.valueOf(size)) / SIZE_B_TO_M;
	}

	/**
	 * @Description 字节b转换千兆字节GB
	 * @author zhangyd
	 * @date 2015年12月1日 下午3:00:38
	 * @param size
	 * @return
	 */
	public static double getSizeGB(long size) {
		return Double.parseDouble(String.valueOf(size)) / SIZE_B_TO_GB;
	}

	/**
	 * @Description MB转换Bit
	 * @author zhangyd
	 * @date 2015年12月2日 下午3:00:38
	 * @param size
	 * @return
	 */
	public static double getSizeBit(String size) {
		return Double.parseDouble(size) * SIZE_B_TO_M;
	}

	/**
	 * @Description 将字符串写入文件==生成文件
	 * @author zhangyd
	 * @date 2015年12月8日 下午3:29:49
	 * @param filePath
	 * @param content
	 * @return
	 */
	public static boolean saveFile(String filePath, String content) {
		return saveFile(filePath, content, "UTF-8");
	}

	/**
	 * @Description 将字符串写入文件==生成文件
	 * @author zhangyd
	 * @date 2015年12月9日 下午1:32:30
	 * @param path
	 * @param content
	 * @param encodeingType
	 * @return
	 */
	public static boolean saveFile(String path, String content, String encodeingType) {
		boolean flag = false;
		DataOutputStream dos = null;
		try {
			File file = new File(path);
			if (content != null && content.length() >= 0) {
				byte abyte[] = content.getBytes(encodeingType);
				dos = new DataOutputStream(new FileOutputStream(file));
				dos.write(abyte, 0, abyte.length);
				dos.flush();

				flag = true;
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (dos != null) {
				close(dos);
			}
		}
		return flag;
	}

	public static void copyFolder(File src, File dest) throws IOException{
		if(src.isDirectory()){
			//if directory not exists, create it
			if(!dest.exists()){
				dest.mkdir();
			}

			//list all the directory contents
			String files[] = src.list();
			for (String file : files) {
				//construct the src and dest file structure
				File srcFile = new File(src, file);
				File destFile = new File(dest, file);
				//recursive copy
				copyFolder(srcFile,destFile);
			}
		}else{
			//if file, then copy it
			//Use bytes stream to support all file types
			InputStream in = new FileInputStream(src);
			OutputStream out = new FileOutputStream(dest);

			byte[] buffer = new byte[1024];

			int length;
			//copy the file content in bytes
			while ((length = in.read(buffer)) > 0){
				out.write(buffer, 0, length);
			}
			in.close();
			out.close();
		}
	}

}