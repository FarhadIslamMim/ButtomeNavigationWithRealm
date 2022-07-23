package com.oshnisoft.bottomnavrealm.ui.dashboard;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;

import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.oshnisoft.bottomnavrealm.App;
import com.oshnisoft.bottomnavrealm.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.realm.Realm;

public class AddDialogFragment extends DialogFragment {

    android.app.AlertDialog alertDialog;


    @BindView(R.id.tieName)
    TextInputEditText tieName;
    @BindView(R.id.tiePhoneNumber)
    TextInputEditText tiePhoneNumber;
    @BindView(R.id.btnCancel)
    Button btnCancel;
    @BindView(R.id.btnSave)
    Button btnSave;

    @Inject
    Realm r;


    Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
    public AddDialogFragment(){ }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Dialog dialog = super.onCreateDialog(savedInstanceState);
            App.getComponent().inject(this);
            View v = ((Activity) context).getLayoutInflater().inflate(R.layout.fragment_dialog_add, null);
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
                    postExpense();
                    Toast.makeText(context, "clicked", Toast.LENGTH_SHORT).show();
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

    public void postExpense(){

            r.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {
                    if (isValidate()) {
                        String name = "";
                        if (!TextUtils.isEmpty(tieName.getText())) {
                            name = tieName.getText().toString();
                        }
                        String phoneNumber = tiePhoneNumber.getText().toString();
                        Information info = new Information();
                        info.setName(name);
                        info.setPhone_number(phoneNumber);
                        realm.insertOrUpdate(info);
                        alertDialog.dismiss();
                    }
                }
            });

    }
    public boolean isValidate(){
        if(TextUtils.isEmpty(tiePhoneNumber.getText())){
            //ToastUtils.longToast("Please input amount to expense!!");
            Toast.makeText(context, "Please Enter Name And PhoneNumber", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}
