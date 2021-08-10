package com.shevlik.pricemonitoring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.shevlik.pricemonitoring.api.dao.ITradeStoreDao;
import com.shevlik.pricemonitoring.model.TradeStore;

import lombok.extern.log4j.Log4j2;

@Repository
@Log4j2
public class TradeStoreDao extends AbstractDao<TradeStore> implements ITradeStoreDao{

	@Override
	protected Class<TradeStore> getClazz() {
		return TradeStore.class;
	}

	@Override
	public List<TradeStore> getAllByIdSet(List<Long> storeIdList) {
		log.info ("TradeStoreDao. IN getAllByIdSet - id list: {}", storeIdList);
		final String PARAM_NAME = "id";

		final String QUERY_SELECT_STORES_BY_ID = takeQueryString(PARAM_NAME, storeIdList.size());
		
		TypedQuery<TradeStore> query = entityManager.createQuery(QUERY_SELECT_STORES_BY_ID, TradeStore.class);
		
		int i = 1;
		for (Long id : storeIdList){
			query.setParameter(PARAM_NAME+ i++, id);
		}
	
		List<TradeStore> storeList = query.getResultList();
		
		log.info ("TradeStoreDao. IN getAllByIdSet - result received");

		return storeList;
	}
	
	private String takeQueryString(final String PARAM_NAME, int size) {
		log.info ("TradeStoreDao. IN private takeQueryString");

		String str = "";
		String subStr = "";

		for(int i=0; i<size; i++) {
			if(str.equals("")) {
				str = PARAM_NAME+(i+1);
				continue;
			}
			subStr = String.join(",",str, PARAM_NAME+(i+1));
			str = subStr;
		}
		final String queryStr = String.join("","SELECT s FROM TradeStore s WHERE s.id IN (",str,")");
		
		return queryStr;
	}

}
