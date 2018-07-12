package com.example.galax.study_20180703_chapter3_7;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SmsReceiver extends BroadcastReceiver {

    public SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private  static  final String TAG ="SmsReceiver";

    @Override
    public void onReceive(Context context, Intent intent) {  //중요한 메소드 -- call back method
        Log.d(TAG, "onReceiver() 가 호출됨");

        Bundle bundle = intent.getExtras();
        SmsMessage[] messages = parseSmsMessage(bundle);

        if(messages != null && messages.length>0){
            String sender = messages[0].getOriginatingAddress();
            String content = messages[0].getMessageBody().toString();
            Date receivedDate = new Date(messages[0].getTimestampMillis());

            Intent mainAct = new Intent(context, MainActivity.class);
            mainAct.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_SINGLE_TOP|Intent.FLAG_ACTIVITY_CLEAR_TOP);

            mainAct.putExtra("sender", sender);
            mainAct.putExtra("contents", content);
            mainAct.putExtra("receivedDate", format.format(receivedDate));
            mainAct.putExtra("check", 1);

            context.startActivity(mainAct);

        }
    }

    private SmsMessage[] parseSmsMessage(Bundle bundle) {
        Object[] objs = (Object[]) bundle.get("pdus");
        SmsMessage[] messages = new SmsMessage[objs.length];

        int smsCount = objs.length;
        for(int i=0; i< smsCount; i++){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                String format = bundle.getString("format");
                messages[i] = SmsMessage.createFromPdu((byte[])objs[i], format);
            }else{
                messages[i] = SmsMessage.createFromPdu((byte[])objs[i]);
            }
        }
        return messages;

    }
}
