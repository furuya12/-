package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.demo.model.Common_Method;
import com.example.demo.model.Login;
import com.example.demo.model.LoginUser;

import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@SessionAttributes("loginUser")
public class LoginController {

	@ModelAttribute("loginUser") // セッションに保存するオブジェクトの本体はメソッドに@ModelAttributeアノテーションを付けて作成する。
	public LoginUser cart() {
		return new LoginUser();
	}

	/**
	 * ログイン画面初期表示処理.
	 * 
	 * @param login ログインフォーム
	 * @return 遷移先画面 ログイン画面
	 */
	@GetMapping("/login")
	private String readLogin(@ModelAttribute Login login) {

		return "login";
	}

	/**
	 * メニュー画面初期表示処理.
	 * 
	 * @param login ログインフォーム
	 * @return 遷移先画面 メニュー画面
	 */
	@PostMapping("/menu")
	private String readMenu(@Validated Login login, BindingResult error, LoginUser loginUser) {

		// エラーメッセージをクリア
		login.setErrorMsg(null);

		if (error.hasErrors()) {
			return "login";
		}

		// ユーザマスタデータを取得
		List<Map<String, String>> userMst = Common_Method.csv_read("USERS_MST.csv");
		
		// ユーザマスタデータの中から画面入力値のユーザID・パスワードと一致するデータを検索する
		for (Map<String, String> userData : userMst) {
			if (login.getNumber().equals(userData.get("employee_number"))
					&& login.getPassword().equals(userData.get("password"))) {
					// 一致するデータが見つかった場合
				
				// チームマスタデータを取得する
				List<Map<String, String>> teamMst = Common_Method.csv_read("TEAM_MST.csv");
				// 権限マスタデータを取得する
				List<Map<String, String>> authorityMst = Common_Method.csv_read("AUTHORITY_MST.csv");
				
				// ログインユーザの情報をセッションに格納する
				// ログインID
				loginUser.setEmployee_number(login.getNumber());
				// 所属チーム番号
				loginUser.setTeam_number(userData.get("team_number"));
				// チーム名
				loginUser.setTeam_name("不明");
				for (Map<String, String> teamData : teamMst) {
					if (userData.get("team_number").equals(teamData.get("team_number"))){
						loginUser.setTeam_name(teamData.get("team_name"));
					}
				}
				// 氏名
				loginUser.setEmployee_name(userData.get("employee_name"));
				// 権限コード
				loginUser.setAuthority_cord(userData.get("authority_cord"));
				// 権限名
				loginUser.setAuthority_name("不明");
				for (Map<String, String> authorityData : authorityMst) {
					if (userData.get("authority_cord").equals(authorityData .get("authority_cord"))){
						loginUser.setAuthority_name(authorityData .get("authority_name"));
					}
				}
				// メニュー画面へ遷移する
				return "menu";
			}
		}
		// エラーメッセージを設定
		login.setErrorMsg("社員番号もしくはパスワードが間違っています");
		return "login";

	}

}