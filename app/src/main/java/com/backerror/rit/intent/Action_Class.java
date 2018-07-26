package com.backerror.rit.intent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;
import android.widget.Toast;

import static android.content.Intent.CATEGORY_BROWSABLE;

public class Action_Class extends AppCompatActivity {
    private static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 1;
    Button callBtn, smsBtn, emailBtn, uriBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_action__class );
        callBtn = findViewById( R.id.call_Button );
        smsBtn = findViewById( R.id.sms_Button );
        emailBtn = findViewById( R.id.email_Button );
        uriBtn = findViewById( R.id.uriButton );
        ///callBtn.setText( getIntent().getStringExtra(Constant.PHONE_NUMBER_KEY ) );

        callBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                if (ActivityCompat.checkSelfPermission(Action_Class.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Action_Class.this,
                                                      new String[]{Manifest.permission.CALL_PHONE},
                                                      MY_PERMISSIONS_REQUEST_CALL_PHONE);


                }else{
                    startActivity(getCall());
                }

            }
        } );









        smsBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent=new Intent( Action_Class.this,Sms.class );
               startActivity( intent );
            }
        } );

        emailBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL  , new String[]{getIntent().getStringExtra( Constant.EMAIL_KEY )});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                emailIntent.putExtra(Intent.EXTRA_TEXT   , "body of email");
                try {
                    startActivity(Intent.createChooser(emailIntent  , "Send mail..."));
                } catch (android.content.ActivityNotFoundException ex) {
                    Toast.makeText( Action_Class.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                }

            }
        } );
        uriBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent uriIntent = new Intent(Intent.ACTION_VIEW);
                uriIntent.setData(Uri.parse(getIntent().getStringExtra( Constant.EMAIL_KEY )));
                startActivity(uriIntent);*/
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_VIEW);
                intent.addCategory( CATEGORY_BROWSABLE);
                intent.setData(Uri.parse("http://www.github.com/towfiqulislam"));
                startActivity(intent);

            }
        } );


    }
    public  Intent getCall(){
        return  new Intent( Intent.ACTION_CALL ,Uri.parse("tel:" + getIntent().getStringExtra(Constant.PHONE_NUMBER_KEY )));

        }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult( requestCode, permissions, grantResults );
        switch(requestCode) {
            case MY_PERMISSIONS_REQUEST_CALL_PHONE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivity(getCall());

                } else {
                    Toast.makeText( Action_Class.this, "Access denied",Toast.LENGTH_LONG ).show();
                }

            }
        }
    }

    @Override
    public void onBackPressed() {

    }


}
