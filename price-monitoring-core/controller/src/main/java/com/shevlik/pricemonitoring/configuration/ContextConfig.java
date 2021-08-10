package com.shevlik.pricemonitoring.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@ComponentScan("com.shevlik.pricemonitoring")
@EnableTransactionManagement
public class ContextConfig {

}
