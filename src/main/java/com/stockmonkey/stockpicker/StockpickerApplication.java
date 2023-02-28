package com.stockmonkey.stockpicker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.stockmonkey.stockpicker.services.DCAservice;

@SpringBootApplication
public class StockpickerApplication implements CommandLineRunner {

	@Autowired
	private DCAservice dcaSvc;

	public static void main(String[] args) {
		SpringApplication.run(StockpickerApplication.class, args);
	}

	public void run(String...args){
		// dcaSvc.computeDCA("spy","2019-01-01","2023-01-01",7,150,1000);
		// dcaSvc.computeDCA("aapl","2020-02-18","2023-02-27",30,500,1000);
		// dcaSvc.printAll();
	}

}
