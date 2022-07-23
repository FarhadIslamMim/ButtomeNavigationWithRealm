package com.oshnisoft.bottomnavrealm.ui.dashboard;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.commons.adapters.FastItemAdapter;
import com.oshnisoft.bottomnavrealm.App;
import com.oshnisoft.bottomnavrealm.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import io.realm.Realm;
import io.realm.RealmObject;

public class DashboardFragment extends Fragment {
    
    private FloatingActionButton floatingActionButton;

    RecyclerView revList;
    @Inject
    Realm realm;

    Context context;
    Activity activity;
    FastItemAdapter<Information> informationFastItemAdapter;
    private CompositeDisposable mCompositeDisposable;

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = ((Activity) context);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        App.getComponent().inject(this);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);
        ((AppCompatActivity) context).setTitle("Dashboard");
        
        floatingActionButton=root.findViewById(R.id.fabAddNew);
        revList=root.findViewById(R.id.revList);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show();
                DialogFragment dialogFragment = new AddDialogFragment();
                dialogFragment.show(((AppCompatActivity) context).getSupportFragmentManager(), "add_expense_dialog");

            }
        });


        getInformationList();


        return root;

    }

    public List<Information> getInformationList(){
        List<Information> informationList = realm.where(Information.class).findAll();
        informationFastItemAdapter = new FastItemAdapter<>();
        informationFastItemAdapter.add(informationList);
        informationFastItemAdapter.setHasStableIds(true);
        informationFastItemAdapter.withSelectable(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        revList.setLayoutManager(layoutManager);
        revList.setAdapter(informationFastItemAdapter);


        return informationList;
    }

    @Override
    public void onResume() {
        super.onResume();
        getInformationList();
    }
}
