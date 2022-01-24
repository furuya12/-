package com.example.demo.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.model.Common_Method;
import com.example.demo.model.KintaiCorrespondence;
import com.example.demo.model.KintaiEdit;
import com.example.demo.model.SessionKintaiEdit;
import com.example.demo.model.Login;
import com.example.demo.model.LoginUser;

@Controller
@SessionAttributes({ "loginUser", "sessionKintaiEdit" })
public class KintaiCorrespondenceController {

	@ModelAttribute("loginUser") // セッションに保存するオブジェクトの本体はメソッドに@ModelAttributeアノテーションを付けて作成する。
	public LoginUser cart() {
		return new LoginUser();
	}

	@ModelAttribute("sessionKintaiEdit") // セッションに保存するオブジェクトの本体はメソッドに@ModelAttributeアノテーションを付けて作成する。
	public SessionKintaiEdit cart2() {
		return new SessionKintaiEdit();
	}

	// 勤怠編集画面表示
	@PostMapping("/kintai_correspondence")
	private String confirm(@ModelAttribute KintaiEdit kintaiEdit, String edit, String reference, LoginUser loginUser,
			SessionKintaiEdit sessionKintaiEdit, String prevMounth, String nextMounth) {
		// 表示中かどうかの判定

		// 表示中
		// 年月の再設定
		if (nextMounth != null) {
			// 前月ボタンが押されていない場合（次月ボタンが押された場合）
			if (sessionKintaiEdit.getMonth() == 12) {
				// 12月だった場合
				sessionKintaiEdit.setYear(sessionKintaiEdit.getYear() + 1);
				sessionKintaiEdit.setMonth(1);
			} else {
				sessionKintaiEdit.setMonth(sessionKintaiEdit.getMonth() + 1);
			}
		} else if (prevMounth != null) {
			if (sessionKintaiEdit.getMonth() == 1) {
				// 12月だった場合
				sessionKintaiEdit.setYear(sessionKintaiEdit.getYear() - 1);
				sessionKintaiEdit.setMonth(12);
			} else {
				sessionKintaiEdit.setMonth(sessionKintaiEdit.getMonth() - 1);
			}

		} else {
			// 初回表示
			// 編集モードの設定。編集ボタンの値がnullではない場合にtrueを設定
			sessionKintaiEdit.setEditMode(edit != null);
			// 編集ユーザの設定
			if (sessionKintaiEdit.isEditMode()) {
				// 編集モードの場合
				sessionKintaiEdit.setEdit_employee_number(edit);
			} else {
				// 編集モードではない場合
				sessionKintaiEdit.setEdit_employee_number(reference);
			}
			// 表示中年月の設定
			GregorianCalendar gcalendar = new GregorianCalendar();
			sessionKintaiEdit.setYear(gcalendar.get(Calendar.YEAR));
			sessionKintaiEdit.setMonth(gcalendar.get(Calendar.MONTH) + 1);
		}

		// 勤怠スケジュール表の表示内容の設定
		// 年月の設定
		kintaiEdit.setYearMonth(sessionKintaiEdit.getYear() + " 年" + sessionKintaiEdit.getMonth() + " 月　勤怠スケジュール");
		// 勤怠情報の取得

		// 表の作成
		// 月の日数取得
		Calendar c = Calendar.getInstance();
		c.clear();
		c.set(sessionKintaiEdit.getYear(), sessionKintaiEdit.getMonth() - 1, 1);
		int days = c.getActualMaximum(Calendar.DATE) - c.get(Calendar.DATE) + 1;
		// 週のひな型
		String[] week = { "日", "月", "火", "水", "木", "金", "土" };
		String[] week_color = { "color : red;", "color : black;", "color : black;", "color : black;", "color : black;",
				"color : black;", "color : blue;" };
		// 曜日設定用
		List<Map<String, String>> data = new ArrayList<Map<String, String>>();
		// 月の日数分繰り返す（i=1~days）
		for (int i = 1; i <= days; i++) {
			// 1行分データ
			Map<String, String> oneData = new HashMap<String, String>();
			// 固定で設定するもの
			// 日付を設定
			oneData.put("day", sessionKintaiEdit.getMonth() + "/" + String.valueOf(i));
			// 曜日を設定
			Calendar cal = Calendar.getInstance();
			cal.set(sessionKintaiEdit.getYear(), sessionKintaiEdit.getMonth() - 1, i);
			int weekNum = cal.get(Calendar.DAY_OF_WEEK) - 1;
			oneData.put("week", week[weekNum]);
			// 曜日の色を設定
			oneData.put("week_color", week_color[weekNum]);
			// 曜日の色を設定);
			// データから設定するもの
			// 出勤時間を設定

			// 退勤時間を設定

			// 備考を設定

			data.add(oneData);
		}
		kintaiEdit.setKintaiList(data);
		return "kintai_edit";
	}
}
