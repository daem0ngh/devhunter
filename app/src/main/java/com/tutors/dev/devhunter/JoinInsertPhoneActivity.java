package com.tutors.dev.devhunter;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tutors.dev.devhunter.data.UserApi;
import com.tutors.dev.devhunter.util.ExLog;
import com.tutors.dev.devhunter.util.StringUtil;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by shs on 2016-01-09.
 */
public class JoinInsertPhoneActivity extends ParentActivity implements View.OnClickListener {

    private EditText edtName, edtPhoneNumber;
    private Button btnOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join_phone_number);

        btnOk = (Button)findViewById(R.id.btnOk);
        btnOk.setOnClickListener(this);
        edtName = (EditText)findViewById(R.id.edtName);
        edtPhoneNumber = (EditText)findViewById(R.id.edtPhoneNumber);

    }

    public void http_API_Request(int whichApi)
    {
        if(UserApi.REQUEST_AUTH_NUMBER == whichApi) {

            String phoneNumber = edtPhoneNumber.getText().toString();
            MyApp.mApiService.user_phone_check(phoneNumber, new Callback<Response>() {
                @Override
                public void success(Response response, Response response2) {
                    ExLog.i(TAG, "response=" + response2.getStatus());
                }

                @Override
                public void failure(RetrofitError error) {
                    ExLog.i(TAG,error.getMessage());
                }
            });
        }
    }

    public boolean validateValues()
    {
        String strMsg = "";
        if(edtPhoneNumber.getText().toString().trim().length() < 10) {
            strMsg = "전화번호가 올바르지 않습니다. 다시 입력해 주세요";
        } else if(edtName.getText().toString().trim().length() < 2) {
            strMsg = "이름이 올바르지 않습니다. 다시 입력해 주세요";
        }
        if(StringUtil.isNotNull(strMsg))
            return false;

        return true;
    }

    @Override
    public void onClick(View v) {
        if(btnOk == v) {

            if (!validateValues())
                return;

            http_API_Request(UserApi.REQUEST_AUTH_NUMBER);
        }
    }
}
