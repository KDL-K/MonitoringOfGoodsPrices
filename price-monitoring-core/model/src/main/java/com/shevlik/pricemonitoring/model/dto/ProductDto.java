package com.shevlik.pricemonitoring.model.dto;



import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"measureUnit", "goodsCategory", "originCountry"})
@ToString
public class ProductDto {
	private Long id;
	@NotBlank (message = "'name' cannot be blank")
	private String name;
	@NotNull (message = "'measureUnit' cannot be null")
	private MeasureUnitDto measureUnit;
	@NotNull (message = "'goodsCategory' cannot be null")
	private GoodsCategoryDto goodsCategory;
	@NotNull (message = "'originCountry' cannot be null")
	private OriginCountryDto originCountry;
}
