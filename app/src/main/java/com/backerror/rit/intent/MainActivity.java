package com.backerror.rit.intent;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button submitBTN;
    EditText phoneNumberET,emailET,uriET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );


        phoneNumberET=findViewById( R.id.phoneNumberEditText );
        emailET=findViewById( R.id.emailEditText );
        uriET=findViewById( R.id.urlEditText );
        submitBTN=findViewById( R.id.submit_BTN );
        submitBTN.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber=phoneNumberET.getText().toString();
                String email=emailET.getText().toString();
                String uri=uriET.getText().toString();
                Intent intent = new Intent(getApplicationContext(), Action_Class.class);
                intent.putExtra(Constant.PHONE_NUMBER_KEY, phoneNumber);
                intent.putExtra(Constant.EMAIL_KEY, email);
                intent.putExtra(Constant.URI_KEY, uri);
                startActivity(intent);

            }
        } );
    }
    /*public void getCall(){

    }*/
}
