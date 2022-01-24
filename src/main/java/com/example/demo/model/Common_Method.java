/**
 * 
 */
package com.example.demo.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * @author higashi
 *	汎用的に使われる処理を共通化したクラス
 */
public class Common_Method {
	/**
	 * csvを読み込みデータを返却するメソッド
	 * @param name:csvのファイル名
	 * @return　csvから読み込んだデータ
	 */
	
	public static List<Map<String,String>> csv_read(String name) {
		String url = "./src/main/resources/csv/";
		
		//csvのデータを保持する変数
		List<Map<String,String>> correctList = new ArrayList<>();
		
		//key(カラム名）　value(カラム値）
		HashMap<String,String> correctMap = new HashMap<>();
	    
		//csvのヘッター
		String line1 = "";
		
		//csvの値
		String line2 = "";
		
		// try-with-resources文はtryまたはcatchブロックを抜けた時点でtry()の括弧内に書いたリソースを解放してくれる
		try(
				FileInputStream fis = new FileInputStream(new File(url + name));
				InputStreamReader isr=new InputStreamReader(fis,"Shift-JIS");
				BufferedReader br=new BufferedReader(isr);
		 ){
			//カラム名配列を作成
			line1=br.readLine();
			String[] columName =line1.split(",");
			
			// 一行ずつデータを読み取り
			while((line2=br.readLine()) != null) {
				
				//行データインスタンスの初期化
				correctMap = new HashMap<>();
				
				//一つのレコードをカラム事に配列に設定
		        String[] correct = line2.split(",");
		        
		        //カラム数分ループ
		        for(int i=0; i < correct.length; i++) {
		        	//カラム名とカラム値をマップに設定
		        	correctMap.put(columName[i],correct[i]);
		        }
		        // 一つのレコード(Map<カラム名,カラム値>をリストに追加
		        correctList.add(correctMap);
		      }
		
			
		// ファイルが存在しない
		}catch (FileNotFoundException e) { 
			e.printStackTrace();
			
		// ファイルの読み込み中にエラーが発生ファイルの読み込み中にエラーが発生
		}catch (IOException e) {
			e.printStackTrace(); 
			
		// システムエラー
		}catch (Exception e){
			e.printStackTrace();
		}
		return correctList;
		
	}

}
