package techkids.mad3.sms;

import java.util.ArrayList;

/**
 * Created by TrungNT on 5/11/2016.
 */
public class MessageList extends Message{
    private String phone;
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    private ArrayList<Message> arrListContentMessage;

    public ArrayList<Message> getArrListContentMessage() {
        return arrListContentMessage;
    }

    public void setArrListMessage(ArrayList<Message> arrListContentMessage) {
        this.arrListContentMessage = arrListContentMessage;
    }

    public void setType(int type)
    {
        this.type = type;
    }

    public int getType()
    {
       return this.type;
    }

    public void setContent(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return this.content;
    }

    private void setDate(String date)
    {
        this.date = date;
    }

    public String getDate()
    {
        return this.date;
    }

    public MessageList()
    {
        this.phone = "";
        arrListContentMessage = null;
    }

    public MessageList(String phone, ArrayList<Message> arrListMessage)
    {
        this.phone = phone;
        this.arrListContentMessage = arrListMessage;
    }

    //dinh nghia kiem tra phan tu cuoi cung co trong mang arrListSMSBodyReceive khong?
    public Message getLastSMSBodyElement()
    {
        if (arrListContentMessage.size() == 0)
            return null;

        return arrListContentMessage.get(arrListContentMessage.size()-1);
    }
}
