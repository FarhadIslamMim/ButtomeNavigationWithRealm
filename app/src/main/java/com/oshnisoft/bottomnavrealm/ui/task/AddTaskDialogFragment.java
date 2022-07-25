package com.oshnisoft.bottomnavrealm.ui.task;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.DialogFragment;

import com.google.android.material.textfield.TextInputEditText;
import com.oshnisoft.bottomnavrealm.App;
import com.oshnisoft.bottomnavrealm.R;
import com.oshnisoft.bottomnavrealm.ui.dashboard.Information;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class AddTaskDialogFragment extends DialogFragment {

    android.app.AlertDialog alertDialog;

    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.btnSave)
    Button btnSave;
    @BindView((R.id.txtName))
    EditText txtName;
    @BindView((R.id.txtLocation))
    EditText txtLocation;
    @BindView((R.id.txtTime))
    EditText txtTime;
    @BindView((R.id.txtDate))
    EditText txtDate;
    @BindView((R.id.txtTaskName))
    EditText txtTaskName;

    @Inject
    Realm r;



    Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
    public AddTaskDialogFragment(){ }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Dialog dialog = super.onCreateDialog(savedInstanceState);
            App.getComponent().inject(this);
            View v = ((Activity) context).getLayoutInflater().inflate(R.layout.fragment_dialog_add_task, null);
            ButterKnife.bind(this, v);

            //initializeView();


            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            builder.setView(v);

            alertDialog = builder.create();


            alertDialog.setCanceledOnTouchOutside(false);


            btnSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    postTask();

                }
            });
            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
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

    private void postTask() {
        Toast.makeText(context, "save button clicked", Toast.LENGTH_SHORT).show();

        r.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                if (isValidate()) {
                    String name = "";
                    if (!TextUtils.isEmpty(txtName.getText())) {
                        name = txtName.getText().toString();
                    }
                    String location ="";
                    if (!TextUtils.isEmpty(txtLocation.getText())) {
                        location = txtLocation.getText().toString();
                    }
                    String time ="";
                    if (!TextUtils.isEmpty(txtTime.getText())) {
                        time = txtTime.getText().toString();
                    }
                    String date ="";
                    if (!TextUtils.isEmpty(txtDate.getText())) {
                        date = txtDate.getText().toString();
                    }
                    String taskname ="";
                    if (!TextUtils.isEmpty(txtTaskName.getText())) {
                        taskname = txtTaskName.getText().toString();
                    }
                    Task task = new Task();
                    task.setName(name);
                    task.setLocation(location);
                    task.setTime(time);
                    task.setDate(date);
                    task.setTasks(taskname);
                    realm.insertOrUpdate(task);
                    alertDialog.dismiss();
                }
            }
        });
    }
    public boolean isValidate(){
        if(TextUtils.isEmpty(txtName.getText())){
            //ToastUtils.longToast("Please input amount to expense!!");
            Toast.makeText(context, "Please Enter Name", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(txtLocation.getText())){
            //ToastUtils.longToast("Please input amount to expense!!");
            Toast.makeText(context, "Please Enter Location", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(txtTime.getText())){
            //ToastUtils.longToast("Please input amount to expense!!");
            Toast.makeText(context, "Please Enter Time", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(txtDate.getText())){
            //ToastUtils.longToast("Please input amount to expense!!");
            Toast.makeText(context, "Please Enter Date", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(TextUtils.isEmpty(txtTaskName.getText())){
            //ToastUtils.longToast("Please input amount to expense!!");
            Toast.makeText(context, "Please Enter Task", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }


}
