package brandon.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import brandon.service.UserService;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations="classpath:applicatonContext.xml")
public class XmlPrimaryContextTest {
	@Autowired
	UserService userService;
	@Test
	public void test1(){
		System.out.println(userService.getUser());
	}
}
