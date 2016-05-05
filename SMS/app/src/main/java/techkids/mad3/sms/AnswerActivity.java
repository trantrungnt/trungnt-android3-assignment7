package techkids.mad3.sms;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by TrungNT on 5/3/2016.
 */
public class AnswerActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lvDisplayAnswer;
    private Button btnSend;
    private TextView tvPhone, tvPhoneActionBar;
    private EditText editTextContentSendSMS;
    private Bundle bundleReceive;
    private String displayPhoneActionBar;

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

        /////////////////////////////////////////////////////////////////////////////////////////
        ListSMSSendAdapter listSMSSendAdapter = new ListSMSSendAdapter((Context)this, R.layout.answer_template, SMSMessageManager.getOurInstance().getArrSMSMessage(), SMSMessageManager.getOurInstance().getArrayListSMSSend());
        lvDisplayAnswer.setAdapter(listSMSSendAdapter);
    }

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
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String phone = tvPhoneActionBar.getText().toString();
        String bodySMS = editTextContentSendSMS.getText().toString();

        if (id == R.id.btnSend)
        {
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
        }
    }
}
