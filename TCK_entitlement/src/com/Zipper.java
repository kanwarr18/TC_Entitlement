package com;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper {

	public static void Zip() throws IOException {
		// TODO Auto-generated method stub

		
		StringBuilder sb = new StringBuilder();
		sb.append("Test String");

		File f = new File("D:\\ENT-1000\\rkan.zip");
		ZipOutputStream out = new ZipOutputStream(new FileOutputStream(f));
		ZipEntry e = new ZipEntry("D:\\ENT-1000\\rkan.xml");
		out.putNextEntry(e);

		byte[] data = sb.toString().getBytes();
		out.write(data, 0, data.length);
		out.closeEntry();

		out.close();
		
		System.out.println("Zipper  done");
	}

}
