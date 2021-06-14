package com.example.corincoronacheckinowner.jshCrossDomain.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.corincoronacheckinowner.R;

public class JSHInfoEditItem extends LinearLayout {

    // Associate
        // View
        private Button button;

    // Constructor
    public JSHInfoEditItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Get Attributes
        TypedArray attributeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.jshInfoEditItem, 0, 0);
        String title = attributeArray.getString(R.styleable.jshInfoEditItem_jshInfoItem_title);
        String content = attributeArray.getString(R.styleable.jshInfoEditItem_jshInfoItem_content);

        // Inflate View
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View jshInfoAndEdit = layoutInflater.inflate(R.layout.widget_item_jsh_info_and_edit, this, false);
        this.addView(jshInfoAndEdit);

        // Associate View
        TextView titleTextView = jshInfoAndEdit.findViewById(R.id.jshInfoAndEditItem_titleTextView);
        TextView contentTextView = jshInfoAndEdit.findViewById(R.id.jshInfoAndEditItem_contentTextView);
        this.button = jshInfoAndEdit.findViewById(R.id.jshInfoAndEditItem_editButton);

        // Initialize View
        titleTextView.setText(title);
        contentTextView.setText(content);
    }

    public void setButtonOnClickListener(View.OnClickListener onClickListener){
        this.button.setOnClickListener(onClickListener);
    }
}
