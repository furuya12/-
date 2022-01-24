package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.model.LoginUser;

@Controller
@SessionAttributes("loginUser")
public class PassEditController {

	@ModelAttribute("loginUser") // セッションに保存するオブジェクトの本体はメソッドに@ModelAttributeアノテーションを付けて作成する。
	public LoginUser cart() {
		return new LoginUser();
	}
	/**
	 * 戻るボタン押下時の処理
	 * メニュー画面へ遷移する。
	 * @return menu
	 */
	@PostMapping("/backMenu")
	private String backMenu(LoginUser loginUser) {
		return "menu";
	}
}
