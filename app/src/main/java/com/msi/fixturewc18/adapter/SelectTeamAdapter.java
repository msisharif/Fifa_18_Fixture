package com.hussain.fixturewc18.adapter;

import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.TextView;

import com.hussain.fixturewc18.R;

public class SelectTeamAdapter extends RecyclerView.Adapter<SelectTeamAdapter.DataObjectHolder> {

    private String[] mData;
    private static SingleClickListener sClickListener;
    private static int sSelected = -1;

    public SelectTeamAdapter(String[] mData) {
        this.mData = mData;
    }

    public String getselectedItem(View view) {
        if(sSelected!=-1){
            return mData[sSelected];
        }
        return "";
    }

    static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView mTextView;
        RadioButton mRadioButton;

        public DataObjectHolder(View itemView) {
            super(itemView);
            this.mTextView = (TextView) itemView.findViewById(R.id.single_list_item_text);
            this.mRadioButton = (RadioButton) itemView.findViewById(R.id.single_list_item_check_button);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            sSelected = getAdapterPosition();
            try {
                sClickListener.onItemClickListener(getAdapterPosition(), view);
            }catch (Exception e){

            }
        }
    }

    public void selectedItem(View v) {
        sSelected = (Integer)v.getTag();
        notifyDataSetChanged();
    }

    void setOnItemClickListener(SingleClickListener clickListener) {
        sClickListener = clickListener;
    }

    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.select_team, parent, false);

        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.mTextView.setText(mData[position]);
        holder.mRadioButton.setChecked(sSelected == position);
        holder.mRadioButton.setTag(position);
        holder.mTextView.setTag(position);
        holder.mRadioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem(v);
            }
        });
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedItem(v);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    interface SingleClickListener {
        void onItemClickListener(int position, View view);
    }

}


