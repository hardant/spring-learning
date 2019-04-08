package brandon.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import brandon.dao.Userdao;
import brandon.service.HelloService;
import brandon.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {AppConfig.class})
public class AppConfigTest {
	@Autowired
	UserService userService;
	@Autowired
	Userdao userDao;
	@Autowired
	HelloService helloService;
	
	public void testGetUserFromService() {
		System.out.println(userService.getUser());
	}
	@Test
	public void test() {
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//		context.register(AppConfig.class);
//		UserService userService = context.getBean(UserService.class);
		userService.printName();
	}

	@Test
	public void test2() {
//		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
//		context.register(AppConfig.class);
//		Userdao userdao = context.getBean(Userdao.class);
		System.out.println(userDao.getUser());
	}

	@Test
	public void Test3() {
		helloService.sayHello();
	}
}
