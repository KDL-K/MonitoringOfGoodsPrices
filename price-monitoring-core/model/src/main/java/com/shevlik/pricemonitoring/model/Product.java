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
@Table (name = "products")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "assortmentList")
@ToString(callSuper = true, exclude = "assortmentList")
public class Product extends AEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "name")
	private String name;
	@ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn (name = "units_id")
	private MeasureUnit measureUnit;
	@ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn (name = "categories_id")
	private GoodsCategory goodsCategory;
	@ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn (name = "origin_id")
	private OriginCountry originCountry;
	@OneToMany (mappedBy = "product", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Assortment> assortmentList;
	
	public Product(Long id, String name, MeasureUnit measureUnit, 
			GoodsCategory goodsCategory, OriginCountry originCountry, List<Assortment> assortmentList) {
		super(id);
		this.name = name;
		this.measureUnit = measureUnit;
		this.goodsCategory = goodsCategory;
		this.originCountry = originCountry;
		this.assortmentList = assortmentList;	
	}
}
