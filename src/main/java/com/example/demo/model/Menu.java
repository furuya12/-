package com.example.demo.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class Menu {
	private String aaa;

	public String getAaa() {
		return aaa;
	}

	public void setAaa(String aaa) {
		this.aaa = aaa;
	}

	private List<String> ccc = new ArrayList<String>();

	public List<String> getCcc() {
		return ccc;
	}

	public void setCcc(List<String> ccc) {
		this.ccc = ccc;
	}

	private List<Map<String, String>> ddd;

	public List<Map<String, String>> getDdd() {
		return ddd;
	}

	public void setDdd(List<Map<String, String>> ddd) {
		this.ddd = ddd;
	}

}
