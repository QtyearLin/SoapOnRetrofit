package com.smilexie.retrofitsoap.webservice;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 接口请求
 * Created by Qtyearlin on 16/7/15.
 */
public interface WeatherInterfaceApi {

    @Headers({"Content-Type: text/xml;charset=UTF-8", "SOAPAction: http://tempuri.org/GetInvestByJkid"})
    @POST("/Mobile/MLoanService.asmx")
    Call<ResponseBean<JSONObject>> GetInvestByJkid(
            @Body String requestEnvelope);
}
