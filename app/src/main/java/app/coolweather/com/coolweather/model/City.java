package app.coolweather.com.coolweather.model;

/**
 * Created by wo on 2017/8/25.
 */

public class City {
    private int id;
    private String cityName;
    private String cityCode;
    private int priovinceId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCityCode() {
        return cityCode;
    }

    public void setCityCode(String cityCode) {
        this.cityCode = cityCode;
    }

    public int getPriovinceId() {
        return priovinceId;
    }

    public void setPriovinceId(int priovinceId) {
        this.priovinceId = priovinceId;
    }
}
