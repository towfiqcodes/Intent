
package com.backerror.rit.intent;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import static android.content.Intent.CATEGORY_BROWSABLE;

public class Action_Class extends AppCompatActivity  {
   Button callBtn,smsBtn,emailBtn,uriBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_action__class );
        callBtn=findViewById( R.id.call_Button );
        smsBtn=findViewById( R.id.sms_Button );
        emailBtn=findViewById( R.id.email_Button );
        uriBtn=findViewById( R.id.uriButton );
        ///callBtn.setText( getIntent().getStringExtra(Constant.PHONE_NUMBER_KEY ) );

        callBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);

                callIntent.setData(Uri.parse("tel:" + getIntent().getStringExtra(Constant.PHONE_NUMBER_KEY )));
                startActivity(callIntent);
            }
        } );
        smsBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent smsIntent = new Intent(Intent.ACTION_SENDTO);
                smsIntent.setData(Uri.parse(getIntent().getStringExtra( Constant.PHONE_NUMBER_KEY )));
                startActivity( smsIntent );

            }
        } );
        emailBtn.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("message/rfc822");
                i.putExtra(Intent.EXTRA_EMAIL  , new String[]{getIntent().getStringExtra( Constant.EMAIL_KEY )});
                i.putExtra(Intent.EXTRA_SUBJECT, "subject of email");
                i.putExtra(Intent.EXTRA_TEXT   , "body of email");
                try {
                    startActivity(Intent.createChooser(i, "Send mail..."));
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

    @Override
    public void onBackPressed() {

    }


}
