spring属性配置文件扩展,支持json格式配置，支持多环境配置
===============

spring 配置文件

```
    <bean id="props" class="org.nicksun.spring.properties.JsonFileProperties">
        <property name="profile" value="${env.name}"></property>
        <property name="file" value="classpath:AppProperties.json"></property>
    </bean>
    <bean class="org.nicksun.spring.properties.AppPropertyPlaceholderConfigurer">
        <property name="properties" ref="props"/>
        <property name="logOnStart" value="true"/>
    </bean>
```
AppProperties.json

```
{
	"tsp.client.timeout": {
		"defalut": 1,
		"prd": 1
	},
	"fsp.register.address": {
		"defalut": "fsp-register.sst.blackfi.sh:9989",
		"prd": "fsp-register-master.blackfi.sh:9989,fsp-register-slave.blackfi.sh:9989"
	}
}
```# spring-json-properties
