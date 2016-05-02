# trungnt-android3-assignment7
##Yêu cầu
+ Viết ứng dụng Gửi/Nhận tin nhắn 
![BTVN-2742016-Receive/SendSMS](http://i477.photobucket.com/albums/rr132/trungepu/BTVN-242016-Receive-SendSMS_zpsvnsoforl.jpg)

##Chú ý khi code
+ Định nghĩa class Message để cho duy nhất các class SendSMS và ReceiveSMS kế thừa và sử dụng thuộc tính phone
```
public abstract class Message {
    protected String phone;
}
```

+ Định nghĩa class SMSBody để chứa thuộc tính ContentSMS và Date (dùng cho các class SendSMS và ReceiveSMs sử dụng và lưu nó vào 1 mảng)
```
private AbstractList<SMSBody> arrListSendSMS;
```

và mảng
```
private AbstractList<SMSBody> arrListReceiveSMS;
```
2 mảng này dùng để hiển thị ra các SMS được nhận và các SMS được gửi. Tạo class SMSBody có tác dụng: khi cùng 1 số điện thoại của 1 người gửi đến, ta có thể gộp nội dung được gửi hoặc được nhận vào cùng 1 số đã gửi đến và hiển thị ra ngoài màn hình.


##Môi trường phát triển
+ Mảy ảo AVD dùng Hệ điều hành Android api 21

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
