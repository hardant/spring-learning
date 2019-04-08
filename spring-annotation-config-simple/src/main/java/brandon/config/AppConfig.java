package brandon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

import brandon.dao.Userdao;
import brandon.service.UserService;

@Configuration
@ComponentScan(basePackages = "brandon.dao")
@ImportResource(locations= {"classpath:beans.xml"})
public class AppConfig {

	@Bean
	public UserService userService(Userdao userdao) {
		UserService userService = new UserService();
		userService.setUserDao(userdao);
		
		return userService;
	}
}
