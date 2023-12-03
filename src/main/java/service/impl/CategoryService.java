package service.impl;


import java.util.List;

import javax.inject.Inject;

import dao.ICategoryDAO;
import model.CategoryModel;
import service.ICategoryService;

public class CategoryService implements ICategoryService{

	// java servlet weld 
	// servlet-weld maven
	@Inject
	private ICategoryDAO categoryDao;

	@Override
	public List<CategoryModel> findAll() {
		return categoryDao.findAll();
	}

}
