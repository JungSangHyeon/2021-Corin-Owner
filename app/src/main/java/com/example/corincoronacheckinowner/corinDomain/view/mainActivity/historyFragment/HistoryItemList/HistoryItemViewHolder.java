package com.example.corincoronacheckinowner.corinDomain.view.mainActivity.historyFragment.HistoryItemList;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.corincoronacheckinowner.R;
import com.example.corincoronacheckinowner.corinDomain.Constant;
import com.example.corincoronacheckinowner.corinDomain.model.HistoryPerHourEntity;
import com.google.android.material.chip.ChipGroup;

public class HistoryItemViewHolder extends RecyclerView.ViewHolder {

    // Associate
        // View
        private TextView contentTitleTextView, totalVisitorTextView, manCountTextView, womenTextView;
        private ChipGroup ageChipGroup;

    // Constructor
    public HistoryItemViewHolder(View itemView) {
        super(itemView);

        // Associate
        this.contentTitleTextView = itemView.findViewById(R.id.inAndOutInfoLayout_contentTitleTextView);
        this.totalVisitorTextView = itemView.findViewById(R.id.inAndOutInfoLayout_totalVisitorValueTextView);
        this.manCountTextView = itemView.findViewById(R.id.inAndOutInfoLayout_manVisitorValueTextView);
        this.womenTextView = itemView.findViewById(R.id.inAndOutInfoLayout_womenVisitorValueTextView);
        this.ageChipGroup = itemView.findViewById(R.id.inAndOutInfoLayout_ageChipGroup);
    }

    public void update(HistoryPerHourEntity historyPerHourEntity) {
        int hour = historyPerHourEntity.getHour();
        String hourString = (hour>12)?
                this.getString(R.string.inAndOutInfoLayout_pmText)+" "+(hour-12)+this.getString(R.string.inAndOutInfoLayout_hourText) :
                this.getString(R.string.inAndOutInfoLayout_amText)+" "+hour+this.getString(R.string.inAndOutInfoLayout_hourText);
        this.contentTitleTextView.setText(hourString);
        this.totalVisitorTextView.setText(Integer.toString(historyPerHourEntity.getTotalVisitor()));
        this.manCountTextView.setText(Integer.toString(historyPerHourEntity.getManCount()));
        this.womenTextView.setText(Integer.toString(historyPerHourEntity.getWomenCount()));
        for(int i=0; i<6; i++){
            if(historyPerHourEntity.getAgeGroupCount()[i]>historyPerHourEntity.getTotalVisitor()/4){
                this.ageChipGroup.check(Constant.AgeGroupChipIds[i]);
            }
        }
    }

    private String getString(int id) { return this.itemView.getContext().getString(id); }
}


