package com.uniyapps.smartvalleyautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.uniyapps.smartvalleyautomation.Operations.RealtimeClass;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void ButGotoPower(View view) {
        startActivity(new Intent(this,EnterIPAddressActivity.class));
        finish();
    }

}
