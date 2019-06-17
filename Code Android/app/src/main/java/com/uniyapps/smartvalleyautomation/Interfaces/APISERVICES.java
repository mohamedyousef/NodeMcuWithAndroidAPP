package com.uniyapps.smartvalleyautomation.Interfaces;

import com.uniyapps.smartvalleyautomation.Model.DataBoard;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

public interface APISERVICES {

    @GET("data/")
    Call<DataBoard> getData();
    @GET("led/on")
    Call<ResponseBody>ledcontrolOn();
    @GET("led/off")
    Call<ResponseBody>ledcontrolOFF();
    @GET("water/run")
    Call<ResponseBody>wateron();
    @GET("water/stop")
    Call<ResponseBody>wateroff();
    @GET("motor/for")
    Call<ResponseBody>motorfor();
    @GET("motor/stop")
    Call<ResponseBody>motorstop();
    @GET("motor/rev")
    Call<ResponseBody>motorrev();
    @GET("autoorman/man")
    Call<ResponseBody>manualon();
    @GET("autoorman/auto")
    Call<ResponseBody>autoon();
    @GET("autoorman/man")
    Call<ResponseBody>autoOF();

}
