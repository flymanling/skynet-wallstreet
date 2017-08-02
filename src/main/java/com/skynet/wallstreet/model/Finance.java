package com.skynet.wallstreet.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

/**
 * 资产负债表
 * @author air
 *
 */
@Entity(name="finance")
public class Finance extends BaseEntity{
	
	private static final long serialVersionUID = 4402457914874760118L;
//	private int fid;
	/**
	 * 公司代码
	 */
	private String code;
	/**
	 * 公司名称
	 */
	private String company;
	/**
	 * 报表日期
	 */
	private String reportDate;
	/**
	 * 货币资金
	 */
	private Double cash;
	/**
	 * 所有者权益(或股东权益)合计
	 */
	private Double netAssets;
	/**
	 * 总负债
	 */
	private Double totalDebt;
	/**
	 * 总资产
	 */
	private Double totalAssets;
	/**
	 * 营业总收入
	 */
	private Double totalTaking;
	/**
	 * 净利润
	 */
	private Double netProfit;
	/**
	 * 现金增加额
	 */
	private Double cashFlowIncreasing;
	/**
	 * 现金余额
	 */
	private Double cashFlowRemaining;
	
//	@Id
//	@GeneratedValue(generator="increment")
//	@GenericGenerator(name="increment", strategy="increment")
//	public int getFid() {
//		return fid;
//	}
//	public void setFid(int fid) {
//		this.fid = fid;
//	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getReportDate() {
		return reportDate;
	}
	public void setReportDate(String reportDate) {
		this.reportDate = reportDate;
	}
	public Double getCash() {
		return cash;
	}
	public void setCash(Double cash) {
		this.cash = cash;
	}
	public Double getNetAssets() {
		return netAssets;
	}
	public void setNetAssets(Double netAssets) {
		this.netAssets = netAssets;
	}
	public Double getTotalDebt() {
		return totalDebt;
	}
	public void setTotalDebt(Double totalDebt) {
		this.totalDebt = totalDebt;
	}
	public Double getTotalAssets() {
		return totalAssets;
	}
	public void setTotalAssets(Double totalAssets) {
		this.totalAssets = totalAssets;
	}
	public Double getTotalTaking() {
		return totalTaking;
	}
	public void setTotalTaking(Double totalTaking) {
		this.totalTaking = totalTaking;
	}
	public Double getNetProfit() {
		return netProfit;
	}
	public void setNetProfit(Double netProfit) {
		this.netProfit = netProfit;
	}
	public Double getCashFlowIncreasing() {
		return cashFlowIncreasing;
	}
	public void setCashFlowIncreasing(Double cashFlowIncreasing) {
		this.cashFlowIncreasing = cashFlowIncreasing;
	}
	public Double getCashFlowRemaining() {
		return cashFlowRemaining;
	}
	public void setCashFlowRemaining(Double cashFlowRemaining) {
		this.cashFlowRemaining = cashFlowRemaining;
	}
}
