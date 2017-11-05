package com.apkglobal.prototype;

import android.app.ProgressDialog;
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
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends Fragment {
    EditText et_name, et_mobile;
    Button btn_login;
    String name, mobile;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_login, container, false);
        et_name = (EditText) view.findViewById(R.id.et_name);
        et_mobile = (EditText) view.findViewById(R.id.et_mobile);
        btn_login = (Button) view.findViewById(R.id.btn_login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog progressDialog = new ProgressDialog(getActivity());
                name = et_name.getText().toString();
                mobile = et_mobile.getText().toString();
                if (name.equals("")) {
                    et_name.setError("can't be blank");
                } else if (mobile.equals("")) {
                    et_mobile.setError("can't be blank");
                } else if (!name.matches("[A-Za-z]+")) {
                    et_name.setError("only alphabets allowed");
                } else if (!mobile.matches("[0-9]+") && mobile.length() == 10) {
                    et_mobile.setError("10 digit number only");
                } else {

                    progressDialog.setMessage("Registering User .....");
                    progressDialog.show();

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://www.ikai.co.in/api/login.php",
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Intent i = new Intent(getActivity(), AfterLogin.class);
                                    startActivity(i);
                                    Toast.makeText(getActivity(), "Registration Successfull", Toast.LENGTH_SHORT).show();
                                    progressDialog.dismiss();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            progressDialog.hide();
                            Toast.makeText(getActivity(), "Error : " + error, Toast.LENGTH_LONG).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("name", name);
                            params.put("mobile", mobile);
                            return params;
                        }

                    };


                }

            }
        });

        return view;
    }
}


