package com.example.corincoronacheckinowner.corinDomain.view.mainActivity.historyFragment;

import android.app.DatePickerDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.corincoronacheckinowner.R;
import com.example.corincoronacheckinowner.corinDomain.Constant;
import com.example.corincoronacheckinowner.corinDomain.model.CorinEntity;
import com.example.corincoronacheckinowner.corinDomain.model.HistoryEntity;
import com.example.corincoronacheckinowner.corinDomain.model.HistoryPerHourEntity;
import com.example.corincoronacheckinowner.corinDomain.view.mainActivity.historyFragment.HistoryItemList.HistoryItemListAdapter;
import com.example.corincoronacheckinowner.corinDomain.tech.POI;
import com.example.corincoronacheckinowner.jshCrossDomain.view.fragment.JSHViewModelFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Calendar;
import java.util.Map;
import java.util.TreeMap;

public class HistoryFragment extends JSHViewModelFragment<CorinEntity> {

    // Working Variable
    private int year, month, date;

    // Associate
        // View
        private TextView dateTextView, noDataTextView;
        private ImageView dateSelectImageView;
        private RecyclerView historyItemList;
        private FloatingActionButton shareButton;

    @Override protected int getLayoutId() { return R.layout.fragment_history; }

    @Override
    protected void associate(View view) {
        this.dateTextView = view.findViewById(R.id.historyFragment_dateTextView);
        this.noDataTextView = view.findViewById(R.id.historyFragment_noDataTextView);
        this.dateSelectImageView = view.findViewById(R.id.historyFragment_selectDateImageView);
        this.historyItemList = view.findViewById(R.id.historyFragment_historyItemList);
        this.shareButton = view.findViewById(R.id.historyFragment_shareButton);
    }

    @Override
    protected void initialize() {
        Calendar calendar = Calendar.getInstance();
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH);
        this.date = calendar.get(Calendar.DATE);

        this.dateSelectImageView.setOnClickListener(v->this.changeDateAndUpdateList());
        this.shareButton.setOnClickListener(v->this.share());
    }

    @Override protected Class getEntityClass() { return CorinEntity.class; }
    @Override protected void onModelUpdate() {
        this.updateList(Calendar.getInstance());
    }

    private void changeDateAndUpdateList() {
        Calendar calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                this.getContext(),
                (view, year, monthOfYear, dayOfMonth) -> {
                    this.year = year;
                    this.month = monthOfYear;
                    this.date = dayOfMonth;
                    calendar.set(Calendar.YEAR, year);
                    calendar.set(Calendar.MONTH, monthOfYear);
                    calendar.set(Calendar.DATE, dayOfMonth);
                    this.updateList(calendar);
                },
                this.year, this.month, this.date
        );
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());
        datePickerDialog.show();
    }

    private void updateList(Calendar calendar) {
        this.dateTextView.setText(this.getDateString(calendar.getTimeInMillis()));
        Map<Integer, HistoryPerHourEntity> map = new TreeMap<>();
         for(HistoryEntity historyEntity : this.entity.getHistoryEntities()) { // 소규모용. 전환시 모델 구조부터 바꿀 것
            if (this.getDateString(calendar.getTimeInMillis()).equals(this.getDateString(historyEntity.getInTime()))) {
                Calendar inTimeCalendar = Calendar.getInstance();
                inTimeCalendar.setTimeInMillis(historyEntity.getInTime());
                int hour = inTimeCalendar.get(Calendar.HOUR_OF_DAY);
                HistoryPerHourEntity historyPerHourEntity = map.getOrDefault(hour, new HistoryPerHourEntity(hour));
                historyPerHourEntity.upTotalVisitor();
                switch (historyEntity.getGender()) {
                    case eMan: historyPerHourEntity.upManCount();break;
                    case eWomen: historyPerHourEntity.upWomenCount();break;
                }
                if (historyEntity.getAge() < 70) historyPerHourEntity.getAgeGroupCount()[historyEntity.getAge() / 10 - 1] += 1;
                map.put(hour, historyPerHourEntity);
            }
        }
        this.noDataTextView.setVisibility(map.size()!=0? View.GONE:View.VISIBLE);
        this.historyItemList.setAdapter(new HistoryItemListAdapter(map));
        this.historyItemList.invalidate();
    }

    private void share() {
        POI.xlsWiter(this.getContext(), this.entity.getHistoryEntities());
    }

    private String getDateString(long time){ return Constant.DateFormat.format(time); }
}