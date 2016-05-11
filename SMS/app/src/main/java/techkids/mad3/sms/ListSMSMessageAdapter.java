package techkids.mad3.sms;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by TrungNT on 4/29/2016.
 */
public class ListSMSMessageAdapter  extends BaseAdapter {
    private ArrayList<MessageList> arrMessageList;
    private int LayoutItemID;
    private TextView txtPhone, txtContentSMSMessage;
    private Context mContext;
    private MessageList messageList;
    private ImageView avatar;

    @Override
    public int getCount() {
        return arrMessageList.size();
    }

    @Override
    public MessageList getItem(int position) {
        return arrMessageList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        messageList = arrMessageList.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.sms_message_template, parent, false);
        }

        avatar = (ImageView) convertView.findViewById(R.id.imgAvatar);
        txtPhone = (TextView) convertView.findViewById(R.id.tvPhone);
        txtContentSMSMessage = (TextView) convertView.findViewById(R.id.tvContentSMS);

        if (position%2==0)
        {
            avatar.setImageResource(R.drawable.person2);
        }
        else
            avatar.setImageResource(R.drawable.person1);

        txtPhone.setText(messageList.getPhone());

        if (Help.TYPE_RECEIVE_SMS == messageList.getType())
                txtContentSMSMessage.setText(messageList.getContent());

        return convertView;
    }

    public ListSMSMessageAdapter(Context mContext, int layoutItemID, ArrayList<MessageList> arrListMessage)
    {
        this.mContext = mContext;
        this.LayoutItemID = layoutItemID;
        this.arrMessageList = arrListMessage;
    }
}
