package com.shevlik.pricemonitoring.model.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class GoodsSectionDto {
	private Long id;
	@NotBlank (message = "'title' cannot be blank")
	private String title;
	private List<GoodsCategoryLightDto> goodsCategories;
}
