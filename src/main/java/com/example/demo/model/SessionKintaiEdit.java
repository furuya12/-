package com.example.demo.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author y.yamazaki
 * 勤怠編集画面の情報のセッション保管用クラス
 */
@Data
public class SessionKintaiEdit implements Serializable{

	private static final long serialVersionUID = 1L;

	// 編集モード
	private boolean editMode = false;
	
	// 編集対象社員番号
	private String edit_employee_number = "";

	// 表示中年
	private int year = 0;
	
	// 表示中年
	private int month = 0;

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}

	public String getEdit_employee_number() {
		return edit_employee_number;
	}

	public void setEdit_employee_number(String edit_employee_number) {
		this.edit_employee_number = edit_employee_number;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
}
