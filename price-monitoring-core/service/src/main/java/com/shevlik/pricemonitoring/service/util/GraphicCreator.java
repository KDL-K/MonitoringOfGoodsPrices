package com.shevlik.pricemonitoring.service.util;

import java.io.File;
import java.util.List;

import com.shevlik.pricemonitoring.model.dto.DatePriceDto;

public interface GraphicCreator {
	File creatGraphic(String fileName, List<DatePriceDto> datePriceList);
}
