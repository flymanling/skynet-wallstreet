package com.skynet.wallstreet.service.interfaces;


public interface ICovertService {

	public void importFinance();
	
	public void importStock(String stockPath, String fileName);
}
