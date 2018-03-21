package org.nicksun.spring.properties;

import java.util.Objects;
import java.util.Properties;

/**
 * @author nicksun
 *
 */
public class AppProperties {

	private static Properties props = null;

	private AppProperties() {
	}

	public static Object getValue(String key) {
		if (Objects.isNull(props)) {
			return null;
		}
		return props.getProperty(key);
	}

	@SuppressWarnings("unchecked")
	public static <T> T getValue(String key, Class<T> clazz) {
		if (Objects.isNull(props)) {
			return null;
		}
		return (T) props.getProperty(key);
	}

	public static void init(Properties p) {
		if (Objects.isNull(props)) {
			props = p;
		}
	}

	public static String getStringValue(String key) {
		return getValue(key, String.class);
	}

	public static Integer getIntegerValue(String key) {
		return Integer.parseInt(getStringValue(key));
	}

	public static Boolean getBooleanValue(String key) {
		return Boolean.parseBoolean(getStringValue(key));
	}

	public static Long getLongValue(String key) {
		return Long.parseLong(getStringValue(key));
	}

	public static Double getDoubleValue(String key) {
		return Double.parseDouble(getStringValue(key));
	}
}
