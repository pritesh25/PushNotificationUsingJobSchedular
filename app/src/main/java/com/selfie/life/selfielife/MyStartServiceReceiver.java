package com.selfie.life.selfielife;

import android.annotation.SuppressLint;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

public class MyStartServiceReceiver extends BroadcastReceiver {

    private static final String TAG = TestJobService.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {

        Log.d(TAG,"receiver called");
      

        Util.scheduleJob(context);

        String action = intent.getAction();

        if(Intent.ACTION_BOOT_COMPLETED.equals(action))
        {
            Log.d(TAG,"hurrey , intent action matched");

            startNewService(context);
        }
        else
        {
            Log.d(TAG,"intent action not matched");

        }
    }

    private void startNewService(Context context)
    {
        @SuppressLint({"NewApi", "LocalSuppress"}) JobScheduler jobScheduler = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            jobScheduler = (JobScheduler) context.getSystemService(Context.JOB_SCHEDULER_SERVICE);
        }

        @SuppressLint({"NewApi", "LocalSuppress"}) JobInfo jobInfo = new JobInfo.Builder(11, new ComponentName(context, TestJobService.class))
                // only add if network access is required
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .build();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            jobScheduler.schedule(jobInfo);
        }
    }
}