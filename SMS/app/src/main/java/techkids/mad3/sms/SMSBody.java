package techkids.mad3.sms;

/**
 * Created by TrungNT on 5/3/2016.
 */
public class SMSBody {
    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    private String ContentSMS;

    public String getContentSMS() {
        return ContentSMS;
    }

    public void setContentSMS(String contentSMS) {
        ContentSMS = contentSMS;
    }

    private String Date;

    private SMSBody()
    {
        this.ContentSMS = "";
        this.Date ="";
    }

    public SMSBody(String ContentSMS, String Date)
    {
        this.ContentSMS = ContentSMS;
        this.Date = Date;
    }
}
