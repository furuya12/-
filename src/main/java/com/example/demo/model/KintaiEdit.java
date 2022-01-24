package com.example.demo.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class KintaiEdit {
	// 年月
	private String yearMonth;
	// 表データ
	private List<Map<String, String>> kintaiList;

	public List<Map<String, String>> getKintaiList() {
		return kintaiList;
	}

	public void setKintaiList(List<Map<String, String>> kintaiList) {
		this.kintaiList = kintaiList;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
}
