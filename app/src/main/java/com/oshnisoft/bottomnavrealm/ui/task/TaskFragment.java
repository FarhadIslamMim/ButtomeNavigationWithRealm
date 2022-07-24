package com.oshnisoft.bottomnavrealm.ui.task;

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
import com.oshnisoft.bottomnavrealm.ui.dashboard.AddDialogFragment;
import com.oshnisoft.bottomnavrealm.ui.dashboard.Information;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;


public class TaskFragment extends Fragment {
    private FloatingActionButton floatingActionButton;
    RecyclerView revList;
    @Inject
    Realm realm;
    FastItemAdapter<Task> taskFastItemAdapter;
    Context context;
    Activity activity;


    public TaskFragment() {
        // Required empty public constructor
    }


    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = ((Activity) context);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_task, container, false);
        ((AppCompatActivity) context).setTitle("Task");
        App.getComponent().inject(this);
        floatingActionButton=root.findViewById(R.id.fabAddNew);
        revList=root.findViewById(R.id.revList);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                DialogFragment dialogFragment = new AddTaskDialogFragment();
                dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "add_expense_dialog");

            }
        });

        getTaskList();
        return root;
    }

    public List<Task> getTaskList() {

        List<Task> taskList = realm.where(Task.class).findAll();
        taskFastItemAdapter = new FastItemAdapter<>();
        taskFastItemAdapter.add(taskList);
        taskFastItemAdapter.setHasStableIds(true);
        taskFastItemAdapter.withSelectable(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        revList.setLayoutManager(layoutManager);
        revList.setAdapter(taskFastItemAdapter);


        return taskList;


    }
    @Override
    public void onResume() {
        super.onResume();
        getTaskList();
    }
}