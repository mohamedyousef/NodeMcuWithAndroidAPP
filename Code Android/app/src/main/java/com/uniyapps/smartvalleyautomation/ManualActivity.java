package com.uniyapps.smartvalleyautomation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.uniyapps.smartvalleyautomation.Interfaces.IRun;
import com.uniyapps.smartvalleyautomation.Operations.DatabaseOperations;

import java.util.ArrayList;

public class ManualActivity extends AppCompatActivity {

    ImageView upbtn,down;
    CardView stop , sensorcard;
    TextView sensorvalue ;
    Spinner spinner;
    int state  = 0;
    CardView btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual);
        upbtn = findViewById(R.id.upbtn);
        down = findViewById(R.id.down);
        stop = findViewById(R.id.stop);
        sensorvalue = findViewById(R.id.sensor_value_txt);
        sensorcard = findViewById(R.id.sensorcard);
        spinner  = findViewById(R.id.speed_motor);
        btn = findViewById(R.id.waterbtn);

        getSupportActionBar().setDisplayShowHomeEnabled(true);


        upbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                DatabaseOperations.getInstance().Up_down_stop(new IRun() {
                    @Override
                    public void result(boolean state) {
                        if (state) {
                            stop.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                            upbtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_upward_green_24dp));
                            down.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_downward_black_24dp));
                        }else{
                            Toast.makeText(ManualActivity.this, "تاكد من اتصالك بالانترنت", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 1);
            }
        });

        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                DatabaseOperations.getInstance().Up_down_stop(new IRun() {
                    @Override
                    public void result(boolean state) {
                        if (state) {
                            v.setBackgroundColor(getResources().getColor(R.color.orange));
                            upbtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_upward_black_24dp));
                            down.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_downward_black_24dp));
                        }
                        else
                            Toast.makeText(ManualActivity.this, "تاكد من اتصالك بالانترنت", Toast.LENGTH_SHORT).show();


                    }
                }, 0);
            }
        });


        down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                DatabaseOperations.getInstance().Up_down_stop(new IRun() {
                    @Override
                    public void result(boolean state) {
                        if (state)  {
                       stop.setBackgroundColor(getResources().getColor(android.R.color.holo_red_light));
                       upbtn.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_upward_black_24dp));
                       down.setImageDrawable(getResources().getDrawable(R.drawable.ic_arrow_downward_green_24dp));
                       }else{
                            Toast.makeText(ManualActivity.this, "تاكد من اتصالك بالانترنت", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, 2);
            }
        });



        ArrayList<String>strings = new ArrayList<>();
        strings.add("First Speed");
        strings.add("Second Speed");
        strings.add("Third Speed");

        spinner.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,strings));
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                DatabaseOperations.getInstance().setControlSpeedOfMotor(position+1);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void RunMotorWater(final View view) {
        if (state == 0) {
            DatabaseOperations.getInstance().RunORStopMotor(1);
            state = 1;
            btn.setBackgroundColor(getResources().getColor(android.R.color.holo_orange_dark));
        } else {
            DatabaseOperations.getInstance().RunORStopMotor(0);
            state=0;
            btn.setBackgroundColor(getResources().getColor(android.R.color.background_dark));
        }
    }

}

