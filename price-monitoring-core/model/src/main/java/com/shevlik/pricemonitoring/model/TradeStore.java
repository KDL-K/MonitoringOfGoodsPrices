package com.shevlik.pricemonitoring.model;


import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table (name = "stores")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "assortmentList")
@ToString(callSuper = true, exclude = "assortmentList")
public class TradeStore extends AEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "name")
	private String name;
	@Column (name = "phone")
	private String phone;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn(name = "addresses_id")
	private Address address;
	@OneToMany (mappedBy = "tradeStore", fetch = FetchType.LAZY)
	private List<Assortment> assortmentList;
	
	public TradeStore(Long id, String name, String phone, Address address, List<Assortment> assortmentList) {
		super(id);
		this.name = name;
		this.phone = phone;
		this.address = address;
		this.assortmentList = assortmentList;
	}
}
