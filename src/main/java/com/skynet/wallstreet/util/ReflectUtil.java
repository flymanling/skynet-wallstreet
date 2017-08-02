package com.skynet.wallstreet.util;

import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

public class ReflectUtil {

	public static <T, V> void assignmentBean(T obj, Map<String, V> data) {
		for(Entry<String, V> entry : data.entrySet()) {
			assignmentField(obj, entry.getKey(), entry.getValue());
		}
	}
	
	@SuppressWarnings("unchecked")
	public static <T, V> void assignmentField(T obj, String fieldName, V value) {
		Class c = obj.getClass();
		try {
			String fieldSetName = parseSetName(fieldName);
			Method setMethod = c.getMethod(fieldSetName, new Class[]{value.getClass()});
			Class setFieldType = setMethod.getParameterTypes()[0];
			if(setFieldType.equals(value.getClass())) {
				setMethod.invoke(obj, value);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	/** 
     * 拼接某属性的 get方法 
     *  
     * @param fieldName 
     * @return String 
     */  
    public static String parseGetName(String fieldName) {  
        if (null == fieldName || "".equals(fieldName)) {  
            return null;  
        }  
        int startIndex = 0;  
        if (fieldName.charAt(0) == '_')  
            startIndex = 1;  
        return "get"  
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()  
                + fieldName.substring(startIndex + 1);  
    } 
	
    /** 
     * 拼接在某属性的 set方法 
     *  
     * @param fieldName 
     * @return String 
     */  
    public static String parseSetName(String fieldName) {  
        if (null == fieldName || "".equals(fieldName)) {  
            return null;  
        }  
        int startIndex = 0;  
        if (fieldName.charAt(0) == '_')  
            startIndex = 1;  
        return "set"  
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()  
                + fieldName.substring(startIndex + 1);  
    }  
    
	public static void main(String[] args) {
//		Finance f = new Finance();
//		System.out.println("before:" + f.getCash());
//		assignmentField(f, "货币资金", 10000000000L);
//		System.out.println("after:" + f.getCash());
	}
}
