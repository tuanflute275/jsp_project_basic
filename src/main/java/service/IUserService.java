package service;

import model.UserModel;

public interface IUserService {
	UserModel login(String userName, String password, Integer status);
}
