package com.oshnisoft.bottomnavrealm.ui.course;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.oshnisoft.bottomnavrealm.App;
import com.oshnisoft.bottomnavrealm.R;
import com.oshnisoft.bottomnavrealm.ui.task.AddTaskDialogFragment;
import com.oshnisoft.bottomnavrealm.ui.task.Task;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class CourseFragment extends Fragment {



    public CourseFragment() {
        // Required empty public constructor
    }

    Context context;
    Activity activity;
    private FloatingActionButton floatingActionButton;
    RecyclerView revList;
    @Inject
    Realm realm;
    FastItemAdapter<Course> courseFastItemAdapter;

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = ((Activity) context);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_course, container, false);
        ((AppCompatActivity) context).setTitle("Course");

        App.getComponent().inject(this);
        floatingActionButton=root.findViewById(R.id.fabAddNew);
        revList=root.findViewById(R.id.revList);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                DialogFragment dialogFragment = new AddCourseDialogFragment();
                dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "add_expense_dialog");

            }
        });

        getCourseList();
        return root;
    }

    public List<Course> getCourseList() {
        List<Course> courseList = realm.where(Course.class).findAll();
        courseFastItemAdapter = new FastItemAdapter<>();
        courseFastItemAdapter.add(courseList);
        courseFastItemAdapter.setHasStableIds(true);
        courseFastItemAdapter.withSelectable(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        revList.setLayoutManager(layoutManager);
        revList.setAdapter(courseFastItemAdapter);


        return courseList;


    }
    @Override
    public void onResume() {
        super.onResume();
        getCourseList();
    }
}