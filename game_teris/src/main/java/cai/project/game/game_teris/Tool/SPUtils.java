package cai.project.game.game_teris.Tool;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.util.ArrayList;

public final class SPUtils {
	
	private final static String name = "config";
	private final static int mode = Context.MODE_PRIVATE;


	/**
	 * 保存首选项
	 * @param context
	 * @param key
	 * @param value
	 */
	public static void saveBoolean(Context context, String key, boolean value){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		Editor edit = sp.edit();
		edit.putBoolean(key, value);
		edit.commit();
	}
	public static void saveInt(Context context, String key, int value){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		Editor edit = sp.edit();
		edit.putInt(key, value);
		edit.commit();
	}
	public static void saveString(Context context, String key, String value){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		Editor edit = sp.edit();
		edit.putString(key, value);
		edit.commit();
	}




	/**保存字符串集合*/
	public static void saveArrayListString(Context context, String key, ArrayList<String> list){

		Editor editor = context.getSharedPreferences(key, mode).edit();
		if (list != null) {
			editor.putInt("EnvironNums", list.size());
			for (int i = 0; i < list.size(); i++) {
				editor.putString("item_" + i, list.get(i));
			}
		}else{
			editor.putInt("EnvironNums", 0);
		}
		editor.commit();
	}


	public static ArrayList<String> getArrayListString(Context context, String key){
		ArrayList<String> environmentList = new ArrayList<String>();
		SharedPreferences preferDataList = context.getSharedPreferences(key, mode);
		int environNums = preferDataList.getInt("EnvironNums", 0);
		for (int i = 0; i < environNums; i++)
		{
			String environItem = preferDataList.getString("item_"+i, null);
			environmentList.add(environItem);
		}
		return environmentList;
	}

	/**
	 * 获取首选项
	 * @param context
	 * @param key
	 * @param defValue
	 * @return
	 */
	public static boolean getBoolean(Context context, String key, boolean defValue){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		return sp.getBoolean(key, defValue);
	}
	
	public static int getInt(Context context, String key, int defValue){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		return sp.getInt(key, defValue);
	}
	
	public static String getString(Context context, String key, String defValue){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		return sp.getString(key, defValue);
	}



	public static void delete(Context context, String key){
		SharedPreferences sp = context.getSharedPreferences(name, mode);
		Editor edit = sp.edit();
		edit.remove(key);
		edit.commit();
	}

}

