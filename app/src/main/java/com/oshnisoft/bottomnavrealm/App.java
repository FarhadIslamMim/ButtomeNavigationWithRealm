package com.oshnisoft.bottomnavrealm;

import android.app.Application;


import com.oshnisoft.bottomnavrealm.dependency.AppComponent;
import com.oshnisoft.bottomnavrealm.dependency.DaggerAppComponent;

import io.realm.Realm;


/**
 * Created by sobuj on 30/06/2021.
 */

public class App extends Application {

    private static AppComponent appComponent;

    private static App mInstance;


    public static synchronized App getInstance() {
        return mInstance;
    }


    @Override
    public void onCreate() {
        super.onCreate();

        mInstance = this;
        Realm.init(this);
        appComponent = DaggerAppComponent.Initializer.init(this);

    }

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
