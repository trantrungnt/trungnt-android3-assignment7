package techkids.mad3.sms;

import java.util.AbstractList;
import java.util.ArrayList;

/**
 * Created by TrungNT on 5/3/2016.
 */
public class SendSMS extends Message {
    public ArrayList<SMSBody> getArrListSendSMS() {
        return arrListSendSMS;
    }

    private ArrayList<SMSBody> arrListSendSMS;

    private SendSMS()
    {
        this.phone = "";
        arrListSendSMS = null;
    }

    public SendSMS(String phone, String contentSMSSend, String date)
    {
        this.phone = phone;
        this.arrListSendSMS = new ArrayList<>();
        this.arrListSendSMS.add(new SMSBody(contentSMSSend, date));
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //lay gia tri cua phan tu cuoi cung trong mang arrListSendSMS neu phan tu do co gia tri va nam trong mang nay
    public SMSBody getLastElementSMSBody()
    {
        if (arrListSendSMS.size()==0)
            return null;

        return arrListSendSMS.get(arrListSendSMS.size()-1);
    }
}
