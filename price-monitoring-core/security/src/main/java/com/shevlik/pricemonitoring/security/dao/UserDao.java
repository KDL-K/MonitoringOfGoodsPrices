package com.shevlik.pricemonitoring.security.dao;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.shevlik.pricemonitoring.security.api.dao.IUserDao;
import com.shevlik.pricemonitoring.security.model.User;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class UserDao extends AbstractDao<User> implements IUserDao{

	@Override
	public User getByLogin(String login) {
		log.info("UserDao. IN getByLogin -  login: {}", login);
		final String QUERY_BY_LOGIN = "SELECT u FROM User u"
				+ " WHERE u.login = :login";
		
		TypedQuery<User> query = entityManager.createQuery(QUERY_BY_LOGIN, User.class);
		query.setParameter("login", login);
		User user = null;
		try{
			user = query.getSingleResult();
		}catch(NoResultException e) {
			log.warn("UserDao. IN getByLogin. There is no such user.");
		}
		
		log.info("UserDao. IN getByLogin - result received");

		return user;
	}

	@Override
	protected Class<User> getClazz() {
		return User.class;
	}
}
