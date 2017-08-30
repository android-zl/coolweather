package app.coolweather.com.coolweather.util;

import android.text.TextUtils;

import app.coolweather.com.coolweather.db.CoolWeatherDB;
import app.coolweather.com.coolweather.model.City;
import app.coolweather.com.coolweather.model.County;
import app.coolweather.com.coolweather.model.Province;

/**
 * Created by wo on 2017/8/26.
 */

public class Utility {
    /**
     * 解析和处理服务器返回的省级数据
     * @param coolWeatherDB
     * @param response
     * @return
     */
    public synchronized  static boolean handlerProvincesResponse(CoolWeatherDB coolWeatherDB,String response){
        if (!TextUtils.isEmpty(response)){
            String[] allprovince = response.split(",");
            if (allprovince != null && allprovince.length > 0){
                for(String p : allprovince){
                    String[] array = p.split("\\|");
                    Province province = new Province();
                    province.setProvinceCode(array[0]);
                    province.setProvinceName(array[1]);
                    //将解析出来的数据存储到province表
                    coolWeatherDB.saveProvince(province);
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的市级数据
     * @param coolWeatherDB
     * @param response
     * @return
     */
    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response ,int provinceId){
        if (!TextUtils.isEmpty(response)){
            String[] allCities  = response.split(",");
                if (allCities != null && allCities.length>0){
                    for (String c : allCities){
                        String[] array = c.split("\\|");
                        City city = new City();
                        city.setCityCode(array[0]);
                        city.setCityName(array[1]);
                        //将解析出来的数据存储到city表
                        coolWeatherDB.saveCity(city);
                    }
                    return true;
                }
        }
        return false;
    }

    /**
     * 解析和处理服务器返回的县级数据
     * @param coolWeatherDB
     * @param response
     * @return
     */
    public static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response , int cityId){
        if (!TextUtils.isEmpty(response)){
            String[] allCounties = response.split(",");
            if (allCounties != null && allCounties.length > 0){
                for (String c : allCounties){
                    String[] array = c.split("\\|");
                    County county = new County();
                    county.setCountyCode(array[0]);
                    county.setCountyName(array[1]);
                    coolWeatherDB.saveCounty(county);
                }
                return true;
            }
        }
        return false;
    }
}
