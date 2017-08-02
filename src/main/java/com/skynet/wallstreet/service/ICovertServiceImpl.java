package com.skynet.wallstreet.service;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.jsoup.helper.StringUtil;
import org.springframework.stereotype.Component;

import com.skynet.wallstreet.common.Constants;
import com.skynet.wallstreet.model.BaseEntity;
import com.skynet.wallstreet.model.Finance;
import com.skynet.wallstreet.model.Stock;
import com.skynet.wallstreet.service.interfaces.ICovertService;
import com.skynet.wallstreet.util.FileUtil;
import com.skynet.wallstreet.util.PropUtil;
import com.skynet.wallstreet.util.ReflectUtil;
@Component("importService")
public class ICovertServiceImpl <T extends BaseEntity, PK extends Serializable> 
						extends IBaseServiceImpl<BaseEntity, PK >
						implements ICovertService{

	Map<String, String> companys = PropUtil.readValue(Constants.COMPANY_PATH);

	public void importStock(String stockPath, String fileName) {
		System.out.println(fileName);
		List<List<String>> csv = FileUtil.readCSVFile(stockPath + fileName);
//			System.out.println(csv);
		String code = fileName.trim().split("\\.")[0];
		List<Stock> stockList = convertCsvStock(csv, code, companys.get(code));
		for(Stock stock : stockList) {
			save(stock);
		}
	}
	
	public List<Stock> convertCsvStock(List<List<String>> csv, String code, String company) {
		List<Stock> stockList = new ArrayList<Stock>();
		int len = csv.size();
		int start = 2;
		for(int i=start;i<len;i++) {
			List<String> row = csv.get(i);
			if(row.size() < 7) {
				continue;
			}
//			System.out.println("row:" + row);
			Stock stock = new Stock();
			stock.setCode(code);
			stock.setCompany(company);
			stock.setReportDate(row.get(0).replace("/", ""));
			stock.setOpening(Double.valueOf(row.get(1)));
			stock.setTop(Double.valueOf(row.get(2)));
			stock.setBottom(Double.valueOf(row.get(3)));
			stock.setEnding(Double.valueOf(row.get(4)));
			stock.setDealCount(Long.valueOf(row.get(5)));
			stock.setDealAmount(Double.valueOf(row.get(6)));
			stockList.add(stock);
		}
		return stockList;
	}
	
//	@Transactional
	public void importFinance() {
		try {
			//文件根目录
			String financePath = Constants.BASE_PATH + "finance2017-07-19(170614)/";
			//资产负债表目录
			String balancePath = financePath + "balance/";
			//利润损益表目录
			String profitPath = financePath + "profit/";
			//现金流量表目录
			String cashFlowPath = financePath + "cashFlow/";
			
			//资产负债表文件名数组
			String[] balanceFileArray = new File(balancePath).list();
			//利润损益表文件名数组
			String[] profitFileArray = new File(profitPath).list();
			//现金流量表文件名数组
			String[] cashFlowFileArray = new File(cashFlowPath).list();
			
			Map<String,Finance> finances = new HashMap<String, Finance>();
			for(String path : balanceFileArray) {
				List<List<String>> csv = FileUtil.readCSVFile(balancePath + path);
				covertCsvFinance(csv, path, finances);
			}
			for(String path : profitFileArray) {
				List<List<String>> csv = FileUtil.readCSVFile(profitPath + path);
				covertCsvFinance(csv, path, finances);
			}
			for(String path : cashFlowFileArray) {
				List<List<String>> csv = FileUtil.readCSVFile(cashFlowPath + path);
				covertCsvFinance(csv, path, finances);
			}

			System.out.println("finances size:" + finances.size());
			for(Entry<String, Finance> entry : finances.entrySet()) {
				save(entry.getValue());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void covertCsvFinance(List<List<String>> csv, String fileName, Map<String, Finance> finances) {
//		System.out.println("fileName:" + fileName);
		String[] strArr = fileName.split("_");
		String company = strArr[0];
		String code = strArr[1];
		List<String> reportDateRow = csv.get(0);
		int dataIndex = 2;//真实数据起点
//		String typeName = "";
		for(int i=1;i<reportDateRow.size();i++) {
			String reportDate = reportDateRow.get(i);
			String financeKey = code+"_"+reportDate;
//			System.out.println(reportDate);
			if(StringUtil.isBlank(reportDate) || "19700101".equals(reportDate)) {
				continue;
			}
			Finance finance = finances.get(financeKey);
			if(finance == null) {
				finance = new Finance();
				finance.setCode(code);
				finance.setCompany(company);
				finance.setReportDate(reportDate);
				finances.put(financeKey, finance);
//				System.out.println("new key:" + financeKey);
			} else {
//				System.out.println("old key:" + financeKey);
			}
			for(int j=dataIndex;j<csv.size();j++) {
				List<String> row = csv.get(j);
				if(row.size() == 1) {
					continue;
				}
				String key = row.get(0);
				String name = Constants.nameMap.get(key);
				if(StringUtil.isBlank(name)) {
					continue;
				}
				String value = row.get(i);
//				System.out.println(row.get(0) + "->" + name + ":" + value);
				ReflectUtil.assignmentField(finance, name, Double.valueOf(value));
			}
//			System.out.println();
		}
	}
	
	public static void main(String[] args) {
//		importFinance();
		//String path = Constants.BASE_PATH + "finance2017-07-19(170614)/balance/红豆股份_600400__资产负债表.xls";
		//System.out.println(new File(path).getName());
		/**
		List<List<String>> csv = FileUtil.readCSVFile(path);
		List<Finance> finances = coverCsvData(csv, "红豆股份_600400__资产负债表");
		BaseDao baseDao = new BaseDao();
		for(Finance finance : finances) {
			System.out.println("货币资金：->" + finance.getCash());
			baseDao.save(finance);
		}
		DBUtil.close();
		*/
		
		//importFinance();
	}
}
