package com.oshnisoft.bottomnavrealm.dependency;


import com.oshnisoft.bottomnavrealm.App;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;


/**
 * Created by monir.sobuj on 5/17/17.
 */

@Module
public class AppModule {
    private App app;

    AppModule(App app){
        this.app = app;
    }

    @Provides
    App provideContext(){
        return app;
    }

    @Provides
    Realm provideRealm(){
        RealmConfiguration config = new RealmConfiguration.Builder()
                .allowWritesOnUiThread(true)
                .deleteRealmIfMigrationNeeded()
                .build();
        return Realm.getInstance(config);
    }


}
