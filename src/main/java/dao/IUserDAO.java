package dao;

import model.UserModel;

public interface IUserDAO extends GenericDAO<UserModel> {
	UserModel login(String userName, String password, Integer status);
}