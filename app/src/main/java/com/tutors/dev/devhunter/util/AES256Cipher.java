package com.tutors.dev.devhunter.util;



import android.util.Base64;

import com.tutors.dev.devhunter.data.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Created by shs on 2015-09-11.
 */
public class AES256Cipher {

    private static volatile AES256Cipher INSTANCE;
    private static byte[] secretKey  = Key.AES_SECRET_KEY;
    private static byte[] IV;

    public static AES256Cipher getInstance() {
        if(INSTANCE == null) {
            synchronized (AES256Cipher.class) {
                if(INSTANCE == null)
                    INSTANCE = new AES256Cipher();
            }
        }
        return INSTANCE;
    }

    private AES256Cipher() {
        IV = Key.AES_IV;
    }

    public static String AES_encode(String plain)
    {
        if(StringUtil.isNull(plain))
            return "";

        byte[] keyData = Key.AES_SECRET_KEY;
        SecretKeySpec secureKey = new SecretKeySpec(keyData, "AES");

        try {

            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.ENCRYPT_MODE, secureKey, new IvParameterSpec(IV));

            byte[] encrypted = c.doFinal(plain.getBytes("UTF-8"));
            return new String(Base64.encode(encrypted, Base64.DEFAULT));
        } catch (Exception e) {
            ExLog.e("AES256Cipher", e.getMessage(), e);
            ExLog.report("AES256Cipher-AES_encode");
        }
        return "";
    }

    public static String AES_decode(String encryptedData)
    {
        if(StringUtil.isNull(encryptedData))
            return "";

        byte [] keyData = Key.AES_SECRET_KEY;
        SecretKey secureKey = new SecretKeySpec(keyData, "AES");

        try {

            Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
            c.init(Cipher.DECRYPT_MODE, secureKey, new IvParameterSpec(IV));

            byte[] decodeByte = Base64.decode(encryptedData.getBytes(),Base64.DEFAULT);
            return new String(c.doFinal(decodeByte), "UTF-8");

        } catch (Exception e) {
            ExLog.e("AES256Cipher", e.getMessage(), e);
            ExLog.report("AES256Cipher-AES_decode");
        }
        return "";
    }




}
