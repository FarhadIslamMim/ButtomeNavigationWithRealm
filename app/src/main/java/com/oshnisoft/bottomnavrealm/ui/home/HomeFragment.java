package com.oshnisoft.bottomnavrealm.ui.home;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.mikepenz.fastadapter.listeners.ClickEventHook;
import com.oshnisoft.bottomnavrealm.App;
import com.oshnisoft.bottomnavrealm.R;
import com.oshnisoft.bottomnavrealm.databinding.FragmentHomeBinding;
import com.oshnisoft.bottomnavrealm.ui.task.AddTaskDialogFragment;
import com.oshnisoft.bottomnavrealm.ui.task.Task;

import org.jetbrains.annotations.NotNull;


import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;

public class HomeFragment extends Fragment {

    FloatingActionButton floatingActionButton;
    RecyclerView revList;
    ImageView imageView;
    @Inject
    Realm realm;
    FastItemAdapter<HomeData> homeDataFastItemAdapter;
    Context context;
    Activity activity;
    long id;


    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = ((Activity) context);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);




        ((AppCompatActivity) context).setTitle("Dashboard");
        App.getComponent().inject(this);

        revList=root.findViewById(R.id.revList);
        floatingActionButton=root.findViewById(R.id.fabAddNew);
        imageView=root.findViewById(R.id.ivDelete);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                DialogFragment dialogFragment = new AddHomeDialogFragment();
                dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "add_expense_dialog");

            }
        });
//        imageView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "D clicked", Toast.LENGTH_SHORT).show();
//                //deleteName();
//
//            }
//        });
        getNameList();
        return root;
    }

    private List<HomeData> deleteName() {
        // RealmResults<PersonDetailsModel> results = myRealm.where(PersonDetailsModel.class).equalTo("id", personId).findAll();
        List<HomeData> homeData = realm.where(HomeData.class).findAll();
        realm.beginTransaction();
        homeData.remove(0);
        realm.commitTransaction();
        //homeDataFastItemAdapter.remove(position);
        //personDetailsAdapter.notifyDataSetChanged();
        return homeData;
    }

    public List<HomeData> getNameList() {
        List<HomeData> homeData = realm.where(HomeData.class).findAll();
        homeDataFastItemAdapter = new FastItemAdapter<>();
        homeDataFastItemAdapter.add(homeData);



        homeDataFastItemAdapter.withItemEvent(new ClickEventHook<HomeData>() {

            @Nullable
            @Override
            public View onBind(@NonNull RecyclerView.ViewHolder viewHolder) {
                //return the views on which you want to bind this event
                if (viewHolder instanceof HomeData.ViewHolder) {
                    return ((HomeData.ViewHolder) viewHolder).ivDelete;
                }
                return null;
            }
            @Override
            public void onClick(View v, int position, FastAdapter<HomeData> fastAdapter, HomeData item) {
                //ToastUtils.longToast(item.getTxtPhoneNo());
                itemDelete(item.getId());

            }
        });


        homeDataFastItemAdapter.setHasStableIds(true);
        homeDataFastItemAdapter.withSelectable(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        revList.setLayoutManager(layoutManager);
        revList.setAdapter(homeDataFastItemAdapter);


        return homeData;


    }
    public void itemDelete(long id){
        HomeData modal = realm.where(HomeData.class).equalTo("id", id).findFirst();
        // on below line we are executing a realm transaction.
        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                // on below line we are calling a method for deleting this course
                modal.deleteFromRealm();
            }
        });
        Toast.makeText(context, "Name Delete Successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        getNameList();
    }



}
