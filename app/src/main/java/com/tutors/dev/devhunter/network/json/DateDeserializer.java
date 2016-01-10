package com.tutors.dev.devhunter.network.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.tutors.dev.devhunter.util.StringUtil;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by jay on 2015. 7. 16..
 */
public class DateDeserializer implements JsonDeserializer<Date> {
    /**
     * 서버에서 UTC 기준을 받는 format 은 yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z' 이다.
     * java 에서 millisecond 는 3자리 SSS로 표현이 되어야 하고,
     * 3자리 미만의 값(ex .72) 은 잘못된 값으로 변환이 된다 (ex .072)
     * 3자리 초과의 값에 대해서도 잘못된 값으로 변환이 된다.
     * 따라서, App에서는 millisecond 없이 처리한다.
     */
    public static final String DATE_UTC_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    @Override
    public Date deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Date dateDate = null;
        String date = json.getAsString();
        if (StringUtil.isNotNull(date)) {
            //Log.d("DATE", "dserialize, date:" + date);
            int dotIndex = date.indexOf('.');
            if (dotIndex > 0) {
                date = date.substring(0, dotIndex);
            }
            //Log.d("DATE", "dserialize, date:" + date);
            SimpleDateFormat formatter = new SimpleDateFormat(DATE_UTC_FORMAT);
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                dateDate = formatter.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return dateDate;
    }
}