package com.yiban.datacenter.XpsToJson;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class XpsToJson {

	public static void readFileByChars(String fileName) {
		File file = new File(fileName);
		Reader reader = null;
		try {
			//System.out.println("以字符为单位读取文件内容，一次读一个字节：");
			// 一次读一个字符
			reader = new InputStreamReader(new FileInputStream(file));
			int tempchar;
			while ((tempchar = reader.read()) != -1) {
				// 对于windows下，\r\n这两个字符在一起时，表示一个换行。
				// 但如果这两个字符分开显示时，会换两次行。
				// 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
				if (((char) tempchar) != '\r') {
					System.out.print((char) tempchar);
				}
			}
			reader.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			System.out.println("以字符为单位读取文件内容，一次读多个字节：");
			// 一次读多个字符
			char[] tempchars = new char[30];
			int charread = 0;
			reader = new InputStreamReader(new FileInputStream(fileName));
			// 读入多个字符到字符数组中，charread为一次读取字符数
			while ((charread = reader.read(tempchars)) != -1) {
				// 同样屏蔽掉\r不显示
				if ((charread == tempchars.length)
						&& (tempchars[tempchars.length - 1] != '\r')) {
					System.out.print(tempchars);
				} else {
					for (int i = 0; i < charread; i++) {
						if (tempchars[i] == '\r') {
							continue;
						} else {
							System.out.print(tempchars[i]);
						}
					}
				}
			}

		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 解压缩（压缩文件中包含多个文件）可代替上面的方法使用。 ZipInputStream类
	 * 当我们需要解压缩多个文件的时候，ZipEntry就无法使用了， 如果想操作更加复杂的压缩文件，我们就必须使用ZipInputStream类
	 * */
	public static void ZipContraMultiFile(String zippath, String outzippath) {
		try {
			File file = new File(zippath);
			File outFile = null;
			ZipFile zipFile = new ZipFile(file);
			ZipInputStream zipInput = new ZipInputStream(new FileInputStream(
					file));
			ZipEntry entry = null;
			InputStream input = null;
			OutputStream output = null;
			while ((entry = zipInput.getNextEntry()) != null) {
				System.out.println("解压缩" + entry.getName() + "文件");
				outFile = new File(outzippath + File.separator
						+ entry.getName());
				boolean success=false;
				if (!outFile.getParentFile().exists()) {
					success=outFile.getParentFile().mkdir();
				}
				if (!outFile.exists()&&success) {
					outFile.createNewFile();
				}
				if(success){
					input = zipFile.getInputStream(entry);
					output = new FileOutputStream(outFile);
					int temp = 0;
					while ((temp = input.read()) != -1) {
						output.write(temp);
					}
				}

				input.close();
				output.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		
		//readFileByChars("E:\\liujiyu\\新建文件夹\\Report\\Documents\\1\\Pages\\1.fpage");
		XMLParse.ParseXML("E:\\XPS\\Report\\Documents\\1\\Pages\\2.xml");
	
	}

}
