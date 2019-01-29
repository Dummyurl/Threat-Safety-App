package com.example.root.readpermissions.activities;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.readpermissions.R;
import com.example.root.readpermissions.enums.ButtonEvent;
import com.example.root.readpermissions.enums.Decision;
import com.example.root.readpermissions.util.PermissionConstants;

public class ViewApp extends AppCompatActivity {

    TextView permissions;
    Button action;
    private PackageManager packageManager = null;
    RatingBar ratingBarSecurity,ratingBarThreat,ratingBarPower;
    TextView securityStrength,securityWeakness,powerConsuming,decision;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_app);
        String appPermissions= getIntent().getExtras().getString("permissions");
        final String packageName= getIntent().getExtras().getString("packageName");
        String appName= getIntent().getExtras().getString("appName");

        packageManager = getPackageManager();

        //getActionBar().setTitle(appName);

        permissions=(TextView) findViewById(R.id.permissions);
        action=(Button) findViewById(R.id.action);
        ratingBarSecurity=(RatingBar) findViewById(R.id.ratingbarSecurity);
        ratingBarThreat=(RatingBar) findViewById(R.id.ratingbarThreat);
        ratingBarPower=(RatingBar) findViewById(R.id.ratingbarPower);

        securityStrength=(TextView) findViewById(R.id.securityPercentage);
        securityWeakness=(TextView) findViewById(R.id.securityThreat);
        powerConsuming=(TextView) findViewById(R.id.powerConsuming);
        decision=(TextView) findViewById(R.id.decision);


        permissions.setText(appName +" has \n"+appPermissions);

   //security

        int a=0,b=0,c=0;

        for(String pmsn: PermissionConstants.threatPermissions){
            if(permissions.getText().toString().contains(pmsn)){
                a++;
            }
        }

        for(String pmsn: PermissionConstants.securityStrength){
            if(permissions.getText().toString().contains(pmsn)){
                b++;
            }
        }

        for(String pmsn: PermissionConstants.powerConsuming){
            if(permissions.getText().toString().contains(pmsn)){
                c++;
            }
        }

        int aa=a*10;
        int bb=b*20;
        int cc=c*20;


        ratingBarThreat.setRating(a/2);
        ratingBarSecurity.setRating(b);
        ratingBarPower.setRating(c);

        securityWeakness.setText(String.valueOf(aa).concat("%"));
        securityStrength.setText(String.valueOf(bb).concat("%"));
        powerConsuming.setText(String.valueOf(cc).concat("%"));


        if(aa>bb){
            action.setText(ButtonEvent.Unistall.toString());
            decision.setText(Decision.Unsafe.toString());
        }
        else{
            action.setText(ButtonEvent.Launch.toString());
            decision.setText(Decision.Safe.toString());
        }




        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(action.getText().toString().equals(ButtonEvent.Launch.toString())){
                    Intent intent = packageManager
                            .getLaunchIntentForPackage(packageName);

                    if (null != intent) {
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Can not launch application, please contact admin",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Uri packageUri=Uri.parse("package:"+packageName);
                    Intent uninstallIntent=new Intent(Intent.ACTION_DELETE,packageUri);
                    if(null!=uninstallIntent){
                        startActivity(uninstallIntent);
                    }
                    else{
                        Toast.makeText(getApplicationContext(),"The application can not be uninstalled, it required root privileges",Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }
}
