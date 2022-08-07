package com.oshnisoft.bottomnavrealm;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;


import com.oshnisoft.bottomnavrealm.dependency.AppComponent;
import com.oshnisoft.bottomnavrealm.dependency.DaggerAppComponent;

import io.realm.Realm;


/**
 * Created by sobuj on 30/06/2021.
 */

public class App extends Application {

    private Context context;
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        Realm.init(this);
        appComponent = DaggerAppComponent.Initializer.init(this);

        context = getApplicationContext();

        //Prevent Screenshot Or Screen Recorder in Android by this method
        setupActivityListener();
    }


    private void setupActivityListener() {
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
                activity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);		 }
            @Override
            public void onActivityStarted(Activity activity) {
            }
            @Override
            public void onActivityResumed(Activity activity) {

            }
            @Override
            public void onActivityPaused(Activity activity) {

            }
            @Override
            public void onActivityStopped(Activity activity) {
            }
            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
            }
            @Override
            public void onActivityDestroyed(Activity activity) {
            }
        });
    }


    private static AppComponent appComponent;

    private static App mInstance;


    public static synchronized App getInstance() {
        return mInstance;
    }


//    @Override
//    public void onCreate() {
//        super.onCreate();
//
//        mInstance = this;
//        Realm.init(this);
//        appComponent = DaggerAppComponent.Initializer.init(this);
//
//    }

    public static AppComponent getComponent(){
        return appComponent;
    }


    public App getActivity(){
        return this;
    }

//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(this);
//    }


}
