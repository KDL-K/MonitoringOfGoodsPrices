package com.shevlik.pricemonitoring.model;

import java.io.Serializable;
import java.util.List;

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
@Table (name = "sections")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude= "goodsCategories")
@ToString(callSuper = true, exclude= "goodsCategories")
public class GoodsSection extends AEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "title")
	private String title;
	@OneToMany (mappedBy = "goodsSection", fetch = FetchType.EAGER)
	private List<GoodsCategory> goodsCategories;
	
	public GoodsSection (Long id, String title, List<GoodsCategory> goodsCategories) {
		super(id);
		this.title = title;
		this.goodsCategories = goodsCategories;
	}
}
