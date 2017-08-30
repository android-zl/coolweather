package app.coolweather.com.coolweather.util;

/**
 * Created by wo on 2017/8/25.
 */

public interface HttpCallBackListener {
    void onFinish(String response);
    void onError(Exception e);
}
