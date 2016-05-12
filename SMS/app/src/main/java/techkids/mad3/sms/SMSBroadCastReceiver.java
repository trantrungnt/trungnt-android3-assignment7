package techkids.mad3.sms;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ListView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by TrungNT on 4/29/2016.
 */
public class SMSBroadCastReceiver extends BroadcastReceiver {
    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM dd");
    private Date strCurrentDate;

    @Override
    public void onReceive(Context context, Intent intent) {
        strCurrentDate = new Date();

        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            Object[] sms = (Object[]) bundle.get("pdus");
            for (int i = 0; i < sms.length; i++) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String smsBody = smsMessage.getDisplayMessageBody();
                String address = smsMessage.getDisplayOriginatingAddress();
                String currentDate = simpleDateFormat.format(strCurrentDate);

                //Toast.makeText(context, smsBody + " - " + address, Toast.LENGTH_SHORT).show();

                //////////////////////////////////////////////////////////////////////////////////
                //luu so dien thoai va noi dung tin nhan vua nhan vao SMSMessageManager
                MessageList messageList = new MessageList();
                messageList.setPhone(address);
                messageList.setContent(smsBody);
                messageList.setType(1);

                SMSMessageManager.getOurInstance().getarrMessageList().add(messageList);
                //thong bao Notification cua ListView o day
                SMSMessageManager.getOurInstance().getListMessageAdapter().notifyDataSetChanged();
                //kiem tra du lieu content body sms co kieu send sms la null hoac empty khong?
                if ((messageList.getType()==Help.TYPE_SEND_SMS) && (!TextUtils.isEmpty(messageList.getContent())))
                                                    SMSMessageManager.getOurInstance().getListSMSSendAdapter().notifyDataSetChanged();
                Log.d("4444", String.valueOf(SMSMessageManager.getOurInstance().getarrMessageList().size()));
                ///////////////////////////////////////////////////////////////////////////////////
                //Hien thi Notification khi nhan duoc tin nhan SMS
                NotificationCompat.Builder mBuilder =
                        new NotificationCompat.Builder(context)
                                .setSmallIcon(R.drawable.message)
                                .setContentTitle(address)
                                .setContentText(smsBody);

                Intent resultIntent = new Intent(context, MainActivity.class);

                TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
                stackBuilder.addParentStack(MainActivity.class);
                stackBuilder.addNextIntent(resultIntent);
                PendingIntent resultPendingIntent =
                        stackBuilder.getPendingIntent(
                                0,
                                PendingIntent.FLAG_UPDATE_CURRENT
                        );
                mBuilder.setContentIntent(resultPendingIntent);
                NotificationManager mNotificationManager =
                        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                mNotificationManager.notify(999, mBuilder.build());
            }
        }
    }
}
