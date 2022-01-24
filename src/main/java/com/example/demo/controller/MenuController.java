package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.model.Common_Method;
import com.example.demo.model.KintaiCorrespondence;
import com.example.demo.model.Login;
import com.example.demo.model.LoginUser;

@Controller
@RequestMapping("/menu")
@SessionAttributes("loginUser")
public class MenuController {

	@ModelAttribute("loginUser") // セッションに保存するオブジェクトの本体はメソッドに@ModelAttributeアノテーションを付けて作成する。
	public LoginUser cart() {
		return new LoginUser();
	}

	/**
	 * 勤怠情報画面初期表示処理.
	 */
	@RequestMapping(value = "", params = "kintai_correspondence", method = RequestMethod.POST)
	private String readKintaijoho(KintaiCorrespondence kintaiCorrespondence, LoginUser loginUser) {

		// 参照可能ユーザの取得

		// ユーザマスタデータを取得
		List<Map<String, String>> userMst = Common_Method.csv_read("USERS_MST.csv");
		// チームマスタデータを取得する
		List<Map<String, String>> teamMst = Common_Method.csv_read("TEAM_MST.csv");

		// 権限ごとに参照可能データを絞り込み。表示番号・チーム名の付与
		// 画面に表示するデータ作成用変数
		List<Map<String, String>> createTarget_emplyee = new ArrayList<Map<String, String>>();
		int i = 1;
		for (Map<String, String> userData : userMst) {
			if ("1".equals(loginUser.getAuthority_cord())) {
				// 一般社員の場合
				if (!loginUser.getEmployee_number().equals(userData.get("employee_number"))) {
					// 社員番号が一致しない場合
					continue;
				}

			} else if ("2".equals(loginUser.getAuthority_cord())) {
				// 管理職の場合
				if (!loginUser.getTeam_number().equals(userData.get("team_number"))) {
					// チームが一致しない場合
					continue;
				}

			} else {
				// 営業の場合
				// 処理なし
			}
			// 表示番号の付与
			String no = String.valueOf(i);
			userData.put("no", String.format("%3s", no).replace(" ", "0"));
			i++;
			// チーム名の付与
			userData.put("team_name", "不明");
			// チーム番号を検索し、一致するチーム番号のチーム名を付与する
			for (Map<String, String> teamData : teamMst) {
				if (userData.get("team_number").equals(teamData.get("team_number"))) {
					userData.put("team_name", teamData.get("team_name"));
				}
			}
			//　画面に表示するデータ作成用変数に追加する
			createTarget_emplyee.add(userData);
		}

		// 画面に表示するデータをセット
		kintaiCorrespondence.setTarget_emplyee(createTarget_emplyee);

		return "kintai_correspondence";
	}

	/**
	 * ユーザ編集画面初期表示処理.
	 */
	@RequestMapping(value = "", params = "user_edit", method = RequestMethod.POST)
	private String readUserEdit(LoginUser loginUser) {

		return "user_edit";
	}

	/**
	 * パスワード編集画面初期表示処理.
	 */
	@RequestMapping(value = "", params = "pass_edit", method = RequestMethod.POST)
	private String readPassEdit(LoginUser loginUser) {

		return "pass_edit";
	}

}
