package com.pangge.moontest.sync;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
<<<<<<< HEAD
import android.util.Log;
=======
>>>>>>> 880b76cfb78fe7545fb118abe68f626a27df344e


import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;
<<<<<<< HEAD
import com.pangge.moontest.Weather1ContentProvider;
=======
import com.pangge.moontest.WeatherContentProvider;
>>>>>>> 880b76cfb78fe7545fb118abe68f626a27df344e
import com.pangge.moontest.data.WeatherContract;

import java.util.concurrent.TimeUnit;

/**
 * Created by iuuu on 17/5/16.
 */

public class WeatherSyncUtils {

<<<<<<< HEAD
    // add constant values to sync weather every 3-4 hours

=======
>>>>>>> 880b76cfb78fe7545fb118abe68f626a27df344e
    private static final int SYNC_INTERVAL_HOURS = 3;
    private static final int SYNC_INTERVAL_SECONDS = (int) TimeUnit.HOURS.toSeconds(SYNC_INTERVAL_HOURS);
    private static final int SYNC_FLEXTIME_SECONDS = SYNC_INTERVAL_SECONDS/3;

    private static boolean sInitialized;

    private static final String WEATHER_SYNC_TAG = "weather-sync";

    static void scheduleFirebaseJobDispaatcherSync(final Context context){
        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
        Job syncWeatherJob = dispatcher.newJobBuilder()
                .setService(WeatherFirebaseJobService.class)
                .setTag(WEATHER_SYNC_TAG)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(
<<<<<<< HEAD
                        //10, 20))
=======
>>>>>>> 880b76cfb78fe7545fb118abe68f626a27df344e
                        SYNC_INTERVAL_SECONDS,
                        SYNC_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                .setReplaceCurrent(true)
                .build();
<<<<<<< HEAD
        Log.i("--success-","NO KIDDING"+SYNC_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS);
        Log.i("--success-","NO KIDDING"+SYNC_INTERVAL_SECONDS);


=======
>>>>>>> 880b76cfb78fe7545fb118abe68f626a27df344e
        dispatcher.schedule(syncWeatherJob);
    }

    synchronized public static void initialize(final Context context){

        if(sInitialized) return;
        sInitialized = true;
        scheduleFirebaseJobDispaatcherSync(context);

        /*
          * We need to check to see if our ContentProvider has data to display in our forecast
          * list. However, performing a query on the main thread is a bad idea as this may
          * cause our UI to lag. Therefore, we create a thread in which we will run the query
          * to check the contents of our ContentProvider.
          */
<<<<<<< HEAD
        /**
         * after  WeatherSyncUtils.startImmediateSync(this); ContentProvider can not be null

=======
>>>>>>> 880b76cfb78fe7545fb118abe68f626a27df344e

        Thread checkForEmpty = new Thread(new Runnable() {
            @Override
            public void run() {
<<<<<<< HEAD
                //only 1 is ok
                Uri forecastQueryUri = Weather1ContentProvider.CONTENT_URI;

=======
                Uri forecastQueryUri = WeatherContentProvider.CONTENT_URI;
>>>>>>> 880b76cfb78fe7545fb118abe68f626a27df344e
                String[] projectionColumns = {WeatherContract.WeatherEntry._ID};
                String selectionStatement = WeatherContract.WeatherEntry
                        .getSqlSelectForTodayOnwards();

                Cursor cursor = context.getContentResolver().query(
                        forecastQueryUri,
                        projectionColumns,
                        selectionStatement,
                        null,
                        null);
<<<<<<< HEAD
                Log.i("---","NO KIDDING");

                if(null == cursor || cursor.getCount() == 0){
                    Log.i("-none Content P--","NO KIDDING");

=======

                if(null == cursor || cursor.getCount() == 0){
>>>>>>> 880b76cfb78fe7545fb118abe68f626a27df344e
                    startImmediateSync(context);
                }

                cursor.close();
            }
        });

<<<<<<< HEAD
        // Finally, once the thread is prepared, fire it off to perform our checks.
        checkForEmpty.start();
        */
=======
        /* Finally, once the thread is prepared, fire it off to perform our checks. */
        checkForEmpty.start();
>>>>>>> 880b76cfb78fe7545fb118abe68f626a27df344e
    }
    public static void startImmediateSync(final Context context) {
        Intent intentToSyncImmediately = new Intent(context, WeatherSyncIntentService.class);
        context.startService(intentToSyncImmediately);
    }
}
