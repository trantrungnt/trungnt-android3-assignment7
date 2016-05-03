package techkids.mad3.sms;

import java.util.ArrayList;

/**
 * Created by TrungNT on 5/3/2016.
 */
public class ReceiveSMS extends Message{
    public ArrayList<SMSBody> getArrListSMSBodyReceive() {
        return arrListSMSBodyReceive;
    }

    private ArrayList<SMSBody> arrListSMSBodyReceive;

    private ReceiveSMS()
    {
        this.phone = "";
        arrListSMSBodyReceive = null;
    }

    public ReceiveSMS(String phone, String content, String date)
    {
        this.phone = phone;
        this.arrListSMSBodyReceive = new ArrayList<>();
        this.arrListSMSBodyReceive.add(new SMSBody(content, date));
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    //dinh nghia kiem tra phan tu cuoi cung co trong mang arrListSMSBodyReceive khong?
    public SMSBody getLastSMSBodyElement()
    {
        if (arrListSMSBodyReceive.size() == 0)
            return null;

        return arrListSMSBodyReceive.get(arrListSMSBodyReceive.size()-1);
    }
}
