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
//public class ListSMSSendAdapter extends BaseAdapter {
//    private ArrayList<SendSMS> arrListSendSMS;
//    private ArrayList<ReceiveSMS> arrListReceiveSMS;
//    private int LayoutItemID;
//    private TextView txtContentSMSReceive, txtContentSMSSend;
//    private Context mContext;
//    private SendSMS sendSMS;
//    private ReceiveSMS receiveSMS;
//    private ImageView avatarReceive, avatarSend;
//
//    @Override
//    public int getCount() {
//        return 0;
//    }
//
//    @Override
//    public SendSMS getItem(int position) {
//        return arrListSendSMS.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        sendSMS = arrListSendSMS.get(position);
//        //receiveSMS = arrListReceiveSMS.get(position);
//
//        if (convertView == null) {
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.answer_template, parent, false);
//        }
//
////        avatarReceive = (ImageView) convertView.findViewById(R.id.imgAvatarReceive);
////        avatarSend = (ImageView) convertView.findViewById(R.id.imgAvatarSend);
//        //txtContentSMSReceive = (TextView) convertView.findViewById(R.id.tvAnswerLeft);
//        txtContentSMSSend = (TextView) convertView.findViewById(R.id.tvAnswerRight);
//
//       // if (TextUtils.isEmpty(sendSMS.getLastElementSMSBody().getContentSMS())) {
//            txtContentSMSSend.setText(sendSMS.getLastElementSMSBody().getContentSMS().toString());
//            //txtContentSMSSend.setVisibility(View.VISIBLE);
//            //txtContentSMSReceive.setVisibility(View.GONE);
//        //}
////
////        if (TextUtils.isEmpty(receiveSMS.getLastSMSBodyElement().getContentSMS())) {
////            txtContentSMSReceive.setText(receiveSMS.getLastSMSBodyElement().getContentSMS().toString());
////            txtContentSMSReceive.setVisibility(View.INVISIBLE);
////            txtContentSMSSend.setVisibility(View.GONE);
////        }
//
//        //if (!TextUtils.isEmpty(sendSMS.getLastElementSMSBody().getContentSMS()) && !TextUtils.isEmpty(receiveSMS.getLastSMSBodyElement().getContentSMS()) )
//        //{
////            txtContentSMSSend.setText(sendSMS.getLastElementSMSBody().getContentSMS().toString());
////            txtContentSMSReceive.setText(receiveSMS.getLastSMSBodyElement().getContentSMS().toString());
////            txtContentSMSReceive.setVisibility(View.VISIBLE);
////            txtContentSMSSend.setVisibility(View.VISIBLE);
//        //}
//
////        txtContentSMSSend.setText("Xin chao");
////        txtContentSMSReceive.setText("uh, xin chao");
////        System.out.println(txtContentSMSSend.getText().toString());
//        //txtContentSMSReceive.setVisibility(View.VISIBLE);
//        //txtContentSMSSend.setVisibility(View.VISIBLE);
//        return convertView;
//    }
//
//    public ListSMSSendAdapter(Context mContext, int layoutItemID, ArrayList<ReceiveSMS> arrListReceiveSMS, ArrayList<SendSMS> arrListSendSMS)
//    {
//        this.mContext = mContext;
//        this.LayoutItemID = layoutItemID;
//        this.arrListReceiveSMS = arrListReceiveSMS;
//        this.arrListSendSMS = arrListSendSMS;
//    }
//}
