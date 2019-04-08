package brandon.service;

import brandon.dao.Userdao;
import brandon.entity.User;

public class UserService {
	private String name = "brandon";
	private Userdao userDao;

	public void setName(String name) {
		this.name = name;
	}

	public void printName() {
		System.out.println(name);
	}
	
	public void setUserDao(Userdao userdao) {
		this.userDao = userdao;
	}
	
	public User getUser () {
		return userDao.getUser();
	}
}
