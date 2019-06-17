package com.uniyapps.smartvalleyautomation;

import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.uniyapps.smartvalleyautomation.Operations.DatabaseOperations;

public class AutoActivity extends AppCompatActivity {

    Switch aSwitch ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Auto");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.black)));

        aSwitch =  findViewById(R.id.swi);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    DatabaseOperations.getInstance().setAutoMotor(1);
                else
                    DatabaseOperations.getInstance().setAutoMotor(3);
            }
        });
    }
}
