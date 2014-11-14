package com.ftofstudios.wentros.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.authentication.encoding.PasswordEncoder;

public class Utils {
private static Logger logger = Logger.getLogger(Utils.class);
	public static String parseDate(Date date) {
		if (date == null)
			return null;
		String startDbDate = "";
		try {
			String pattern = "yyyy-MM-dd HH:mm:ss";
			SimpleDateFormat formatter = new SimpleDateFormat(pattern);
			startDbDate = formatter.format(date);
		} catch (Exception e) {
			logger.debug(e);
		}
		return startDbDate;
	}
	
	public static String replaceKeyWord(String fileData,
			HashMap<String, String> replaceKeyValue) {
		Iterator<String> iterKeys = replaceKeyValue.keySet().iterator();
		String key = null;
		String val = null;
		while (iterKeys.hasNext()) {
			key = (String) iterKeys.next();
			val = replaceKeyValue.get(key);
			// now search and replace in the file
			key = "${" + key + "}";
			fileData = fileData.replace(key, val);
		}
		return fileData;
	}
	
	public static String generatePassword(String constStr, int length){

		final String ALPHA_NUM = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
		for (int i=0;  i < length;  i++) {  
			int ndx = (int)(Math.random()*ALPHA_NUM.length());  
			constStr += ALPHA_NUM.charAt(ndx);  
		}  
		return constStr;
	}
	
	public static String encodePassword(String password) {
		PasswordEncoder encoder = new Md5PasswordEncoder();
		String hashedPassword = encoder.encodePassword(password, null);
		return hashedPassword;
		
	}
}
