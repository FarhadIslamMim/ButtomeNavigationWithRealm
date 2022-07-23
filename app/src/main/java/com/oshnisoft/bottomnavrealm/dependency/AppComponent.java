package com.oshnisoft.bottomnavrealm.dependency;




import com.oshnisoft.bottomnavrealm.App;
import com.oshnisoft.bottomnavrealm.MainActivity;
import com.oshnisoft.bottomnavrealm.ui.dashboard.AddDialogFragment;
import com.oshnisoft.bottomnavrealm.ui.dashboard.DashboardFragment;

import dagger.Component;

/**
 * Created by Tariqul.Islam on 5/17/17.
 */

@Component(modules = AppModule.class)

public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(AddDialogFragment addDialogFragment);
    void inject(DashboardFragment dashboardFragment);

    final class Initializer {
        private Initializer(){}

        public static AppComponent init(App app){
            return DaggerAppComponent.builder().appModule(new AppModule(app)).build();
        }
    }

}
