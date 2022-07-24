package com.oshnisoft.bottomnavrealm.ui.course;


import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.mikepenz.fastadapter.IItem;
import com.oshnisoft.bottomnavrealm.R;

import java.util.Collections;
import java.util.List;
import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;


/**
 * Created by monir.sobuj on 6/8/17.
 */

public class Course extends RealmObject implements IItem<Course, Course.ViewHolder> {


    private String cname;
    private String cduration;
    private String ctracks;
    private String cmoney;

    @Ignore
    private Object tag;// defines if this item is isSelectable
    @Ignore
    private boolean isSelectable = true;
    @Ignore
    private boolean isEnabled = true;
    @Ignore
    private boolean isSelected = false; // defines if the item is selected



    @Override
    public Object getTag() {
        return tag;
    }

    @Override
    public Course withTag(Object tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public Course withEnabled(boolean enabled) {
        this.isEnabled = enabled;
        return this;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public Course withSetSelected(boolean selected) {
        isSelected = selected;
        return this;
    }


    @Override
    public boolean isSelectable() {
        return isSelectable;
    }

    @Override
    public Course withSelectable(boolean selectable) {
        this.isSelectable = selectable;
        return this;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public String getCduration() {
        return cduration;
    }

    public void setCduration(String cduration) {
        this.cduration = cduration;
    }

    public String getCtracks() {
        return ctracks;
    }

    public void setCtracks(String ctracks) {
        this.ctracks = ctracks;
    }

    public String getCmoney() {
        return cmoney;
    }

    public void setCmoney(String cmoney) {
        this.cmoney = cmoney;
    }

    @Override
    public int getType() {
        return R.id.revList;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_course;
    }

    @Override
    public View generateView(Context ctx) {
        ViewHolder viewHolder                           = getViewHolder(LayoutInflater.from(ctx).inflate(getLayoutRes(), null, false));
        bindView(viewHolder, Collections.EMPTY_LIST);
        return viewHolder.itemView;
    }

    @Override
    public View generateView(Context ctx, ViewGroup parent) {
        ViewHolder viewHolder                           = getViewHolder(LayoutInflater.from(ctx).inflate(getLayoutRes(), parent, false));
        bindView(viewHolder, Collections.EMPTY_LIST);
        return viewHolder.itemView;
    }

    private ViewHolder getViewHolder(View view) {
        return new ViewHolder(view);
    }

    @Override
    public ViewHolder getViewHolder(ViewGroup parent) {
        return getViewHolder(LayoutInflater.from(parent.getContext()).inflate(getLayoutRes(), parent, false));
    }

    @Override
    public void bindView(ViewHolder holder, List<Object> payloads) {
        Context ctx = holder.itemView.getContext();

        //String time = "Total Order BDT. " + StringUtils.commaSeparatedDecimal(total_order);

        holder.courseName.setText(TextUtils.isEmpty(cname) ? "":"Course Name: "+cname);
        holder.courseDuration.setText(TextUtils.isEmpty(cduration) ? "":"Course Duration: "+cduration+" Month's");
        holder.courseTracks.setText("Course Tracks: "+ctracks);
        holder.courseMoney.setText("Cost: "+cmoney+" BDT.");
        //holder.phoneNumber.setText(phone_number);
    }

    @Override
    public void unbindView(ViewHolder holder) {

        holder.courseName.setText(null);
        holder.courseDuration.setText(null);
        holder.courseTracks.setText(null);
        holder.courseMoney.setText(null);
        //holder.phoneNumber.setText(null);

    }

    @Override
    public boolean equals(int code) {
        return false;
    }

    @Override
    public Course withIdentifier(long identifier) {
        return this;
    }

    @Override
    public long getIdentifier() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{


        TextView courseName;
        TextView courseDuration;
        TextView courseTracks;
        TextView courseMoney;
        //TextView phoneNumber;
        public ViewHolder(View itemView) {
            super(itemView);

            courseName                                             = itemView.findViewById(R.id.CourseName);
            courseDuration                                         = itemView.findViewById(R.id.CourseDuration);
            courseTracks                                             = itemView.findViewById(R.id.CourseTracks);
            courseMoney                                             = itemView.findViewById(R.id.CourseMoney);
            //phoneNumber                                           = itemView.findViewById(R.id.txtPhoneNumber);

        }
    }
}
