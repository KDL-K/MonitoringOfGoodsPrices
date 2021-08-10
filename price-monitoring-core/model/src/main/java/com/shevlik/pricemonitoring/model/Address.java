package com.shevlik.pricemonitoring.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "addresses")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "tradeStoreList")
@ToString(callSuper = true, exclude = "tradeStoreList")
public class Address extends AEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "country")
	private String country;
	@Column (name = "city")
	private String city;
	@Column (name = "street")
	private String street;
	@Column (name = "building")
	private String building;
	@OneToMany (mappedBy = "address", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<TradeStore> tradeStoreList;
	
	public Address(Long id, String country, String city, String street, String building, List<TradeStore> tradeStoreList) {
		super(id);
		this.country = country;
		this.city = city;
		this.street = street;
		this.building = building;
		this.tradeStoreList = tradeStoreList;
	}
}
