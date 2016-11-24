package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * String工具�?
 * @author Franklin
 *
 */
public class StringUtils {

	/**
	 * �?��字符串是否为空�?或NULL
	 * @param s String
	 * @return TRUE为空，否则为FALSE
	 */
	public static boolean isNull(String s) {
		if(s==null || "".equals(s)) {
			return true;
		}
		return false;
	}
	/**
	 * 获取UUID
	 * @return
	 */
	public static String uuidUpperCase(){
		return UUID.randomUUID().toString().toLowerCase().replaceAll("-", "");
	}
	/**
	 * �?��字符串数值是否为空或长度�?
	 * @param s String[]
	 * @return True为空或长度为0，否则为Falase
	 */
	public static boolean arrayIsNull(String[] s) {
		if(s==null || s.length==0) {
			return true;
		}
		return false;
	}
	
	
	public static int toInt(String s, int defaultValue){
		try {
			return Integer.parseInt(s);
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	/**
	 * 将源字符串按','分隔，如果遇到双引号中有','的情况，则此时的','不作为分隔符处理而是将双引号内的�?��内容当做�?��切割单元保存
	 * @param str 源字符串
	 * @return 切割后的字符串数�?
	 */
	public static List<String> stringSplit(String str){
		if(str == null || "".equals(str)){
			return null;
		}
		List<String> list = new ArrayList<String>(); 
		StringBuffer cell = new StringBuffer();
		if(str.endsWith(",")){
			str=str+",";
		}
		//迭代str的每个字符，将双引号内有","的字符不做为分隔符处理，将双引号内的内弱作为�?��整体的字符串处理。其他按�?,"切割
		for(int i=0; i<str.length(); i++){
			char ch = str.charAt(i);
			if(ch == '\"' ){
				for(int j=i+1; j<str.length(); j++){
					char ch2 = str.charAt(j);
					if(ch2 == '\"'){
						i++;
						i++;
						break;
					}else{
						cell.append(ch2);
						i++;
						continue;
					}
				}
				list.add(cell.toString());
				cell = new StringBuffer();
				continue;
			}else if(ch == ','){
				list.add(cell.toString());
				cell = new StringBuffer();
				continue;
			}else{
				cell.append(ch);
				if(i == (str.length()-1)){
					list.add(cell.toString());
				}
				continue;
			}
		}
		return list;
	}
	
	/**
	 * 将数据库的Clob字段转换为String对象
	 * @param clob 数据库Clob字段
	 * @return 转换后的字符�?
	 */
	public static String clobTOString(Clob clob){
		String str = "";
		if(clob == null){
			str = null;
		}
		Reader reader = null ;
		BufferedReader bufferReader = null;
		try {
		    reader = clob.getCharacterStream();
			bufferReader = new BufferedReader(reader);
			StringBuffer buffer = new StringBuffer();
			String line = null;
			while((line= bufferReader.readLine()) != null){
				buffer.append(line);
			}
			str = buffer.toString();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(bufferReader != null){
					bufferReader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return str;
	}
	public static String dates() {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = format.format(date);
		return s;
	}
}
