# trungnt-android3-assignment7
##Yêu cầu
+ Viết ứng dụng Gửi/Nhận tin nhắn 
![BTVN-2742016-Receive/SendSMS](http://i477.photobucket.com/albums/rr132/trungepu/BTVN-242016-Receive-SendSMS_zpsvnsoforl.jpg)

## Demo Code
+ [Demo Gửi/Nhận SMS và Custom Action Bar veron 1](https://youtu.be/taLripFeC-Y)

##Chú ý khi code
+ Định nghĩa class Message để cho duy nhất các class SendSMS và ReceiveSMS kế thừa và sử dụng thuộc tính phone
```
public abstract class Message {
    protected String phone;
}
```

+ Định nghĩa class SMSBody để chứa thuộc tính ContentSMS và Date (dùng cho các class SendSMS và ReceiveSMs sử dụng và lưu nó vào 1 mảng)
```
   private ArrayList<SMSBody> arrListSendSMS;
```

và mảng
```
   private ArrayList<SMSBody> arrListSMSBodyReceive;
```
2 mảng này dùng để hiển thị ra các SMS được nhận và các SMS được gửi. Tạo class SMSBody có tác dụng: khi cùng 1 số điện thoại của 1 người gửi đến, ta có thể gộp nội dung được gửi hoặc được nhận vào cùng 1 số đã gửi đến và hiển thị ra ngoài màn hình.

+ Ảnh 9-patch là ảnh được chia ra làm 9 phần. Ở đây, ta tạo ảnh nên có hình Reply Left và Reply Right có định dạng file answer1.9.png và answer2.9.png; trước tiên, ta vẽ 2 hình này và lưu kiểu dạng file png. Sau đó, ta xử lý Transparency cho ảnh và chỉnh 4 ảnh góc ảnh này giữ nguyên (xử lý 4 góc ảnh bằng trang web online-image-editor.com hoặc xử lý ảnh bằng FileEditor trong Android Studio - xem lại hướng dẫn vì chưa xử lý bằng Android Studio nên chưa rõ)

+ Tạo Floating Action Button: vào file build.gradle của Module:app và add thư viện 'com.melnykov:floatingactionbutton:1.3.0' vào và sync về sử dụng
```
dependencies {
    compile fileTree(dir: 'libs', include: ['*.jar'])
    testCompile 'junit:junit:4.12'
    compile 'com.android.support:appcompat-v7:23.3.0'
    compile 'com.melnykov:floatingactionbutton:1.3.0'
}
```
Trong giao diện activity_main.xml,  ta add nó vào giao diện
```
 <com.melnykov.fab.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_margin="16dp"
        android:layout_alignParentBottom="true"
        android:keepScreenOn="true"
        android:layout_alignParentRight="true"
        android:src="@drawable/plus"
        fab:fab_colorNormal="@color/colorAccent"
        fab:fab_colorPressed="@color/accent_pressed"
        fab:fab_colorRipple="@color/ripple" />
```
Và trong MainActivity.java, ta thêm vào đoạn code
```
 private void initDisplayListSMSMessage()
    {
        ListSMSMessageAdapter listSMSMessageAdapter = new ListSMSMessageAdapter((Context) this, R.layout.sms_message_template, SMSMessageManager.getOurInstance().getArrSMSMessage());
        listViewSMSMessage = (ListView) this.findViewById(R.id.lvDisplaySMSList);
        listViewSMSMessage.setAdapter(listSMSMessageAdapter);

        FloatingActionButton fab = (FloatingActionButton) this.findViewById(R.id.fab);
        fab.attachToListView(listViewSMSMessage);
        fab.setType(FloatingActionButton.TYPE_NORMAL);

    }
```

+ Kiểm tra phần tử cuối cùng của mảng ArrayList<SMSBody> arrListSMSBodyReceive có tồn tại không?  Nếu phần tử của mảng này không có thì mảng này null, còn nếu có phần tử cuối cùng trong mảng tồn tại thì size của mảng giảm đi 1. Mục đích của việc này để không bị tràn vị trí của mảng (nếu không kiểm tra như vậy, khi truy cập vào mảng này, khi mảng không tồn tại giá trị nào và ta lấy giá trị => chương trình sẽ bị lỗi)
```
//dinh nghia kiem tra phan tu cuoi cung co trong mang arrListSMSBodyReceive khong?
    public SMSBody getLastSMSBodyElement()
    {
        if (arrListSMSBodyReceive.size() == 0)
            return null;

        return arrListSMSBodyReceive.get(arrListSMSBodyReceive.size()-1);
    }
```

+ Để ListView lvDisplaySMSList nhận sự kiện onItemClick(), trong giao diện activity_main.xml, ta cho thêm khai báo: android:descendantFocusability="blocksDescendants" trong RelativeLayout
```
     android:descendantFocusability="blocksDescendants"
```
Và trong ListView lvDisplaySMSList, ta khai báo
```
<ListView
        android:id="@+id/lvDisplaySMSList"
        android:focusableInTouchMode="false"
        android:focusable="false"
        android:clickable="false"
        android:layout_width="match_parent"
        android:layout_height="match_parent">
    </ListView>
```
Trong file MainActivity.java, ta định nghĩa phương thức
```
//click vao 1 item tren listViewSMSMessage thi mo giao dien Send SMS
        listViewSMSMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ID selected: ", String.valueOf(position));
            }
        });
```

+ Đăng ký AnswerAcitivty trong Mainifest.xml để mở AnswerActivity
```
  <activity android:name="techkids.mad3.sms.AnswerActivity">
  </activity>
```
Và khi click vào 1 item trong Listview listViewSMSMessage thì gọi phương thức onItemClick() và mở AnswerActivity
```
 //click vao 1 item tren listViewSMSMessage thi mo giao dien Send SMS
        listViewSMSMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ID selected: ", String.valueOf(position));

                Intent intentDisplayAnswerActivity = new Intent(MainActivity.this, AnswerActivity.class);
                startActivity(intentDisplayAnswerActivity);
            }
        });
```

+ KHi click vào nút FloatingActionButton thì hiển thị ra Giao diện của AnswerActivity
```
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
```

+ Sửa giao diện thành phần ActionBar của Main Activity: 
Trong file style.xml, ta khai báo
```
<resources>

    <!-- Base application theme. -->
    <style name="AppTheme" parent="Theme.AppCompat.Light.NoActionBar">
        <!-- Customize your theme here. -->
    </style>
    <style name="CustomTheme" parent="Theme.AppCompat.Light">
        <item name="contentInsetStart">0dp</item>
        <item name="contentInsetEnd">0dp</item>
        <item name="colorPrimary">@color/colorFirstActionBar</item>
        <item name="colorPrimaryDark">@color/colorSecondActionBar</item>
        <item name="colorAccent">@color/colorThirdActionBar</item>
    </style>
</resources>
```
Trong file Mainifest.xml, ta khai báo sử dụng CustomTheme
```

 <application
        android:name=".SMSMessageManager"
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:supportsRtl="true"
        android:theme="@style/CustomTheme">
        ...
 </application>
```
Tạo giao diện custom_action_bar_layout.xml

Trong fiile MainActivity.java, ta viết code dưới trong phương thức void initDisplayListSMSMessage(), Phương thức này được gọi trong void onResume()
```
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setDisplayShowCustomEnabled(true);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar_layout);
        View view =getSupportActionBar().getCustomView();
```


##Môi trường phát triển
+ Mảy ảo AVD dùng Hệ điều hành Android api 21
+ Công cụ Android Studio verson 2.1

##Tham khảo
+ [Floating Action Buttons](https://guides.codepath.com/android/floating-action-buttons)
+ [Tham khảo Floating Action Button](https://github.com/makovkastar/FloatingActionButton)
+ [tham khảo dạng file ảnh 9 Patch Image](http://developer.android.com/intl/zh-tw/guide/topics/resources/drawable-resource.html)
+ [Edit nine patch image online](https://romannurik.github.io/AndroidAssetStudio/nine-patches.html)
+ [Trang Web xử lý Transparency Online](http://www.online-image-editor.com/)
+ [Trang Paint Online](http://www.queeky.com/app)
+ [Tham khảo Drawables](https://guides.codepath.com/android/Drawables)
+ [Draw Shapes in Android](https://androidresearch.wordpress.com/2012/03/24/drawing-shapes-in-android/)
+ [Tham khảo Send SMS trong Android](http://www.tutorialspoint.com/android/android_sending_sms.htm)
+ [How to send sms message in Android](http://www.mkyong.com/android/how-to-send-sms-message-in-android/)
+ [Masterial Icon](https://materialdesignicons.com/)
+ [Customizing the toolbar in android (Action Bar)](http://www.vogella.com/tutorials/AndroidActionBar/article.html)
+ [Custom Action Bar example Tutorial](http://www.journaldev.com/9952/android-custom-action-bar-example-tutorial)
