package com.oshnisoft.bottomnavrealm.ui.notifications;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.oshnisoft.bottomnavrealm.R;
import com.oshnisoft.bottomnavrealm.databinding.FragmentNotificationsBinding;

import org.jetbrains.annotations.NotNull;

public class NotificationsFragment extends Fragment {

    Context context;
    Activity activity;

    @Override
    public void onAttach(@NonNull @NotNull Context context) {
        super.onAttach(context);
        this.context = context;
        this.activity = ((Activity) context);
    }
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_notifications, container, false);
        ((AppCompatActivity) context).setTitle("Dashboard");
        return root;
    }
}