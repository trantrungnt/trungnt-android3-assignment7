package techkids.mad3.sms;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

/**
 * Created by TrungNT on 5/3/2016.
 */
public class AnswerActivity extends AppCompatActivity implements View.OnClickListener {
    private ListView lvDisplayAnswer;
    private Button btnSend;
    private EditText editTextContentSendSMS;

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

    private void initDisplayAnswer()
    {
        lvDisplayAnswer = (ListView) this.findViewById(R.id.lvDisplayAnswer);
        editTextContentSendSMS = (EditText) this.findViewById(R.id.editTextContentSendSMS);
        btnSend = (Button) this.findViewById(R.id.btnSend);
        btnSend.setOnClickListener(this);

        /////////////////////////////////////////////////////////////////////////////////////////
        ListSMSSendAdapter listSMSSendAdapter = new ListSMSSendAdapter((Context)this, R.layout.answer_template, SMSMessageManager.getOurInstance().getArrSMSMessage(), SMSMessageManager.getOurInstance().getArrayListSMSSend());
        lvDisplayAnswer.setAdapter(listSMSSendAdapter);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btnSend)
        {

        }
    }
}
