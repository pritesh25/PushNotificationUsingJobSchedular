package com.selfie.life.selfielife;

        import android.annotation.SuppressLint;
        import android.app.Activity;
        import android.app.job.JobInfo;
        import android.app.job.JobScheduler;
        import android.content.BroadcastReceiver;
        import android.content.ComponentName;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.graphics.Color;
        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.annotation.Nullable;
        import android.support.v4.app.Fragment;
        import android.support.v7.app.AlertDialog;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ProgressBar;
        import android.widget.Toast;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.IOException;

        import okhttp3.MediaType;
        import okhttp3.RequestBody;
        import okhttp3.ResponseBody;
        import retrofit2.Call;
        import retrofit2.Callback;
        import retrofit2.Response;
        import retrofit2.http.Multipart;
        import retrofit2.http.POST;
        import retrofit2.http.Part;

        import static com.selfie.life.selfielife.MyConfiguration.BASE_URL_File;
        import static com.selfie.life.selfielife.MyConfiguration.PROFILE_TOKEN;

public class FragmentNotification extends Fragment
{
    ProgressBar     pb_friend_profile_picture,pb_friend_profile;
    String  str_table_name,
               str_id;

    private static final String TAG = FragmentNotification.class.getSimpleName() ;
    @SuppressLint("NewApi")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notification,container,false);

        @SuppressLint({"NewApi", "LocalSuppress"}) JobScheduler jobScheduler = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            jobScheduler = (JobScheduler) getActivity().getSystemService(Context.JOB_SCHEDULER_SERVICE);
        }

        @SuppressLint({"NewApi", "LocalSuppress"}) JobInfo jobInfo = new JobInfo.Builder(11, new ComponentName(getActivity(), TestJobService.class))
                // only add if network access is required
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .build();

        jobScheduler.schedule(jobInfo);


        //Intent intent = new Intent(Intent.ACTION_BOOT_COMPLETED);
        //getContext().sendBroadcast(intent);

        //fetchingFriendProfileData("6");
        init(view);

        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    Intent intent = new Intent(Intent.ACTION_BOOT_COMPLETED);
                    getContext().sendBroadcast(intent);
                }
                catch (Exception e)
                {
                    Log.d(TAG,"catch error = "+e.getMessage());
                }

            }
        });

        return view;
    }

    private void init(View view) {


        //pb_friend_profile_picture = (ProgressBar) view.findViewById(R.id.pb_friend_profile_picture);
    }

    private void fetchingFriendProfileData(String str_myUser_id) {
//        pb_friend_profile_picture.setVisibility(View.VISIBLE);

      //  RequestBody token = RequestBody.create(MediaType.parse("text/plain"), MyConfiguration.getPreferences(getContext(),PROFILE_TOKEN));
        RequestBody token = RequestBody.create(MediaType.parse("text/plain"),"p+eN45whyGL9HnPqX/mttPgq+Hmv2Fp4rPbUVzo9j+mth7FwGVTWY+hgQ4LsdKEE+xX2uB/JyBaoGB6qFUZQ1A==");
        RequestBody user_id         = RequestBody.create(MediaType.parse("text/plain"), str_myUser_id);
        fetchingSinglePostDataInterface service = MyConfiguration.getRetrofit(MyConfiguration.BASE_URL).create(fetchingSinglePostDataInterface.class);
        Call<ResponseBody> req = service.postImage(token,user_id);
        req.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response)
            {
                String output="";
                try
                {
                    output = response.body().string();
                    Log.d(TAG,"Friend Profile output > " + output);
                    JSONObject jsonObject = new JSONObject(output);
                    boolean res = Boolean.parseBoolean(jsonObject.getString("result"));
                   // String str_end = jsonObject.getString("endless_info");

                    JSONArray subArray = jsonObject.getJSONArray("endless_info");

                    for (int i = 0; i < subArray.length(); i++) {

                        JSONObject jsonObject1 = subArray.getJSONObject(i);

                        str_table_name        = jsonObject1.getString("table_name");
                        str_id         = jsonObject1.getString("id");

                        Toast.makeText(getContext(), ""+str_table_name, Toast.LENGTH_SHORT).show();

                        Log.d(TAG,"Endless output >"+str_table_name);



                    }



                } catch (JSONException e){
                    e.printStackTrace();     Log.d(TAG,"JSON Exception = "+e.getMessage().toString());
//                    pb_friend_profile_picture.setVisibility(View.GONE);
                }catch (IOException e){
                    e.printStackTrace();    Log.d(TAG,"IO Exception = "+e.getMessage().toString());
                 //   pb_friend_profile_picture.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t){
                Log.d(TAG,"Retrofit Failure");
            }
        });
    }

    public interface fetchingSinglePostDataInterface {
        @Multipart
        @POST(BASE_URL_File+"/check_push_notification.php")
        Call<ResponseBody> postImage(
                @Part("token")              RequestBody token,
                @Part("user_id")            RequestBody user_id );
    }




}
