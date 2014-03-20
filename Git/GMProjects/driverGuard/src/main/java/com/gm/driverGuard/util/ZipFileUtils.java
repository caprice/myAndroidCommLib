package com.gm.driverGuard.util;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class ZipFileUtils {
	/**
	 * 批量压缩文件（夹）
	 * 
	 * @param resFileList
	 *            要压缩的文件（夹）列表
	 * @param zipFile
	 *            生成的压缩文件
	 * @throws IOException
	 *             当压缩过程出错时抛出
	 */
	static final private int BUFF_SIZE = 1024;

	public static void zipFiles(Collection<File> resFileList, File zipFile, String comment) throws IOException {
		ZipOutputStream zipout = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipFile), BUFF_SIZE));
		for (File resFile : resFileList) {
			zipFile(resFile, zipout, "");
		}
		if (comment != null)
			zipout.setComment(comment);
		zipout.close();
	}

	/**
	 * 压缩文件
	 * 
	 * @param resFile
	 * @param zipout
	 * @param rootpath
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	private static void zipFile(File resFile, ZipOutputStream zipout, String rootpath) throws FileNotFoundException, IOException {
		rootpath = rootpath + (rootpath.trim().length() == 0 ? "" : File.separator) + resFile.getName();
		rootpath = new String(rootpath.getBytes("8859_1"), "GB2312");
		if (resFile.isDirectory()) {
			File[] fileList = resFile.listFiles();
			for (File file : fileList) {
				zipFile(file, zipout, rootpath);
			}
		} else {
			byte buffer[] = new byte[BUFF_SIZE];
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(resFile), BUFF_SIZE);
			zipout.putNextEntry(new ZipEntry(rootpath));
			int realLength;
			while ((realLength = in.read(buffer)) != -1) {
				zipout.write(buffer, 0, realLength);
			}
			in.close();
			zipout.flush();
			zipout.closeEntry();
		}
	}

	/**
	 * 解压文件名包含传入文字的文件
	 * 
	 * @param zipFile
	 *            压缩文件
	 * @param folderPath
	 *            目标文件夹
	 * @param nameContains
	 *            传入的文件匹配名
	 * @throws ZipException
	 *             压缩格式有误时抛出
	 * @throws IOException
	 *             IO错误时抛出
	 */
	public static ArrayList<File> upZipSelectedFile(File zipFile, String folderPath) throws ZipException, IOException {
		ArrayList<File> fileList = new ArrayList<File>();
		File desDir = new File(folderPath);
		if (!desDir.exists()) {
			desDir.mkdir();
		}
		ZipFile zf = new ZipFile(zipFile);
		for (Enumeration<?> entries = zf.entries(); entries.hasMoreElements();) {
			ZipEntry entry = ((ZipEntry) entries.nextElement());
			InputStream in = zf.getInputStream(entry);
			String str = folderPath + entry.getName();
			str = new String(str.getBytes("8859_1"), "GB2312");
			File desFile = new File(str);
			if (!desFile.exists()) {
				File fileParentDir = desFile.getParentFile();
				if (!fileParentDir.exists()) {
					fileParentDir.mkdirs();
				}
				desFile.createNewFile();
			}
			OutputStream out = new FileOutputStream(desFile);
			byte buffer[] = new byte[BUFF_SIZE];
			int realLength;
			while ((realLength = in.read(buffer)) > 0) {
				out.write(buffer, 0, realLength);
			}
			in.close();
			out.close();
			fileList.add(desFile);
		}
		return fileList;
	}
	
	public static void main(String[] args) throws ZipException, IOException{
		File zipFile = new File("E:/Temp/63bbaa.zip");
		ZipFileUtils.upZipSelectedFile(zipFile, "C:/test/");
	}
}
