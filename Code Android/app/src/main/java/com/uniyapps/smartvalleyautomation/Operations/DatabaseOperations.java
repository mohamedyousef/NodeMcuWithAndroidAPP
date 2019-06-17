package com.uniyapps.smartvalleyautomation.Operations;


import com.uniyapps.smartvalleyautomation.Interfaces.APISERVICES;
import com.uniyapps.smartvalleyautomation.Interfaces.IResultReceive;
import com.uniyapps.smartvalleyautomation.Interfaces.IRun;
import com.uniyapps.smartvalleyautomation.Model.DataBoard;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DatabaseOperations {

    private static DatabaseOperations instance = null;
    APISERVICES apiservices;

    private DatabaseOperations(){
        apiservices = RetrofitInstance.getRetrofitInstance().create(APISERVICES.class);
    }
    public static DatabaseOperations getInstance() {
        if (instance == null)
            instance = new DatabaseOperations();
        return instance;
    }

    public void turnLightOnOrOFf(boolean state ,final IRun iRun) {
        Call<ResponseBody> responseBodyCall;
        if (state) {
            responseBodyCall = apiservices.ledcontrolOn();
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    iRun.result(true);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }else{
         responseBodyCall = apiservices.ledcontrolOFF();
         responseBodyCall.enqueue(new Callback<ResponseBody>() {
             @Override
             public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                 iRun.result(false);
             }

             @Override
             public void onFailure(Call<ResponseBody> call, Throwable t) {

             }
         });
        }

    }

    public void setAutoMotor(int val) {
        Call<ResponseBody> responseBodyCall;
        if (val==3)
        {
            responseBodyCall = apiservices.autoOF();
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
        else if(val==1){
            responseBodyCall = apiservices.autoon();
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
    }

    public void setControlSpeedOfMotor(int speed) {
        //set speed
     }

     // befor run and manual min = 0  max = 100
    public void setSensorValue(float limit) {
        //  sensorLimit
    }

    public void Up_down_stop(final IRun iRun, final int value){
     //send move up or down or stop
        Call<ResponseBody>responseBodyCall ;
        if (value == 1)
        {
            responseBodyCall = apiservices.motorfor();
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    iRun.result(true);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    iRun.result(false);
                }
            });
        }
       else if (value == 2)
        {
            responseBodyCall = apiservices.motorrev();
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    iRun.result(true);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    iRun.result(false);
                }
            });
        }

        else if (value == 0)
        {
            responseBodyCall = apiservices.motorstop();
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    iRun.result(true);
                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    iRun.result(false);
                }
            });
        }

    }
    public void RunORStopMotor(int value){
         // send order to run water pump
        Call<ResponseBody> responseBodyCall;
        if (value==0)   {
           responseBodyCall = apiservices.wateroff();
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
        else
        {
            responseBodyCall = apiservices.wateron();
            responseBodyCall.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                }

                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {

                }
            });
        }
    }
    public void getData(final IResultReceive iResultReceive){
        Call<DataBoard> dataBoard = apiservices.getData();
        dataBoard.enqueue(new Callback<DataBoard>() {
            @Override
            public void onResponse(Call<DataBoard> call, Response<DataBoard> response) {
                iResultReceive.on_result(response.body());
            }

            @Override
            public void onFailure(Call<DataBoard> call, Throwable t) {
                iResultReceive.on_result(null);
            }
        });
    }

}
