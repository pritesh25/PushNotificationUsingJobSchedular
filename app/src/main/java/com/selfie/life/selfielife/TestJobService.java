package com.selfie.life.selfielife;

import android.annotation.SuppressLint;
import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * JobService to be scheduled by the JobScheduler.
 * start another service
 */
@SuppressLint("NewApi")
public class TestJobService extends JobService {
    //private static final String TAG = "SyncService";
    private static final String TAG = TestJobService.class.getSimpleName() ;

    @Override
    public boolean onStartJob(JobParameters params) {
        Intent service = new Intent(getApplicationContext(), MainActivity.class);
        getApplicationContext().startService(service);
        Toast.makeText(this, "job started", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"Pritesh Notification");
        Util.scheduleJob(getApplicationContext()); // reschedule the job
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
       // Util.scheduleJob(getApplicationContext());
        return true;
    }

}