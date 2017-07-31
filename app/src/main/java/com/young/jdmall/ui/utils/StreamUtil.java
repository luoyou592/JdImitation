package com.young.jdmall.ui.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class StreamUtil {
	//将输入流中数据返回字符串
	public static String parseStream(InputStream is) throws IOException {
		//将输入流数据读取到内存中
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
//		ByteArrayInputStream
		byte[] buffer  = new byte[1024];
		int len=0;
		while ((len=is.read(buffer))>0) {
			baos.write(buffer, 0, len);
		}
		return baos.toString();
	}
}
