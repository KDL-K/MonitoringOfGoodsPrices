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
@Table (name = "categories")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude= "productList")
@ToString(callSuper = true, exclude= "productList")
public class GoodsCategory extends AEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column
	private String title;
	@ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn (name = "sections_id")
	private GoodsSection goodsSection;
	@OneToMany (mappedBy = "goodsCategory")
	private List<Product> productList;
	
	public GoodsCategory (Long id, String title, GoodsSection goodsSection, List<Product> productList) {
		super(id);
		this.title = title;
		this.goodsSection = goodsSection;
		this.productList = productList;
	}

}
