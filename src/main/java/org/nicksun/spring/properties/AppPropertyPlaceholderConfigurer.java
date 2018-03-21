package org.nicksun.spring.properties;

import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

public class AppPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer implements InitializingBean {

	private static final Logger LOG = LoggerFactory.getLogger(AppPropertyPlaceholderConfigurer.class);
	private boolean logOnStart;

	public void setLogOnStart(boolean logOnStart) {
		this.logOnStart = logOnStart;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		if (!logOnStart) {
			return;
		}
		StringBuilder buffer = new StringBuilder("#props\n");
		Properties props = mergeProperties();
		props.entrySet().forEach(e -> {
			buffer.append(e.getKey()).append("    =    ").append(e.getValue().toString()).append("\n");
		});
		if (LOG.isInfoEnabled()) {
			LOG.info(buffer.toString());
		}
	}

}
