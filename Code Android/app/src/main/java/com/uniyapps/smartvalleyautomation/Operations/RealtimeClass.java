package com.uniyapps.smartvalleyautomation.Operations;


 import com.uniyapps.smartvalleyautomation.Interfaces.IResultReceive;
import com.uniyapps.smartvalleyautomation.Model.DataBoard;
 import com.uniyapps.smartvalleyautomation.Model.DataModel;

 import org.greenrobot.eventbus.EventBus;

public class RealtimeClass extends Thread {

    public static boolean THREAD_MAIN  = true;
    @Override
    public void run() {
        super.run();
        while(THREAD_MAIN){
            DatabaseOperations.getInstance().getData(new IResultReceive() {
                @Override
                public void on_result(DataBoard dataBoard) {
                    EventBus.getDefault().post(new DataModel(dataBoard));
                }
            });
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
