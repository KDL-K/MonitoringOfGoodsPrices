package com.shevlik.pricemonitoring.service.util;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import com.shevlik.pricemonitoring.model.dto.DatePriceDto;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class LineChartMaker implements GraphicCreator{
	private final String AXIS_X_NAME = "Date";
	private final String AXIS_Y_NAME = "Price";
	private final int imageWidth = 640;
	private final int imageHeight = 480;
	private final String lineName = "price";

	@Override
	public File creatGraphic(String fileName, List<DatePriceDto> datePriceList) {
		log.info("LineChartMaker. IN creatGraphic - graphic filename: {}", fileName);
		JFreeChart lineChart = ChartFactory.createLineChart(null, 
				AXIS_X_NAME, AXIS_Y_NAME, createDataset(datePriceList), 
				PlotOrientation.VERTICAL, true, true, false);
		
	    File lineChartFile = new File(String.join(".", fileName,"png")); 
	    try {
			ChartUtils.saveChartAsPNG(lineChartFile, lineChart, imageWidth ,imageHeight);
		} catch (IOException e) {
			log.warn("LineChartMaker. IN creatGraphic - filename: {}. Cannot creat.", fileName);
			throw new GraphicCreationException(e);
		}
		return lineChartFile;
	}

	private CategoryDataset createDataset(List<DatePriceDto> datePriceList) {
		log.info("LineChartMaker. IN private createDataset");
		DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
		
		for(DatePriceDto datePrice : datePriceList) {
			dataset.addValue(datePrice.getPrice(), lineName, datePrice.getPriceDate().toString());
		}
		
	    return dataset;
	}

}
