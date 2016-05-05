package techkids.mad3.sms;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private ListSMSMessageAdapter listSMSMessageAdapter;
    private EditText editPhoneActionBar;
    private String phoneSendTo;
    private Bundle bundlePhoneSendTo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        initDisplayListSMSMessage();
    }

    private void initDisplayListSMSMessage()
    {
        listSMSMessageAdapter = new ListSMSMessageAdapter((Context) this, R.layout.sms_message_template, SMSMessageManager.getOurInstance().getArrSMSMessage());
        SMSMessageManager.getOurInstance().setListMessageAdapter(listSMSMessageAdapter);
        SMSMessageManager.getOurInstance().setListViewSMSMessage((ListView) this.findViewById(R.id.lvDisplaySMSList));
        SMSMessageManager.getOurInstance().getlistViewSMSMessage().setAdapter(SMSMessageManager.getOurInstance().getListMessageAdapter());

        //click vao 1 item tren listViewSMSMessage thi mo giao dien Send SMS
        SMSMessageManager.getOurInstance().getlistViewSMSMessage().setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ID selected: ", String.valueOf(position));

                Intent intentDisplayAnswerActivity = new Intent(MainActivity.this, AnswerActivity.class);
                startActivity(intentDisplayAnswerActivity);
            }
        });

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view =getSupportActionBar().getCustomView();

        editPhoneActionBar = (EditText) view.findViewById(R.id.editTextPhoneActionBar);
        phoneSendTo = editPhoneActionBar.getText().toString();

        FloatingActionButton fab = (FloatingActionButton) this.findViewById(R.id.fab);
        fab.attachToListView(SMSMessageManager.getOurInstance().getlistViewSMSMessage());
        fab.setType(FloatingActionButton.TYPE_NORMAL);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //lay du lieu tu editTextPhoneActionBar va dung bundle dong goi du lieu, sau do intent day goi du lieu nay sang AnswerActivity
                bundlePhoneSendTo = new Bundle();
                bundlePhoneSendTo.putString("IDPhone", phoneSendTo);

                Intent intentOpenAnswerActivity = new Intent(MainActivity.this, AnswerActivity.class);
                intentOpenAnswerActivity.putExtras(bundlePhoneSendTo);
                startActivity(intentOpenAnswerActivity);
            }
        });


        //////////////////////////////////////////////////////////////////////////////////////////
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.parseColor("#F57F17"));
    }


}
