package com.oshnisoft.bottomnavrealm.dependency;




import com.oshnisoft.bottomnavrealm.App;
import com.oshnisoft.bottomnavrealm.MainActivity;
import com.oshnisoft.bottomnavrealm.ui.course.AddCourseDialogFragment;
import com.oshnisoft.bottomnavrealm.ui.course.CourseFragment;
import com.oshnisoft.bottomnavrealm.ui.dashboard.AddDialogFragment;
import com.oshnisoft.bottomnavrealm.ui.dashboard.DashboardFragment;
import com.oshnisoft.bottomnavrealm.ui.task.AddTaskDialogFragment;
import com.oshnisoft.bottomnavrealm.ui.task.TaskFragment;

import dagger.Component;



@Component(modules = AppModule.class)

public interface AppComponent {

    void inject(MainActivity mainActivity);
    void inject(AddDialogFragment addDialogFragment);
    void inject(DashboardFragment dashboardFragment);
    void inject(TaskFragment taskFragment);
    void inject(AddTaskDialogFragment addTaskDialogFragment);
    void inject(AddCourseDialogFragment addCourseDialogFragment);
    void inject(CourseFragment courseFragment);


    final class Initializer {
        private Initializer(){}

        public static AppComponent init(App app){
            return DaggerAppComponent.builder().appModule(new AppModule(app)).build();
        }
    }

}
