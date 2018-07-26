package com.backerror.rit.intent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Sms extends AppCompatActivity {
    public static  final int MY_PERMISSIONS_REQUEST_SEND_SMS=1;
    private EditText smsWriteET;
    private Button sendBTN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.sms_fragment );

        smsWriteET=findViewById( R.id.smsWriteText );
        sendBTN=findViewById( R.id.sendBtn );
         String sms=smsWriteET.getText().toString();

        sendBTN.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ActivityCompat.checkSelfPermission(Sms.this, Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Sms.this,
                                                      new String[]{Manifest.permission.SEND_SMS},
                                                      MY_PERMISSIONS_REQUEST_SEND_SMS);
                }else{
                    startActivity(sendSMSMessage());
                }

            }
        } );

    }

    public Intent sendSMSMessage() {
        String sms=smsWriteET.getText().toString();
        Uri uri = Uri.parse(sms);
        Intent intent=  new Intent( Intent.ACTION_SENDTO);
        intent.setData(  Uri.parse("tel:" + getIntent().getStringExtra(Constant.PHONE_NUMBER_KEY )));
        intent.setData( uri );

        /*if (ContextCompat.checkSelfPermission( this,
                                               Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale( this,
                                                                     Manifest.permission.SEND_SMS)) {
            } else {
                ActivityCompat.requestPermissions(this,
                                                  new String[]{Manifest.permission.SEND_SMS},
                                                  MY_PERMISSIONS_REQUEST_SEND_SMS);
            }
        }*/
  return intent;

    }
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        String sms=smsWriteET.getText().toString();


        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_SEND_SMS: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(getIntent().getStringExtra( Constant.PHONE_NUMBER_KEY ),null,sms , null, null);
                    Toast.makeText( getApplicationContext(), "SMS sent.",
                                    Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "SMS faild, please try again.", Toast.LENGTH_LONG).show();

                }
            }
        }

    }

}
