//package com.tutors.dev.devhunter;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.InputFilter;
//import android.text.TextWatcher;
//import android.view.View;
//import android.view.inputmethod.InputMethodManager;
//import android.widget.EditText;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//
//import com.tutors.dev.devhunter.network.user.UserModel;
//
//import java.util.ArrayList;
//import java.util.Locale;
//
//import retrofit.RetrofitError;
//import retrofit.client.Response;
//
///**
// * Created by shs on 2015-06-16.
// */
//public class JoinActivity extends ParentActivity implements View.OnClickListener
//{
//    private UserModel mJoinItem;
//
//    private ImageView btnBack, btnNext, img_ok, btnCountryCode;
//    private TextView lblNext, lblCountryCode;
//    private EditText edtName, edtPhoneNumber;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState)
//    {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_join_first_phone_name);
//
//        //-----------------------------------------------------------------------------
//        //	Titlebar 설정
//        //-----------------------------------------------------------------------------
//        btnBack = (ImageView)findViewById(R.id.btnBack);
//        btnBack.setOnClickListener(this);
//        btnNext = (ImageView)findViewById(R.id.btnNext);
//        btnNext.setOnClickListener(this);
//        lblNext = (TextView)findViewById(R.id.lblNext);
//        lblNext.setOnClickListener(this);
//        img_ok = (ImageView)findViewById(R.id.img_ok);
//        img_ok.setVisibility(View.GONE);
//
//        //-----------------------------------------------------------------------------
//        //	UI 설정
//        //-----------------------------------------------------------------------------
//        btnCountryCode = (ImageView)findViewById(R.id.btnCountryCode);
//        btnCountryCode.setOnClickListener(this);
//        lblCountryCode = (TextView)findViewById(R.id.lblCountryCode);
//        lblCountryCode.setOnClickListener(this);
//        edtPhoneNumber = (EditText)findViewById(R.id.edtPhoneNumber);
//        edtPhoneNumber.setFilters(new InputFilter[]{new InputFilter.LengthFilter(20)});
//        edtPhoneNumber.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {}
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                updateUI_next();
//            }
//        });
//
//
//        edtName = (EditText)findViewById(R.id.edtName);
//        edtName.setFilters(new InputFilter[]{new InputFilter.LengthFilter(50)});
//        edtName.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {}
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                img_ok.setVisibility(edtName.getText().length() > 1 ? View.VISIBLE : View.GONE);
//                updateUI_next();
//            }
//        });
//
//        if(savedInstanceState == null)
//        {
//            mDialCodeList = new ArrayList<DialcodeModel>();
//            mJoinItem = new UserModel();
//            http_API_Request(TaskManager.API_DIALCODE_LIST);
//        }
//        else
//        {
//            mDialCodeList = (ArrayList<DialcodeModel>) CommonClass.fromArrayJson(new TypeToken<ArrayList<DialcodeModel>>(){}.getType(), savedInstanceState.getString("mDialCodeList"));
//        }
//        lblCountryCode.setText(R.string.select);
//        lblCountryCode.setTag(null);
//    }
//
//
//    public void updateUI_next()
//    {
//        if(!validateValues(false)) {
//            btnNext.setImageResource(R.drawable.btn_next_off);
//            lblNext.setTextColor(getResources().getColor(R.color.joinDisableColor));
//            lblNext.setEnabled(false);
//            return;
//        }
//
//        btnNext.setImageResource(R.drawable.btn_next);
//        lblNext.setTextColor(getResources().getColor(R.color.joinTextColor));
//        lblNext.setEnabled(true);
//    }
//
//
//    public void updateUI_Phone()
//    {
//        if(StringUtil.isNotNull(DeviceUtil.mPhoneNumber))
//            edtPhoneNumber.setText(DeviceUtil.mPhoneNumber);
//
//        String code = DeviceUtil.getMyCountryCode();
//        if(StringUtil.isNotNull(code))
//        {
//            for(int i=0; i<mDialCodeList.size(); i++)
//            {
//                if(mDialCodeList.get(i).code.equalsIgnoreCase(code))
//                {
//                    lblCountryCode.setText(mDialCodeList.get(i).toString());
//                    lblCountryCode.setTag(mDialCodeList.get(i).code);
//                }
//            }
//        }
//
//        //초대정보로 회원가입할 경우 전화번호 / 이름 자동갱신
//        String inviteItem = getIntent().getStringExtra("inviteInfo");
//        if(StringUtil.isNotNull(inviteItem))
//        {
//            InviteModel invite = (InviteModel) InviteModel.fromJSon(InviteModel.class, inviteItem);
//            if(invite != null) {
//                edtPhoneNumber.setText(invite.phone.replace("-", ""));
//                edtName.setText(InfoManager.getMyName());
//            }
//        }
//
//    }
//
//    public void http_API_Request(int whichApi)
//    {
//        query_popup(whichApi);
//        if(whichApi == TaskManager.API_DIALCODE_LIST)
//        {
//            MyApp.mApiService.join_countrycode(new CustomCallback<DialcodeList>(this) {
//                @Override
//                public boolean onSuccess(DialcodeList dialcodeList, Response response) {
//
//                    if (dialcodeList == null || dialcodeList.results == null || dialcodeList.results.size() < 1) {
//                        PopupManager.showDialog(JoinActivity.this, -1, R.string.no_search_results);
//                        return false;
//                    }
//                    mDialCodeList.addAll(dialcodeList.results);
//                    updateUI_Phone();
//                    updateUI_next();
//                    if (mProgress != null)
//                        PopupManager.closeProgress(mProgress);
//                    return false;
//                }
//
//                @Override
//                public boolean onFailure(RetrofitError error) {
//                    if (mProgress != null)
//                        PopupManager.closeProgress(mProgress);
//                    return false;
//                }
//            });
//        }
//
//    }
//
//    @Override
//    protected void onDestroy()
//    {
//        PopupManager.cancelProgress(mProgress);
//        super.onDestroy();
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState)
//    {
//        outState.putString("mDialCodeList", CommonClass.toArrayJson(mDialCodeList));
//        super.onSaveInstanceState(outState);
//    }
//
//    public void updateGatheringData()
//    {
//        mJoinItem.phone = edtPhoneNumber.getText().toString().trim();
//        mJoinItem.name = edtName.getText().toString().trim();
//
//        if(lblCountryCode.getTag() != null)
//            mJoinItem.country_code = (String)lblCountryCode.getTag();
//
//    }
//
//    public boolean validateValues(boolean isMsg)
//    {
//        String strMsg = "";
//        if(lblCountryCode.getTag() == null)
//        {
//            strMsg = getString(R.string.join_error_country);
//            if(isMsg)
//                onClick(lblCountryCode);
//        }
//        else if(edtPhoneNumber.getText().toString().trim().length() < 6)
//        {
//            strMsg = getString(R.string.join_error_phone);
//            if(isMsg) {
//                edtPhoneNumber.requestFocus();
//                MyApp.mIMM.showSoftInput(edtPhoneNumber, InputMethodManager.SHOW_IMPLICIT);
//            }
//        }
//        else if(edtName.getText().toString().trim().length() < 1)
//        {
//            strMsg = getString(R.string.join_error_your_name);
//            if(isMsg) {
//                edtName.requestFocus();
//                MyApp.mIMM.showSoftInput(edtName, InputMethodManager.SHOW_IMPLICIT);
//            }
//        }
//        if (strMsg.length() > 0) {
//            if(isMsg) PopupManager.showToast(this, strMsg, Key.TOAST_POPUP_ICON_ALERT);
//            return false;
//        }
//
//        return true;
//    }
//
//
//    @Override
//    public void onClick(View v) {
//
//        if(v == btnBack)
//        {
//            finish();
//        }
//        //////////////////////////
//        /// 다음
//        //////////////////////////
//        else if(v == btnNext || v == lblNext)
//        {
//            if (!validateValues(true))
//                return;
//
//            updateGatheringData();
//            Intent nextJoinIntent = new Intent(this, JoinIdPwdActivity.class);
//            nextJoinIntent.putExtra("mJoinItem",mJoinItem.toJson());
//            startActivityForResult(nextJoinIntent, 0);
//
//        }
//        //////////////////////////////
//        /// 국가 선택
//        /////////////////////////////
//        else if(v == lblCountryCode || v == btnCountryCode)
//        {
//            Locale locale = MyApp.get().getResources().getConfiguration().locale;
//            PopupManager.showDialog_Country(this, R.string.select_country_code, mDialCodeList, locale.getCountry(), new PopupManager.OnDialogObjectCompleted<DialcodeModel>() {
//                @Override
//                public void onCompleted(DialcodeModel obj) {
//                    lblCountryCode.setText(obj.toString());
//                    lblCountryCode.setTag(obj.code);
//                    updateUI_next();
//                }
//            });
//        }
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data)
//    {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (resultCode == RESULT_OK)
//        {
//            Intent intent = new Intent();
//            intent.putExtras(data.getExtras());
//            setResult(resultCode, intent);
//            finish();
//        }
//        else if (resultCode == Key.RESULT_CLOSE_ALL)
//        {
//            setResult(resultCode);
//            finish();
//        }
//    }
//}
