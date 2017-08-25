package app.coolweather.com.coolweather.util;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import app.coolweather.com.coolweather.db.CoolWeatherOpenHelper;
import app.coolweather.com.coolweather.model.City;
import app.coolweather.com.coolweather.model.County;
import app.coolweather.com.coolweather.model.Province;

/**
 * Created by wo on 2017/8/25.
 */

public class CoolWeatherDB {
    /**
     * 数据库名
     */
    public static final String DB_NAME = "cool_weather";
    /**
     * 数据库版本
     */
    public static final int VERSION = 1;
    private static CoolWeatherDB coolWeatherDB;
    private SQLiteDatabase db;

    /**
     * 将构造方法私有化
     */
    private CoolWeatherDB(Context contex){
        CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(contex,DB_NAME,null,VERSION);
        db = dbHelper.getWritableDatabase();
    }
    /**
     * 获取CoolWeatherDB实例
     */
    public synchronized static CoolWeatherDB getInstance(Context context){
        if (coolWeatherDB == null){
            coolWeatherDB = new CoolWeatherDB(context);
        }
        return coolWeatherDB;
    }
    /**
     * 将Province实例储存到数据库
     */
    public void saveProvince(Province province){
        if (province != null){
            ContentValues values = new ContentValues();
            values.put("province_name",province.getProvinceName());
            values.put("province_code",province.getProvinceCode());
            db.insert("Province",null,values);
        }
    }
    /**
     * 从数据库读取全国所有的省份信息
     */
    public List<Province> loadProvinces(){
        List<Province> list = new ArrayList<>();
        Cursor cursor = db.query("Private",null,null,null,null,null,null);
        if (cursor.moveToFirst()){
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("private_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            }while (cursor.moveToNext());

        }
        if (cursor != null){
            cursor.close();
        }
        return list;
    }
    /**
     * 将City实例储存在数据库
     */
    public void saveCity(City city){
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name",city.getCityName());
            values.put("city_code",city.getCityCode());
            values.put("province_id",city.getPriovinceId());
            db.insert("city",null,values);
        }
    }
    /**
     * 从数据库读取某省下所有的城市信息
     */
    public List<City> loadCities(int provinceId){
        List<City> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from city wehre province_id = ?", new String[]{String.valueOf(provinceId)});
        if (cursor.moveToFirst()){
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setPriovinceId(provinceId);
                list.add(city);
            }while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return list;
    }
    /**
     * 将County实例储存到数据库
     */
    public void saveCounty(County county){
        if (county != null){
            ContentValues contentValues = new ContentValues();
            contentValues.put("county_name",county.getCountyName());
            contentValues.put("county_code",county.getCountyCode());
            contentValues.put("city_id",county.getCityId());
            db.insert("county",null,contentValues);
        }
    }
    /**
     * 从数据库读取某城市下所有县的信息
     */
    public List<County> loadCounty(int cityId){
        List<County> list = new ArrayList<>();
        Cursor cursor = db.rawQuery("select * from county where = ?",new String[]{String.valueOf(cityId)});
        if (cursor.moveToFirst()){
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
            }while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return list;
    }
}

