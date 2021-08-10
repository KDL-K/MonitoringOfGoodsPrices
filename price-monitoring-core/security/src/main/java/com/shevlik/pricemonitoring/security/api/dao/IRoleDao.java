package com.shevlik.pricemonitoring.security.api.dao;

import com.shevlik.pricemonitoring.security.model.Role;
import com.shevlik.pricemonitoring.security.model.RoleValue;

public interface IRoleDao extends GenericDao<Role>{
	Role findByEnumValue(RoleValue value);
}
