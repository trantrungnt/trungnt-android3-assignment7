package techkids.mad3.sms;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by TrungNT on 5/2/2016.
 */
public class ListSMSSendAdapter extends BaseAdapter {
    private ArrayList<MessageList> arrListMessage = new ArrayList<>();
    private int LayoutItemID;
    private TextView txtContentSMSReceive, txtContentSMSSend;
    private Context mContext;
    private MessageList message;
    private ImageView avatar;

    @Override
    public int getCount() {
        return arrListMessage.size();
    }

    @Override
    public MessageList getItem(int position) {
        return arrListMessage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        message = arrListMessage.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.answer_template, parent, false);
        }

        avatar = (ImageView) convertView.findViewById(R.id.imgAvatarReceive);
        txtContentSMSReceive = (TextView) convertView.findViewById(R.id.tvAnswerLeft);
        txtContentSMSSend = (TextView) convertView.findViewById(R.id.tvAnswerRight);

        if (position%2==0)
        {
            avatar.setImageResource(R.drawable.person2);
        }
        else
            avatar.setImageResource(R.drawable.person1);

        if (Help.TYPE_SEND_SMS == message.getType())
        {
            txtContentSMSSend.setText(message.getContent());
            txtContentSMSSend.setVisibility(View.VISIBLE);
            txtContentSMSReceive.setVisibility(View.GONE);
        }

        if (Help.TYPE_RECEIVE_SMS == message.getType())
        {
                txtContentSMSReceive.setText(message.getContent());
                txtContentSMSReceive.setVisibility(View.VISIBLE);
                txtContentSMSSend.setVisibility(View.GONE);
        }

//        //check content null
//        if(Help.TYPE_RECEIVE_SMS == message.getType() && U message.getContent()) {
//
//        }

        return convertView;
    }

    public ListSMSSendAdapter(Context mContext, int layoutItemID, ArrayList<MessageList> arrListMessage)
    {
        this.mContext = mContext;
        this.LayoutItemID = layoutItemID;
        this.arrListMessage = arrListMessage;
    }
}
