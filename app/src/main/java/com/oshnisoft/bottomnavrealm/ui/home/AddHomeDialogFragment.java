package com.oshnisoft.bottomnavrealm.ui.home;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.oshnisoft.bottomnavrealm.App;
import com.oshnisoft.bottomnavrealm.R;
import com.oshnisoft.bottomnavrealm.ui.task.Task;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;


public class AddHomeDialogFragment extends DialogFragment {


    public AddHomeDialogFragment() {
        // Required empty public constructor
    }

    android.app.AlertDialog alertDialog;
    @BindView(R.id.etName)
    EditText editTextName;
    @BindView(R.id.btnSave)
    Button buttonSave;
    @BindView(R.id.btnCancel)
    Button buttonCancel;
    @Inject
    Realm r;

    Context context;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        App.getComponent().inject(this);
        View v = ((Activity) context).getLayoutInflater().inflate(R.layout.fragment_add_home_dialog, null);
        ButterKnife.bind(this, v);

        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        builder.setView(v);
        alertDialog = builder.create();
        alertDialog.setCanceledOnTouchOutside(true);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        return alertDialog;
    }
    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    private void saveData() {
        r.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (isValidate()) {
                    String name = "";
                    if (!TextUtils.isEmpty(editTextName.getText())) {
                        name = editTextName.getText().toString();
                    }

                    HomeData homeData = new HomeData();
                    homeData.setName(name);
                    realm.insertOrUpdate(homeData);
                    alertDialog.dismiss();
                }
            }
        });
    }
    public boolean isValidate() {
        if (TextUtils.isEmpty(editTextName.getText())) {
            //ToastUtils.longToast("Please input amount to expense!!");
            Toast.makeText(context, "Please Enter Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}