package com.shevlik.pricemonitoring.security.api.dao;

import com.shevlik.pricemonitoring.security.model.User;

public interface IUserDao extends GenericDao<User>{
	User getByLogin(String login);
}
