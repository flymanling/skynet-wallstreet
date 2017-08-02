package com.skynet.wallstreet.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Component;

import com.skynet.wallstreet.common.Constants;
import com.skynet.wallstreet.model.BaseEntity;
import com.skynet.wallstreet.model.Finance;
import com.skynet.wallstreet.service.interfaces.IAnalyseService;
import com.skynet.wallstreet.service.interfaces.QuerySettable;
import com.skynet.wallstreet.util.PropUtil;

@Component("analyseService")
public class IAnalyseServiceImpl <T extends BaseEntity, PK extends Serializable> 
					extends IBaseServiceImpl<Finance, PK > 
					implements IAnalyseService{

	public void findIncreaseCompany() {
		Map<String, String> companys = PropUtil.readValue(Constants.COMPANY_PATH);
		List<String> bingoCode = new ArrayList<String>();
		for(String code : companys.keySet()) {
			try{
				
				System.out.println("running " + code);
				boolean rs = findIncreaseCode(code);
				if(rs) {
					bingoCode.add(code);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("bingoCode:" + bingoCode);
	}
	
	public boolean findIncreaseCode(final String code) {
		List<Finance> list = findList(code);
		return analyse(code, list);
	}
	
	public List<Finance> findList(final String code) {
		String hql = "from finance as f where f.code=:code and f.reportDate<'19970101' order by f.reportDate desc";
		
		return list(hql, new QuerySettable(){
			public void setQuery(Query query) {
				query.setString("code", code);
			}
		});
	}
	
	public static boolean analyse(String code, List<Finance> list) {
		if(list == null || list.size() < 6) {
			return false;
		}
		Double lastTaking = list.get(0).getTotalTaking();
		Double lastProfit = list.get(0).getNetProfit();
		if(lastTaking == null || lastProfit == null) {
			return false;
		}
		List<String> dateList = new ArrayList<String>();
		dateList.add(list.get(0).getReportDate());
		List<Double> takingList = new ArrayList<Double>();
		List<Double> profitList = new ArrayList<Double>();
		int keep = 0;
		for(int i=1;i<list.size();i++) {
			if(keep >=3) {
				return true;
			}
			Finance finance = list.get(i);
			if(finance.getTotalTaking()*1.1 > lastTaking) {
				return false;
			}
			if(finance.getNetProfit()*1.1 > lastProfit) {
				return false;
			}
			keep++;
			takingList.add(lastTaking);
			profitList.add(lastProfit);
			dateList.add(finance.getReportDate());
			lastTaking = finance.getTotalTaking();
			lastProfit = finance.getNetProfit();
		}
		takingList.add(lastTaking);
		profitList.add(lastProfit);
		System.out.println("code:" + code);
		System.out.println("date:" + dateList);
		System.out.println("takingList:" + takingList);
		System.out.println("profitList:" + profitList);
		return true;
	}
	
	public static void main(String[] args) {
		/*
		Session session = DBUtil.getSession();
		findIncreaseCompany(session);
//		isIncrease("000651", session);
		session.close();
		DBUtil.close();
		*/
		
		List<Finance> list = new ArrayList<Finance>();
		for(int i=10;i>0;i--) {
			Finance f = new Finance();
			f.setReportDate(i+"");
			f.setTotalTaking(i*100 + 100.0);
			f.setNetProfit(i*10 + 10.0);
			list.add(f);
		}
		for(Finance f : list) {
			System.out.println(f.getReportDate() + ", " + f.getTotalTaking() + ", " + f.getNetProfit());
		}
		boolean rs = analyse("11111", list);
		System.out.println(rs);
		
	}
}
