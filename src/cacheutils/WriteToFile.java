package cacheutils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class WriteToFile {

	private static String encode = "utf-8";

	public static void writeStrToFile(String xml, String file) {
		try {
			String path = System.getProperty("user.dir") + "\\" + file;
			//System.out.println(path);
			FileOutputStream fos = new FileOutputStream(new File(path));
			Writer os = new OutputStreamWriter(fos, encode);
			os.write(xml);
			os.flush();
			fos.close();
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
	}
	
    public static void appendFile(String content,String fileName) {
        try {
        	String path = System.getProperty("user.dir") + "\\" + fileName;
            //��һ��д�ļ��������캯���еĵڶ�������true��ʾ��׷����ʽд�ļ�
            FileWriter writer = new FileWriter(path, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }	
	
}
