package com.uniyapps.smartvalleyautomation;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.EventLog;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.uniyapps.smartvalleyautomation.Fragments.DetailsFragment;
import com.uniyapps.smartvalleyautomation.Interfaces.IRun;
import com.uniyapps.smartvalleyautomation.Model.DataModel;
import com.uniyapps.smartvalleyautomation.Operations.DatabaseOperations;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class ControlActivity extends AppCompatActivity {

   CardView details ,  auto  , manual , light;
   boolean state = true;

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().removeStickyEvent(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();  ;
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_control);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        View viewActionBar = getLayoutInflater().inflate(R.layout.title_action_bar, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(//Center the textview in the ActionBar !
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView textviewTitle = viewActionBar.findViewById(R.id.mytext);
        textviewTitle.setText("Control Unit");
        actionBar.setCustomView(viewActionBar,params);

        details  = findViewById(R.id.card_details);
        light   = findViewById(R.id.card_goto_light);
        auto   = findViewById(R.id.card_auto);
        manual   = findViewById(R.id.card_goto_manual);

        details.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                animation1.setDuration(600);
                v.startAnimation(animation1);
                return false;
            }
        });
        light.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                animation1.setDuration(600);
                v.startAnimation(animation1);

                return false;
            }
        });

        auto.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                animation1.setDuration(600);
                v.startAnimation(animation1);
                return false;
            }
        });

        manual.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Animation animation1 = new AlphaAnimation(0.3f, 1.0f);
                animation1.setDuration(600);
                v.startAnimation(animation1);
                return false;
            }
        });


        auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ControlActivity.this,AutoActivity.class));
            }
        });
        manual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ControlActivity.this,ManualActivity.class));
            }
        });


        light.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatabaseOperations.getInstance().turnLightOnOrOFf(state,new IRun() {
                    @Override
                    public void result(boolean state) {
                        if (state) {
                            light.setCardBackgroundColor(getResources().getColor(R.color.yellow));
                        }else {
                            light.setCardBackgroundColor(getResources().getColor(R.color.blue));
                        }
                    }
                });
                state = !state;
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void ReciveData(DataModel dataModel){
        // do things
        // check what is running
        if (dataModel.getAuto()== 0)
        {
            manual.setCardBackgroundColor(getResources().getColor(R.color.yellow));
        }
        else if (dataModel.getAuto()==1)
        {
            auto.setCardBackgroundColor(getResources().getColor(R.color.yellow));
        }


    }


    public void BuGotoDetails(View view)  {
        DetailsFragment detailsFragment = new DetailsFragment();
        detailsFragment.show(getSupportFragmentManager(),DetailsFragment.TAG);
    }
}
