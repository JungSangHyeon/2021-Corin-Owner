package com.example.corincoronacheckincustomer;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class JSHSelectItem extends LinearLayout {

    // Associate
        // View
        private TextView title;

    // Constructor
    public JSHSelectItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Get Attributes
        TypedArray attributeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.jshSelectItem, 0, 0);
        String title = attributeArray.getString(R.styleable.jshSelectItem_jshSelectItem_title);

        // Inflate View
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View jshToolbar = layoutInflater.inflate(R.layout.widget_item_jsh_select, this, false);
        this.addView(jshToolbar);

        // Associate View
        this.title = jshToolbar.findViewById(R.id.title);

        // Initialize View
        this.title.setText(title);
    }
}
