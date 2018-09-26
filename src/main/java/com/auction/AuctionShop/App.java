package com.auction.AuctionShop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(
				com.auction.AuctionShop.configuration.DataBaseConfiguration.class);

	}
}
