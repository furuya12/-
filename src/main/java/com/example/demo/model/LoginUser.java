package com.example.demo.model;

import java.io.Serializable;

import lombok.Data;

/**
 * @author y.yamazaki
 * ログインユーザ情報のセッション保管用クラス
 */
@Data
public class LoginUser implements Serializable{

	private static final long serialVersionUID = 1L;

	// 社員番号
	private String employee_number = "";
	// 所属チーム番号
	private String team_number = "";
	// チーム名
	private String team_name = "";
	// 氏名
	private String employee_name = "";
	// 権限コード
	private String authority_cord = "";
	// 権限名
	private String authority_name = "";
	
	public String getEmployee_number() {
		return employee_number;
	}
	public void setEmployee_number(String employee_number) {
		this.employee_number = employee_number;
	}
	public String getTeam_name() {
		return team_name;
	}
	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}
	public String getEmployee_name() {
		return employee_name;
	}
	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}
	public String getAuthority_name() {
		return authority_name;
	}
	public void setAuthority_name(String authority_name) {
		this.authority_name = authority_name;
	}
	public String getTeam_number() {
		return team_number;
	}
	public void setTeam_number(String team_number) {
		this.team_number = team_number;
	}
	public String getAuthority_cord() {
		return authority_cord;
	}
	public void setAuthority_cord(String authority_cord) {
		this.authority_cord = authority_cord;
	}
}
