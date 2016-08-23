package cacheutils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * 
 * @author Andy.Chen
 * @mail Chenjunjun.ZJ@gmail.com
 *
 */
public class InputStreamUtils {
	
	final static int BUFFER_SIZE = 4096;
	
	final static String pageCode = "utf-8";
	
	/**
	 * 将InputStream转换成String
	 * @param in InputStream
	 * @return String
	 * @throws Exception
	 * 
	 */
	public static String InputStreamTOString(InputStream in) throws IOException{
		
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while((count = in.read(data,0,BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);
		
		data = null;
		return new String(outStream.toByteArray(),pageCode);
	}
	
	/**
	 * 将InputStream转换成某种字符编码的String
	 * @param in
	 * @param encoding
	 * @return
	 * @throws IOException 
	 * @throws Exception
	 */
         public static String InputStreamTOString(InputStream in,String encoding) throws IOException {
		
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while((count = in.read(data,0,BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);
		
		data = null;
		return new String(outStream.toByteArray(),pageCode);
	}
    
    /**
     * 将String转换成InputStream
     * @param in
     * @return
     * @throws Exception
     */
    public static InputStream StringTOInputStream(String in) throws Exception{
    	
    	ByteArrayInputStream is = new ByteArrayInputStream(in.getBytes(pageCode));
    	return is;
    }
    
    /**
     * 将InputStream转换成byte数组
     * @param in InputStream
     * @return byte[]
     * @throws IOException
     */
    public static byte[] InputStreamTOByte(InputStream in) throws IOException{
    	
    	ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while((count = in.read(data,0,BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);
		
		data = null;
		return outStream.toByteArray();
    }
    
    /**
     * 将byte数组转换成InputStream
     * @param in
     * @return
     * @throws Exception
     */
    public static InputStream byteTOInputStream(byte[] in) throws IOException{
    	
    	ByteArrayInputStream is = new ByteArrayInputStream(in);
    	return is;
    }
    
    /**
     * 将byte数组转换成String
     * @param in
     * @return
     * @throws Exception
     */
    public static String byteTOString(byte[] in) throws IOException{
    	
    	InputStream is = byteTOInputStream(in);
    	return InputStreamTOString(is);
    }

}