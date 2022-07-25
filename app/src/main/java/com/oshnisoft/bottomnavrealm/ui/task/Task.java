package com.oshnisoft.bottomnavrealm.ui.task;


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

public class Task extends RealmObject implements IItem<Task, Task.ViewHolder> {


    private String name;
    private String location;
    private String time;
    private String date;
    private String tasks;

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
    public Task withTag(Object tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public Task withEnabled(boolean enabled) {
        this.isEnabled = enabled;
        return this;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public Task withSetSelected(boolean selected) {
        isSelected = selected;
        return this;
    }


    @Override
    public boolean isSelectable() {
        return isSelectable;
    }

    @Override
    public Task withSelectable(boolean selectable) {
        this.isSelectable = selectable;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTasks() {
        return tasks;
    }

    public void setTasks(String tasks) {
        this.tasks = tasks;
    }

    @Override
    public int getType() {
        return R.id.revList;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_new_task;
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

        holder.txtName.setText(TextUtils.isEmpty(name) ? "":name);
        holder.txtLocation.setText(location);
        holder.txtTime.setText(time+" Hours");
        holder.txtDate.setText(date);
        holder.txtTask.setText(tasks);
        //holder.phoneNumber.setText(phone_number);
    }

    @Override
    public void unbindView(ViewHolder holder) {

        holder.txtName.setText(null);
        holder.txtLocation.setText(null);
        holder.txtTime.setText(null);
        holder.txtDate.setText(null);
        holder.txtTask.setText(null);
        //holder.phoneNumber.setText(null);

    }

    @Override
    public boolean equals(int code) {
        return false;
    }

    @Override
    public Task withIdentifier(long identifier) {
        return this;
    }

    @Override
    public long getIdentifier() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{


        TextView txtName;
        TextView txtLocation;
        TextView txtDate;
        TextView txtTime;
        TextView txtTask;
        //TextView phoneNumber;
        public ViewHolder(View itemView) {
            super(itemView);

            txtName                                             = itemView.findViewById(R.id.txtName);
            txtLocation                                         = itemView.findViewById(R.id.txtLocation);
            txtTime                                             = itemView.findViewById(R.id.txtTime);
            txtDate                                             = itemView.findViewById(R.id.txtDate);
            txtTask                                             = itemView.findViewById(R.id.txtTaskName);
            //phoneNumber                                           = itemView.findViewById(R.id.txtPhoneNumber);

        }
    }
}
