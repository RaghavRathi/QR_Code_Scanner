package com.apkglobal.prototype;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import org.w3c.dom.Text;

public class FirstActivity extends Fragment {
    TextView tv_qrcode_result;
    Button btn_scan,btn_last;


    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_first, container, false);
        tv_qrcode_result = (TextView)view.findViewById(R.id.tv_qrcode_result);
        btn_scan = (Button)view.findViewById(R.id.btn_scan);
        btn_last = (Button)view.findViewById(R.id.btn_last);
        btn_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),SecondActivity.class);
                startActivityForResult(intent,0);
            }
        });
        btn_last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),LastActivity.class);
                startActivity(intent);
            }
        });
        return view;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==0){
            if(resultCode== CommonStatusCodes.SUCCESS){
                if(data!=null){
                    Barcode bar = data.getParcelableExtra("barcode");
                    tv_qrcode_result.setText("Barcode Value : "+bar.displayValue);
                } else{
                    tv_qrcode_result.setText("No QR code found");

                }
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }

    }

}
