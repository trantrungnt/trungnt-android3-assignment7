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
    private ArrayList<ReceiveSMS> arrListReceiveSMS;
    private int LayoutItemID;
    private TextView txtPhone, txtContentSMSMessage;
    private Context mContext;
    private ReceiveSMS receiveSMS;
    private ImageView avatar;

    @Override
    public int getCount() {
        return arrListReceiveSMS.size();
    }

    @Override
    public ReceiveSMS getItem(int position) {
        return arrListReceiveSMS.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        receiveSMS = arrListReceiveSMS.get(position);

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

        txtPhone.setText(receiveSMS.getPhone());
        txtContentSMSMessage.setText(receiveSMS.getLastSMSBodyElement().getContentSMS().toString());

        return convertView;
    }

    public ListSMSMessageAdapter(Context mContext, int layoutItemID, ArrayList<ReceiveSMS> arrListReceiveSMS)
    {
        this.mContext = mContext;
        this.LayoutItemID = layoutItemID;
        this.arrListReceiveSMS = arrListReceiveSMS;
    }
}
