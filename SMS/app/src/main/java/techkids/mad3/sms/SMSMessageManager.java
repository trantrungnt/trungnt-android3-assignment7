package techkids.mad3.sms;

import android.app.Application;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by TrungNT on 4/29/2016.
 */
public class SMSMessageManager extends Application {
    private static SMSMessageManager ourInstance = new SMSMessageManager();
    private static ListSMSMessageAdapter listSMSMessageAdapter;
    private static ListView listViewSMSMessage;

    public void setListMessageAdapter(ListSMSMessageAdapter listSMSMessageAdapter)
    {
        this.listSMSMessageAdapter = listSMSMessageAdapter;
    }

    public ListSMSMessageAdapter getListMessageAdapter()
    {
        return listSMSMessageAdapter;
    }

    public void setListViewSMSMessage(ListView listViewSMSMessage)
    {
        this.listViewSMSMessage = listViewSMSMessage;
    }

    public ListView getlistViewSMSMessage()
    {
        return listViewSMSMessage;
    }


    private ArrayList<MessageList> arrMessageList = new ArrayList<>();

    public ArrayList<MessageList> getarrMessageList() {
        return arrMessageList;
    }

    private static ListSMSSendAdapter listSMSSendAdapter;
    private static ListView listViewSMSSend;

    public void setListSMSSendAdapter(ListSMSSendAdapter listSMSSendAdapter)
    {
        this.listSMSSendAdapter = listSMSSendAdapter;
    }

    public ListSMSSendAdapter getListSMSSendAdapter()
    {
        return this.listSMSSendAdapter;
    }

    public void setListViewSMSSend(ListView listViewSMSSend)
    {
        this.listViewSMSSend = listViewSMSSend;
    }

    public ListView getListViewSMSSend()
    {
        return this.listViewSMSSend;
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
