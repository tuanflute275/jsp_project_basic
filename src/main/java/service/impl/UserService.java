package service.impl;

import dao.IUserDAO;
import dao.impl.UserDAO;
import model.UserModel;
import service.IUserService;

public class UserService implements IUserService{

	private IUserDAO userDAO;

	public UserService() {
		userDAO = new UserDAO();
	}
	
	@Override
	public UserModel login(String userName, String password, Integer status) {
		return userDAO.login(userName, password, status);
	}

}
