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
	"test1": {
		"defalut": 1,
		"prd": 1
	},
	"test2": {
		"defalut": "2",
		"prd": "1"
	}
}
```
default：为默认值，必须存在的节点 prd:为指定分支的值，分支可以任意添加，


使用AppProperties.class

```
AppProperties.getValue("tsp.client.timeout");
AppProperties.getIntegerValue("tsp.client.timeout");
AppProperties.getStringValue("tsp.client.timeout");
```