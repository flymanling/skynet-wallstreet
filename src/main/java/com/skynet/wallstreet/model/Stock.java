package com.skynet.wallstreet.model;

import javax.persistence.Entity;

@Entity(name="stock")
public class Stock extends BaseEntity{

	private static final long serialVersionUID = 4930285035985081786L;

	
	private String code;
	
	private String company;
	
	private String reportDate;
	
	private Double opening;
	
	private Double top;
	
	private Double bottom;
	
	private Double ending;
	
	private Long dealCount;
	
	private Double dealAmount;

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

	public Double getOpening() {
		return opening;
	}

	public void setOpening(Double opening) {
		this.opening = opening;
	}

	public Double getTop() {
		return top;
	}

	public void setTop(Double top) {
		this.top = top;
	}

	public Double getBottom() {
		return bottom;
	}

	public void setBottom(Double bottom) {
		this.bottom = bottom;
	}

	public Double getEnding() {
		return ending;
	}

	public void setEnding(Double ending) {
		this.ending = ending;
	}

	public Long getDealCount() {
		return dealCount;
	}

	public void setDealCount(Long dealCount) {
		this.dealCount = dealCount;
	}

	public Double getDealAmount() {
		return dealAmount;
	}

	public void setDealAmount(Double dealAmount) {
		this.dealAmount = dealAmount;
	}
}
