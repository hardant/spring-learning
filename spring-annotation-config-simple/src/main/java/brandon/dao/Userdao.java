package brandon.dao;

import org.springframework.stereotype.Repository;

import brandon.entity.User;

@Repository
public class Userdao {
	public User getUser() {
		return new User("Brandon");
	}
}
