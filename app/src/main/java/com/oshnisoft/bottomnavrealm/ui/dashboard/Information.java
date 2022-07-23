package com.oshnisoft.bottomnavrealm.ui.dashboard;


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

public class Information extends RealmObject implements IItem<Information, Information.ViewHolder> {


    private String name;
    private String phone_number;

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
    public Information withTag(Object tag) {
        this.tag = tag;
        return this;
    }

    @Override
    public boolean isEnabled() {
        return isEnabled;
    }

    @Override
    public Information withEnabled(boolean enabled) {
        this.isEnabled = enabled;
        return this;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public Information withSetSelected(boolean selected) {
        isSelected = selected;
        return this;
    }


    @Override
    public boolean isSelectable() {
        return isSelectable;
    }

    @Override
    public Information withSelectable(boolean selectable) {
        this.isSelectable = selectable;
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public int getType() {
        return R.id.revList;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.item_information;
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
        holder.txtName.setText(TextUtils.isEmpty(name) ? "":name);
        holder.phoneNumber.setText(phone_number);
    }

    @Override
    public void unbindView(ViewHolder holder) {

        holder.txtName.setText(null);
        holder.phoneNumber.setText(null);

    }

    @Override
    public boolean equals(int code) {
        return false;
    }

    @Override
    public Information withIdentifier(long identifier) {
        return this;
    }

    @Override
    public long getIdentifier() {
        return UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{


        TextView txtName;
        TextView phoneNumber;
        public ViewHolder(View itemView) {
            super(itemView);

            txtName                                             = itemView.findViewById(R.id.txtName);
            phoneNumber                                           = itemView.findViewById(R.id.txtPhoneNumber);

        }
    }
}
