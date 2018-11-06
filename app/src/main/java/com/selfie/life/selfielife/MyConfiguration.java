package com.selfie.life.selfielife;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

public class MyConfiguration
{

    private static final String TAG = MyConfiguration.class.getSimpleName();

    //*****************************************************************//
    //check folder path in registration php file for post and story etc//
    //*****************************************************************//

//     public static final String MAIN_URL          = "http://selfilife.com/App/media/";
//     public static final String BASE_URL          = "http://selfilife.com";
//     public static final String PROFILE_IMAGE_URL = "http://selfilife.com/App/";   // FOR SUGGESTED FRIEND PROFILE IMAGE
//     public static final String BASE_URL_File     = "/App/";

  /*  public static final String MAIN_URL             = "http://192.168.1.144/selfielife/media/";
    public static final String BASE_URL             = "http://192.168.1.144";
    public static final String PROFILE_IMAGE_URL    = "http://192.168.1.144/selfielife/";   // FOR SUGGESTED FRIEND PROFILE IMAGE
    public static final String BASE_URL_File        = "/selfielife/";  */


    public static final String MAIN_URL             = "http://192.168.1.107/selfielife/media/";
    public static final String BASE_URL             = "http://192.168.1.107";
    public static final String PROFILE_IMAGE_URL    = "http://192.168.1.107/selfielife/";   // FOR SUGGESTED FRIEND PROFILE IMAGE
    public static final String BASE_URL_File        = "/selfielife/";

    public static Retrofit getRetrofit(String BASE_URL) {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.connectTimeout(1, TimeUnit.MINUTES);
        httpClient.readTimeout(1,TimeUnit.MINUTES);
        httpClient.writeTimeout(1,TimeUnit.MINUTES);
        Retrofit.Builder builder = new Retrofit.Builder().baseUrl(BASE_URL);
        Retrofit retrofit = builder.client(httpClient.build()).build();
        return retrofit;
    }

    // shared preference keys
    public static final String DEVICE_PREF_NAME         = "DevicePref";

    public static final String PROFILE_USER_ID          = "profile_user_id";
    public static final String PROFILE_NAME             = "profile_full_name";
    public static final String PROFILE_IMAGE_NAME       = "profile_image_name";
    public static final String PROFILE_EMAIL            = "profile_email_id";
    public static final String PROFILE_USER_NAME        = "profile_user_name";
    public static final String PROFILE_MOBILE           = "profile_mobile";

    public static final String PROFILE_TOKEN            = "profile_token";
    public static final String PROFILE_LOGIN_ID         = "profile_login_id";
    public static final String PROFILE_USER_TYPE        = "profile_user_type";
    public static final String PROFILE_TEMP_CODE        = "profile_temp_code";
    public static final String PROFILE_SOURCE           = "profile_source";
    public static final String PROFILE_ACCOUNT_TYPE     = "profile_account_type";
    public static final String PROFILE_LOGIN_TYPE       = "profile_login_type";
    public static final String PROFILE_LOGIN_ACTIVE     = "profile_login_active";

    public static final String PROFILE_BIO              = "profile_bio";
    public static final String PROFILE_OCCUPATION       = "profile_occupation";
    public static final String PROFILE_CITY             = "profile_city";
    public static final String PROFILE_COUNTRY          = "profile_country";
    public static final String PROFILE_COUNTRY_CODE     = "profile_country_code";

    public static final String PROFILE_DOB              = "profile_dob";
    public static final String PROFILE_GENDER           = "profile_gender";
    public static final String PROFILE_MARITAL          = "profile_marital";

    public static final String MAIN_FOLDER              = "SelfiLife";
    public static final String THUMB_FOLDER             = ".thumb";
    public static final String STORY_FOLDER             = ".story";

    public static final int VIEW_TYPE_ITEM_LEFT              = 0;
    public static final int VIEW_TYPE_ITEM_RIGHT             = 1;
    public static final int VIEW_TYPE_LOADING                = 2;

    public static final int REQUEST_GALLERY_IMAGE            = 01;
    public static final int REQUEST_GALLERY_VIDEO            = 02;

    public static final int SINGLE_SELECTION                 = 11;
    public static final int MULTI_SELECTION                  = 12;

    public static final int TYPE_HEADER                      = 21;
    public static final int TYPE_ITEM                        = 22;
    public static final int TYPE_FOOTER                      = 23;

    public static final int VIEW_TYPE_IMAGE                  = 31;
    public static final int VIEW_TYPE_VIDEO                  = 32;

    public static final int PERMISSION_CODE                  = 41;

    public static String OFFLINE_STORY_CLAUSE     = null;

    public static final long   UPDATE_INTERVAL_IN_MILLISECONDS = 10000;
    public static final long   FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS = 5000;
    public static final int    REQUEST_CHECK_SETTINGS = 100;

    public static final String OTP_sender_id             = "59029410";

    // set value to shared preference
    public static void setPreferences(Context context, String key, String value) {
        SharedPreferences DevicePref            = context.getSharedPreferences(DEVICE_PREF_NAME, 0);
        SharedPreferences.Editor DeviceEditor   = DevicePref.edit();
        DeviceEditor.putString(key, value);
        DeviceEditor.commit();
    }
    // get value from shared preference
    public static String getPreferences(Context context, String key) {
        SharedPreferences DevicePref = context.getSharedPreferences(DEVICE_PREF_NAME, 0);
        String data = DevicePref.getString(key, "");
        return data;
    }

    public boolean isPermissionAllowed(Context context, String singlePermission) {

        boolean flag;

        int result = ContextCompat.checkSelfPermission(context, singlePermission);

        //If permission is granted returning true
        if (result == PackageManager.PERMISSION_GRANTED)
        {
            flag = true;
        }
        else
        {
            //If permission is not granted returning false
            flag = false;
        }

        return flag;
        
    }

    public void requestStoragePermission(Activity activity, Context context, String[] permissions){

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.ACCESS_FINE_LOCATION)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission

            //Toast.makeText(context,""+context.getResources().getText(R.string.we_need_this_permission_to_perform_operation),Toast.LENGTH_SHORT).show();
            Log.d(TAG,""+context.getResources().getText(R.string.we_need_location_permission_to_perform_operation));

        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.READ_PHONE_STATE)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission

            //Toast.makeText(context,""+context.getResources().getText(R.string.we_need_this_permission_to_perform_operation),Toast.LENGTH_SHORT).show();
            Log.d(TAG,""+context.getResources().getText(R.string.we_need_phone_state_permission_to_perform_operation));

        }

        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.RECEIVE_SMS)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission

            //Toast.makeText(context,""+context.getResources().getText(R.string.we_need_this_permission_to_perform_operation),Toast.LENGTH_SHORT).show();
            Log.d(TAG,""+context.getResources().getText(R.string.we_need_sms_receive_permission_to_perform_operation));

        }


        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,Manifest.permission.READ_SMS)){
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission

            //Toast.makeText(context,""+context.getResources().getText(R.string.we_need_this_permission_to_perform_operation),Toast.LENGTH_SHORT).show();
            Log.d(TAG,""+context.getResources().getText(R.string.we_need_sms_read_permission_to_perform_operation));

        }



        //And finally ask for the permission
        //ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_CODE);
        ActivityCompat.requestPermissions(activity,permissions,PERMISSION_CODE);
    }
}