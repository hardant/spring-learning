## 目的

学习通过Java类配置Spring项目

## 示例

### 用Java类作为主配置

#### 配置类

```java
@Configuration //class是一个配置类
@ComponentScan(basePackages = "brandon.dao") // 要扫描的包
@ImportResource(locations= {"classpath:beans.xml"}) // 要引入的xml配置文件
public class AppConfig {

	@Bean
	public UserService userService(Userdao userdao) {
		UserService userService = new UserService();
		userService.setUserDao(userdao);
		
		return userService;
	}
}
```

#### 被引入的xml配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
	<bean id="helloService" class="brandon.service.HelloService"/>
</beans>
```

项目启动后,所有的beans都被IoC容器管理了

### xml作为主配置

#### XML配置

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xmlns:context="http://www.springframework.org/schema/context"
	xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
		http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context-4.3.xsd">
	
	<context:annotation-config/> <!-- 项目中有java类配置文件 -->
	<context:component-scan base-package="brandon.dao"/>
	<bean class="brandon.config.BeansConfig"/> <!-- 通过bean引入java类配置文件 -->
</beans>
```

#### Java类配置

```java
@Configuration
public class BeansConfig {
	@Bean
	public UserService userService(Userdao userdao) {
		UserService userService = new UserService();
		userService.setUserDao(userdao);
		
		return userService;
	}
}
```

项目启动后,所有的beans都被IoC容器管理了