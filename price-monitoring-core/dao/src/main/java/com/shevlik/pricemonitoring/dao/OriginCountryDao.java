package com.shevlik.pricemonitoring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.shevlik.pricemonitoring.api.dao.IOriginCountryDao;
import com.shevlik.pricemonitoring.model.OriginCountry;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class OriginCountryDao extends AbstractDao<OriginCountry> implements IOriginCountryDao{

	@Override
	protected Class<OriginCountry> getClazz() {
		return OriginCountry.class;
	}

	@Override
	public List<OriginCountry> getAllByIdSet(List<Long> originIdList) {
		log.info ("OriginCountryDao. IN getAllByIdSet - id list: {}", originIdList);
		final String PARAM_NAME = "id";

		final String QUERY_SELECT_ORIGIN_COUNTRIES_BY_ID = takeQueryString(PARAM_NAME, originIdList.size());
		
		TypedQuery<OriginCountry> query = entityManager.createQuery(QUERY_SELECT_ORIGIN_COUNTRIES_BY_ID, OriginCountry.class);
		
		int i = 1;
		for (Long id : originIdList){
			query.setParameter(PARAM_NAME+ i++, id);
		}
	
		List<OriginCountry> originList = query.getResultList();
		
		log.info ("OriginCountryDao. IN getAllByIdSet - result received");

		return originList;
	}
	
	private String takeQueryString(final String PARAM_NAME, int size) {
		log.info ("OriginCountryDao. IN private takeQueryString");

		String str = "";
		String subStr = "";
		String token = ":";

		for(int i=0; i<size; i++) {
			if(str.equals("")) {
				str = token+PARAM_NAME+(i+1);
				continue;
			}
			subStr = String.join(",",str, token+PARAM_NAME+(i+1));
			str = subStr;
		}
		final String queryStr = String.join("","SELECT o FROM OriginCountry o WHERE o.id IN (",str,")");
		log.info ("OriginCountryDao. IN private takeQueryString - query: {} ", str);
		return queryStr;
	}

}
