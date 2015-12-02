package com.yiban.datacenter.XpsToJson;
import java.io.File;
import java.io.IOException;

public class hello {

	/*主程序部分*/


		public static void main(String[] args) throws IOException {
			/***解压文件****/
//			File inFile = new File("testzip.zip");
//			File outFile = new File("out");
//			ZipTool.unzip(inFile, outFile);
//			
			
			/**压缩文件及目录**/
				File[] files ={
						new File("a"),
						new File("document"),
						new File("lib"),
						new File("t3.text")
				};
				File targetFile = new File("testzip.zip");
				ZipTool.zip(files,targetFile);
		}

	}

