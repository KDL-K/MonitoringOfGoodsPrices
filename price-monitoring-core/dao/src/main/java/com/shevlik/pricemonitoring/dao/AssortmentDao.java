package com.shevlik.pricemonitoring.dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.shevlik.pricemonitoring.api.dao.IAssortmentDao;
import com.shevlik.pricemonitoring.model.Assortment;
import com.shevlik.pricemonitoring.model.TradeStore;

import lombok.extern.log4j.Log4j2;


@Repository
@Log4j2
public class AssortmentDao extends AbstractDao<Assortment> implements IAssortmentDao{
    
	@Override
	protected Class<Assortment> getClazz() {
		return Assortment.class;
	}

	@Override
	public List<Assortment> getAssortmentByStores(List<TradeStore> stores) {
		log.info ("AssortmentDao. IN getAssortmentByStores");
		final String PARAM_NAME = "store";
		
		final String QUERY_SELECT_ASSORTMENT = takeQueryString(PARAM_NAME, stores.size());
		
		TypedQuery<Assortment> query = entityManager.createQuery(QUERY_SELECT_ASSORTMENT, Assortment.class);
		int i = 1;
		for (TradeStore store : stores){
			query.setParameter(PARAM_NAME+ i++, store);
		}
		
		List<Assortment> assortmentByStores = query.getResultList();
		
		log.info ("AssortmentDao. IN getAssortmentByStores - result received");
		
		return assortmentByStores;
	}

	private String takeQueryString(final String PARAM_NAME, int size) {
		log.info ("AssortmentDao. IN private takeQueryString");

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
		final String queryStr = String.join("","SELECT a FROM Assortment a WHERE a.tradeStore IN (",str,")");
		
		return queryStr;
	}

	@Override
	public List<Assortment> getByStore(TradeStore store) {
		log.info ("AssortmentDao. IN getByStore - store: {}, id: {}", store.getName(), store.getId());

		final String QUERY_GET_BY_STORE = "SELECT a FROM Assortment a"
				+ " WHERE a.tradeStore=:store";
		TypedQuery<Assortment> query = entityManager.createQuery(QUERY_GET_BY_STORE, Assortment.class);
		query.setParameter("store", store);
		List<Assortment> assortmentList = query.getResultList();
		
		log.info ("AssortmentDao. IN getByStore - result received");

		return assortmentList;
	}

}
