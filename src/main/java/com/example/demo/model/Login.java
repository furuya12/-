package com.example.demo.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class Login {

	/**  社員番号      */
	@NotBlank(message = "社員番号を入力してください")
	private String number;
	
	/**  パスワード      */
	@NotBlank(message = "パスワードを入力してください")
    private String password;
	
	/**  エラーメッセージ      */
    private String errorMsg;
    
    /**  社員番号GET      */
    public String getNumber() {
		return number;
	}

    /**  社員番号SET      */
    public void setNumber(String number) {
		this.number = number;
	}

    /**  パスワードGET      */
    public String getPassword() {
		return password;
	}
	
    /**  パスワードSET      */
	public void setPassword(String password) {
		this.password = password;
	}
	
    /**  エラーメッセージGET      */
    public String getErrorMsg() {
		return errorMsg;
	}
	
    /**   エラーメッセージSET      */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
}
