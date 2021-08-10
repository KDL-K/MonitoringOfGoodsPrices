package com.shevlik.pricemonitoring.security.dao;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.shevlik.pricemonitoring.security.api.dao.IRoleDao;
import com.shevlik.pricemonitoring.security.model.Role;
import com.shevlik.pricemonitoring.security.model.RoleValue;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class RoleDao extends AbstractDao<Role> implements IRoleDao{

	@Override
	protected Class<Role> getClazz() {
		return Role.class;
	}

	@Override
	public Role findByEnumValue(RoleValue value) {
		log.info("RoleDao. IN findByEnumValue -  RoleValue: {}", value);
		final String QUERY_BY_NAME = "SELECT r FROM Role r"
				+ " WHERE r.value = :value";
		TypedQuery<Role> query = entityManager.createQuery(QUERY_BY_NAME, Role.class);
		query.setParameter("value", value);
		Role role = query.getSingleResult();
		log.info("RoleDao. IN findByEnumValue - result received");
		return role;
	}

}
