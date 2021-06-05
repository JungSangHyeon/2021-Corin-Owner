package com.example.corincoronacheckincustomer;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class JSHInfoEditItem extends LinearLayout {

    // Associate
        // View
        private Button button;

    // Constructor
    public JSHInfoEditItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Get Attributes
        TypedArray attributeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.JSHInfoEditItem, 0, 0);
        String title = attributeArray.getString(R.styleable.JSHInfoEditItem_JSHInfoItem_title);
        String content = attributeArray.getString(R.styleable.JSHInfoEditItem_JSHInfoItem_content);

        // Inflate View
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View jshToolbar = layoutInflater.inflate(R.layout.widget_item_info_and_edit, this, false);
        this.addView(jshToolbar);

        // Associate View
        TextView titleTextView = jshToolbar.findViewById(R.id.title);
        TextView contentTextView = jshToolbar.findViewById(R.id.content);
        this.button = jshToolbar.findViewById(R.id.button);

        // Initialize View
        titleTextView.setText(title);
        contentTextView.setText(content);
    }

    public void setButtonOnClickListener(View.OnClickListener onClickListener){
        this.button.setOnClickListener(onClickListener);
    }
}
