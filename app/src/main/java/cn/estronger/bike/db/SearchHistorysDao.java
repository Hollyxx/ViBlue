package cn.estronger.bike.db;

import java.util.ArrayList;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import cn.estronger.bike.bean.SearchHistorysBean;

import static java.lang.System.currentTimeMillis;

public class SearchHistorysDao {
	private DBSearchHelper helper;

	private SQLiteDatabase db;

	public SearchHistorysDao(Context context) {
		helper = new DBSearchHelper(context);
	}


	/**
	 * 对数据库进行增加或是update的操作
	 *
	 */

	public void addOrUpdate(String name,String address,double longitude,double latitude){
		SQLiteDatabase db = helper.getWritableDatabase();
		String sql = "select _id from t_historywords where name = ? ";
		Cursor cursor = db.rawQuery(sql, new String[]{name});
		if(cursor.getCount()>0){
			//说明数据库中已经有数据：更新数据库的时间：
			String sql_update = "update t_historywords set updatetime = ? where name = ? ";
			db.execSQL(sql_update, new String[]{currentTimeMillis()+"",name});
		}else{
			//直接插入一条记录：
			String sql_add = "insert into t_historywords(name,address,longitude,latitude,updatetime) values (?,?,?,?,?);";
			db.execSQL(sql_add, new String[]{name,address,longitude+"", latitude+"",System.currentTimeMillis()+""});
		}

		cursor.close();
		db.close();
	}

	/**
	 * 查询数据库中所有的数据
	 *
	 */

	public ArrayList<SearchHistorysBean> findAll(){
		ArrayList<SearchHistorysBean> data = new ArrayList<SearchHistorysBean>();;
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor cursor = db.query("t_historywords", null, null, null, null, null, "updatetime desc");
		//遍历游标，将数据存储在
		while(cursor.moveToNext()){
			SearchHistorysBean searchDBData = new SearchHistorysBean();
			searchDBData._id =cursor.getInt(cursor.getColumnIndex("_id"));
			searchDBData.name= cursor.getString(cursor.getColumnIndex("name"));
			searchDBData.address= cursor.getString(cursor.getColumnIndex("address"));
			searchDBData.updatetime = cursor.getLong(cursor.getColumnIndex("updatetime"));
			searchDBData.longitude = cursor.getDouble(cursor.getColumnIndex("longitude"));
			searchDBData.latitude = cursor.getDouble(cursor.getColumnIndex("latitude"));
			data.add(searchDBData);
		}
		cursor.close();
		db.close();
		return data;
	}


	/**
	 *
	 * 删除数据库中的所有数据
	 */

	public void deleteAll(){
		SQLiteDatabase db = helper.getReadableDatabase();
		db.execSQL("delete from t_historywords");
		db.close();
	}
	/**
	 *
	 * 删除数据库中的所有数据
	 */

	public void deleteOne(){
		SQLiteDatabase db = helper.getReadableDatabase();
		db.execSQL("delete from t_historywords where _id in (select _id from t_historywords limit 1)");
		db.close();
	}
}
