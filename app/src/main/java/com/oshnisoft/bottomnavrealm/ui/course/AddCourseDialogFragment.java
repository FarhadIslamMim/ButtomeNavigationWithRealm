package com.oshnisoft.bottomnavrealm.ui.course;

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

import com.oshnisoft.bottomnavrealm.App;
import com.oshnisoft.bottomnavrealm.R;
import com.oshnisoft.bottomnavrealm.ui.task.Task;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.Realm;

public class AddCourseDialogFragment extends DialogFragment {

    android.app.AlertDialog alertDialog;

    @BindView(R.id.btnCancelCourse)
    Button btnCancelCourse;
    @BindView(R.id.btnSaveCourse)
    Button btnSaveCourse;

    @BindView(R.id.EdtCourseName)
    EditText EdtCourseName;
    @BindView(R.id.EdtCourseDuration)
    EditText EdtCourseDuration;
    @BindView(R.id.EdtCourseTracks)
    EditText EdtCourseTracks;
    @BindView(R.id.EdtCourseMoney)
    EditText EdtCourseMoney;


    @Inject
    Realm r;



    Context context;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }
    public AddCourseDialogFragment(){ }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            Dialog dialog = super.onCreateDialog(savedInstanceState);
            App.getComponent().inject(this);
            View v = ((Activity) context).getLayoutInflater().inflate(R.layout.fragment_add_course_dialog, null);
            ButterKnife.bind(this, v);

            //initializeView();


            android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(context);

            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

            builder.setView(v);

            alertDialog = builder.create();


            alertDialog.setCanceledOnTouchOutside(false);


            btnSaveCourse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    postCourse();

                }
            });
            btnCancelCourse.setOnClickListener(new View.OnClickListener() {
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

    private void postCourse() {
        r.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
//                {  if (TextUtils.isEmpty(cName)) {
//                    courseNameEdt.setError("Please enter Course Name");
//                } else if (TextUtils.isEmpty(courseDescription)) {
//                    courseDescriptionEdt.setError("Please enter Course Description");
//                } else if (TextUtils.isEmpty(courseDuration)) {
//                    courseDurationEdt.setError("Please enter Course Duration");
//                } else if (TextUtils.isEmpty(courseTracks)) {
//                    courseTracksEdt.setError("Please enter Course Tracks");
//                }
//                }
                if (isValidate()) {
                    String cName = "";
                    if (!TextUtils.isEmpty(EdtCourseName.getText())) {
                        cName = EdtCourseName.getText().toString();
                    }
                    String cTracts ="";
                    if (!TextUtils.isEmpty(EdtCourseTracks.getText())) {
                        cTracts = EdtCourseTracks.getText().toString();
                    }
                    String cDuration ="";
                    if (!TextUtils.isEmpty(EdtCourseDuration.getText())) {
                        cDuration = EdtCourseDuration.getText().toString();
                    }
                    String cMoney ="";
                    if (!TextUtils.isEmpty(EdtCourseMoney.getText())) {
                        cMoney = EdtCourseMoney.getText().toString();
                    }

                    Course course = new Course();
                    course.setCname(cName);
                    course.setCtracks(cTracts);
                    course.setCduration(cDuration);
                    course.setCmoney(cMoney);
                    realm.insertOrUpdate(course);
                    alertDialog.dismiss();
                }
            }
        });
    }
    public boolean isValidate(){
        if(TextUtils.isEmpty(EdtCourseName.getText())){
            //ToastUtils.longToast("Please input amount to expense!!");
            Toast.makeText(context, "Please Enter Course Name", Toast.LENGTH_SHORT).show();
            return false;
        }else if(TextUtils.isEmpty(EdtCourseDuration.getText())){
            //ToastUtils.longToast("Please input amount to expense!!");
            Toast.makeText(context, "Please Enter Course Duration", Toast.LENGTH_SHORT).show();
            return false;
        }else if(TextUtils.isEmpty(EdtCourseTracks.getText())){
            //ToastUtils.longToast("Please input amount to expense!!");
            Toast.makeText(context, "Please Enter Course Tracks", Toast.LENGTH_SHORT).show();
            return false;
        }else if(TextUtils.isEmpty(EdtCourseMoney.getText())){
            //ToastUtils.longToast("Please input amount to expense!!");
            Toast.makeText(context, "Please Enter Course Money", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }







}
