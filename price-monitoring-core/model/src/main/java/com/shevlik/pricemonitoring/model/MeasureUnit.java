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
@Table (name = "units")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "productList")
@ToString(callSuper = true, exclude = "productList")
public class MeasureUnit extends AEntity implements Serializable{
	
	private static final long serialVersionUID = 1L; 
	
	@Column (name = "title")
	private String title;
	@OneToMany (mappedBy = "measureUnit", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Product> productList;
	
	public MeasureUnit (Long id, String title, List<Product> productList) {
		super(id);
		this.title = title;
		this.productList = productList;
	}
}
