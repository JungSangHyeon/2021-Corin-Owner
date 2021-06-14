package com.example.corincoronacheckinowner.corinDomain.view.mainActivity.historyFragment.HistoryItemList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.corincoronacheckinowner.R;
import com.example.corincoronacheckinowner.corinDomain.model.HistoryPerHourEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HistoryItemListAdapter extends RecyclerView.Adapter<HistoryItemViewHolder> {

    // Working Variable
    private List<HistoryPerHourEntity> valueList;

    // Constructor
    public HistoryItemListAdapter(Map<Integer, HistoryPerHourEntity> historyEntities) {
        this.valueList = new ArrayList<>(historyEntities.values());
    }

    @Override
    public HistoryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_info_in_out, parent,false);
        return new HistoryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryItemViewHolder holder, int position) {
        holder.update(this.valueList.get(position));
    }

    @Override public int getItemCount() { return this.valueList.size(); }
}