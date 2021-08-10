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
@EqualsAndHashCode
@ToString
public class GoodsCategoryDto {
	private Long id;
	@NotBlank (message = "'title' cannot be blank")
	private String title;
	@NotNull (message = "'goodsSection' cannot be null")
	private GoodsSectionLightDto goodsSection;
}
