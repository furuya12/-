package com.example.demo.model;

import java.util.List;
import java.util.Map;

import lombok.Data;

@Data
public class KintaiCorrespondence {
    private List<Map<String,String>> target_emplyee;
    
	public List<Map<String, String>> getTarget_emplyee() {
		return target_emplyee;
	}
	public void setTarget_emplyee(List<Map<String, String>> target_emplyee) {
		this.target_emplyee = target_emplyee;
	}
}
