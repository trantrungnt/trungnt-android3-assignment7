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
import android.util.Log;
import android.widget.ListView;

import java.text.SimpleDateFormat;
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
                ReceiveSMS receiveSMS = new ReceiveSMS(address, smsBody, currentDate);
                SMSMessageManager.getOurInstance().getArrSMSMessage().add(receiveSMS);
                //thong bao Notification cua ListView o day
                SMSMessageManager.getOurInstance().getListMessageAdapter().notifyDataSetChanged();
                Log.d("4444", String.valueOf(SMSMessageManager.getOurInstance().getArrSMSMessage().size()));
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
