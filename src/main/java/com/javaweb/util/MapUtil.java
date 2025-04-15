package com.javaweb.util;

import java.util.Map;

public class MapUtil {
	public static <T> T getObject (Map<String,Object> params, String key, Class<T> tclass) {
		Object obj = params.getOrDefault(key,null); // lay gia tri cua mot key , thi no cung co the bang "" 
		if (obj != null) {
			if (tclass.getTypeName().equals("java.lang.Long")) {
				obj = NumberUtil.isLong(obj.toString()) ? Long.parseLong(obj.toString()) : null;
			}
			else if (tclass.getTypeName().equals("java.lang.Integer")) {
				obj = NumberUtil.isInt(obj.toString()) ? Integer.parseInt(obj.toString()) : null;
			}
			else if (tclass.getTypeName().equals("java.lang.Float")) {
				obj = NumberUtil.isFloat(obj.toString()) ? Float.parseFloat(obj.toString()) : null;
			}
			else if (tclass.getTypeName().equals("java.lang.String")) {
				obj = obj.toString();
			}
			return tclass.cast(obj);  // tra ve gia tri co kieu du lieu tuong uong voi tClass
		}
		return null;
	}
}
