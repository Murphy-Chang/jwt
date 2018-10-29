package com.mine.jwt.util;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Title:
 * Description:
 * Copyright: Copyright (c) 2018
 * Company: mine
 *
 * @author Murphy.Chang
 * @version 1.0
 * @date 2018/10/26
 */
public class MapUtil {
	/**
	 * 判断map是否为空
	 * @param map
	 * @return
	 */
	public static boolean isEmpty(Map<String, Object> map){
		return map == null || map.isEmpty();
	}

	/**
	 * 对象转map
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> objToMap(Object obj){
		Map<String, Object> map = new HashMap<>();
		if(obj == null) return new HashMap<>();

		try {
			Field[] fields = obj.getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				map.put(field.getName(), field.get(obj));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new HashMap<>();
		}
		return map;
	}

	/**
	 * map转对象
	 * @param map
	 * @param clazz
	 * @return
	 */
	public static <T> T mapToObj(Map<String, Object> map, Class<T> clazz){
		if(isEmpty(map)) return null;

		T t;
		try{
			t = clazz.newInstance();

			Field[] fields = t.getClass().getDeclaredFields();
			for (Field field : fields) {
				int mod = field.getModifiers();
				if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
					continue;
				}

				field.setAccessible(true);
				field.set(t, map.get(field.getName()));
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}

		return t;
	}

}
