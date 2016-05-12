package techkids.mad3.sms;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by TrungNT on 5/3/2016.
 */
public class AnswerActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lvDisplayAnswer;
    private Button btnSend, btnBackActionBar;
    private TextView tvPhone, tvPhoneActionBar;
    private EditText editTextContentSendSMS;
    private Bundle bundleReceive;
    private String displayPhoneActionBar;
    private static final SimpleDateFormat sendDateFormat = new SimpleDateFormat("MMM dd");
    private Date strSendCurrentDate;
    private MessageList messageList = new MessageList();

    //private ListSMSSendAdapter listSMSSendAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        initDisplayAnswer();
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        initDisplayActionBarAnswer();
    }

    private void initDisplayAnswer()
    {
        lvDisplayAnswer = (ListView) this.findViewById(R.id.lvDisplayAnswer);
        editTextContentSendSMS = (EditText) this.findViewById(R.id.editTextContentSendSMS);
        tvPhone = (TextView) this.findViewById(R.id.tvPhone);
        btnSend = (Button) this.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);

        connectDisplayListViewSend();
        SMSMessageManager.getOurInstance().getListSMSSendAdapter().notifyDataSetChanged();
    }

    //Phuong thuc hien thi cac thanh phan trong ActionBar Answer
    private void initDisplayActionBarAnswer()
    {
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_answer);
        View view =getSupportActionBar().getCustomView();

        //lay so dien thoai vua nhan duoc de gui tin nhan
        tvPhoneActionBar = (TextView) view.findViewById(R.id.tvPhoneActionBar);
        displayPhoneActionBar = getIntent().getStringExtra("IDPhone").toString();
        tvPhoneActionBar.setText(displayPhoneActionBar);

        //tim thanh phan Button btnBackActionBar
        btnBackActionBar = (Button) view.findViewById(R.id.btnBackActionBar);
        btnBackActionBar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        //khi nhan nut btnSend
        if (id == R.id.btnSend)
        {
            //Goi phuong thuc gui SMS
            sendSMSMessage();
        }

        //khi nhan nut btnBackActionBar trong Action Bar Answer
        if (id == R.id.btnBackActionBar)
                            AnswerActivity.this.finish();
    }

    //Phuong thuc gui SMS
    private void sendSMSMessage()
    {
        String phone = tvPhoneActionBar.getText().toString();
        String bodySMS = editTextContentSendSMS.getText().toString();

        strSendCurrentDate = new Date();
        String currentSendDate = sendDateFormat.format(strSendCurrentDate);

        try {
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone, null, bodySMS, null, null);
            Toast.makeText(getApplicationContext(), "SMS Sent!",
                    Toast.LENGTH_LONG).show();

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(),
                    "SMS sends fail, please try again later!",
                    Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }

        messageList.setPhone(phone);
        messageList.setType(0);
        messageList.setContent(bodySMS);

        SMSMessageManager.getOurInstance().getarrMessageList().add(messageList);
        connectDisplayListViewSend();
        SMSMessageManager.getOurInstance().getListSMSSendAdapter().notifyDataSetChanged();
    }

    //ket noi ListViewSend va hien thi du lieu tren ListView
    private void connectDisplayListViewSend()
    {
        ListSMSSendAdapter listSMSSendAdapter = new ListSMSSendAdapter((Context)this, R.layout.answer_template,
                SMSMessageManager.getOurInstance().getarrMessageList()
        );

        SMSMessageManager.getOurInstance().setListViewSMSSend(lvDisplayAnswer);
        SMSMessageManager.getOurInstance().setListSMSSendAdapter(listSMSSendAdapter);
        SMSMessageManager.getOurInstance().getListViewSMSSend().setAdapter(SMSMessageManager.getOurInstance().getListSMSSendAdapter());

    }
}
