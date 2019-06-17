package com.uniyapps.smartvalleyautomation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.uniyapps.smartvalleyautomation.Operations.RealtimeClass;
import com.uniyapps.smartvalleyautomation.Operations.RetrofitInstance;

public class EnterIPAddressActivity extends AppCompatActivity {

    EditText iptxt ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_ipaddress);
        iptxt = findViewById(R.id.buclick_enterIP);
    }
    public void ButGotoMain(View view) {
        if (!TextUtils.isEmpty(iptxt.getText())) {
        RetrofitInstance.BASE_URL = "http://"+iptxt.getText().toString()+"/";
       Intent intent = new Intent(this,ControlActivity.class);
        startActivity(intent);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
        finish();
        new RealtimeClass().start();

        }
        else
            Toast.makeText(this, "قم بكتابة عنوان ip الخاص بالبوردة", Toast.LENGTH_SHORT).show();
    }
}
