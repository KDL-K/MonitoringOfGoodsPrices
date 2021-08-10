package com.shevlik.pricemonitoring.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.shevlik.pricemonitoring.model.util.DateTypeConverter;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
@Entity
@Table (name = "histories")
public class PriceHistory extends AEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Convert (converter = DateTypeConverter.class)
	@Column (name = "price_date")
	private LocalDate priceDate;
	@ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	@JoinColumn (name = "products_id")
	private Product product;
	@Column (name = "price")
	private Double price;
	
	public PriceHistory(Long id, LocalDate priceDate, Product product, Double price) {
		super(id);
		this.priceDate = priceDate;
		this.product = product;
		this.price = price;
	}

}
