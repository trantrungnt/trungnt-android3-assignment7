package techkids.mad3.sms;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;

import com.melnykov.fab.FloatingActionButton;
import com.melnykov.fab.ScrollDirectionListener;

public class MainActivity extends AppCompatActivity {
    private ListView listViewSMSMessage;

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
        ListSMSMessageAdapter listSMSMessageAdapter = new ListSMSMessageAdapter((Context) this, R.layout.sms_message_template, SMSMessageManager.getOurInstance().getArrSMSMessage());
        listViewSMSMessage = (ListView) this.findViewById(R.id.lvDisplaySMSList);
        listViewSMSMessage.setAdapter(listSMSMessageAdapter);

        FloatingActionButton fab = (FloatingActionButton) this.findViewById(R.id.fab);
        fab.attachToListView(listViewSMSMessage);
        fab.setType(FloatingActionButton.TYPE_NORMAL);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentOpenAnswerActivity = new Intent(MainActivity.this, AnswerActivity.class);
                startActivity(intentOpenAnswerActivity);
            }
        });


        //click vao 1 item tren listViewSMSMessage thi mo giao dien Send SMS
        listViewSMSMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ID selected: ", String.valueOf(position));

                Intent intentDisplayAnswerActivity = new Intent(MainActivity.this, AnswerActivity.class);
                startActivity(intentDisplayAnswerActivity);
            }
        });
    }

}
