package org.nicksun.spring.properties;

import java.io.File;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.ResourceUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonFileProperties implements FactoryBean<Properties>, InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(JsonFileProperties.class);
	private static final String DEFAULT_VALUE = "default";
	private static ObjectMapper objectMapper = ObjectMapperFactory.getDefaultObjectMapper();
	private Properties props;
	private String file;
	private String profile;

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void afterPropertiesSet() throws Exception {
		props = new Properties();
		try {
			File cfgFile = ResourceUtils.getFile(file);
			String text = Files.lines(cfgFile.toPath()).collect(Collectors.joining());
			if (Objects.isNull(text) || text.length() <= 0) {
				return;
			}
			Map<String, Object> map = objectMapper.readValue(text, new TypeReference<Map<String, Object>>() {
			});
			if (Objects.nonNull(map)) {
				map.forEach((k, v) -> {
					Object o = null;
					if (v instanceof Map) {
						Map<String, Object> value = (Map<String, Object>) v;
						o = value.get(profile);
						if (Objects.isNull(o)) {
							o = value.get(DEFAULT_VALUE);
						}
					} else {
						o = v;
					}
					if (Objects.nonNull(o)) {
						props.setProperty(k, o.toString());
					} else {
						LOG.warn(String.format("key:%s has no default value", k));
					}
				});
				AppProperties.init(props);
			}
		} catch (Exception e) {
			throw new Exception("解析AppProperties.json文件失败," + e.getMessage(), e);
		}
	}

	@Override
	public Properties getObject() throws Exception {
		return props;
	}

	@Override
	public Class<?> getObjectType() {
		return Properties.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}
}
