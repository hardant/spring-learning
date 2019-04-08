package brandon.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import brandon.dao.Userdao;
import brandon.service.UserService;

@Configuration
public class BeansConfig {
	@Bean
	public UserService userService(Userdao userdao) {
		UserService userService = new UserService();
		userService.setUserDao(userdao);
		
		return userService;
	}
}
