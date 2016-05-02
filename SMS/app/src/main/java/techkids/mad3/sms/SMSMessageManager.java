package techkids.mad3.sms;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by TrungNT on 4/29/2016.
 */
public class SMSMessageManager extends Application {
    private static SMSMessageManager ourInstance = new SMSMessageManager();
    private ArrayList<ReceiveSMS> arrListSMSMessage = new ArrayList<>();

    public ArrayList<SendSMS> getArrayListSMSSend() {
        return arrayListSMSSend;
    }

    private ArrayList<SendSMS> arrayListSMSSend = new ArrayList<>();

    public ArrayList<ReceiveSMS> getArrSMSMessage() {
        return arrListSMSMessage;
    }

    public static SMSMessageManager getOurInstance() {
        return ourInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        ourInstance = this;
    }
}
