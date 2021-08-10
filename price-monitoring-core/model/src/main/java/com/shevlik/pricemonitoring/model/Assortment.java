package com.shevlik.pricemonitoring.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import com.shevlik.pricemonitoring.model.util.DateTypeConverter;

@Entity
@Table (name = "assortment")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true, exclude = "tradeStore")
@ToString(callSuper = true, exclude = "tradeStore")
public class Assortment extends AEntity implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Column (name = "price")
	private Double price;
	@Convert (converter = DateTypeConverter.class)
	@Column (name = "update_date")
	private LocalDate updateDate;
	@ManyToOne (cascade = CascadeType.MERGE)
	@JoinColumn (name = "products_id")
	private Product product;
	@ManyToOne (cascade = CascadeType.MERGE)
	@JoinColumn (name = "stores_id")
	private TradeStore tradeStore;
	
	public Assortment(Long id, Double price, LocalDate updateDate, Product product, TradeStore tradeStore) {
		super(id);
		this.price = price;
		this.updateDate = updateDate;
		this.product = product;
		this.tradeStore = tradeStore;
	}
}
