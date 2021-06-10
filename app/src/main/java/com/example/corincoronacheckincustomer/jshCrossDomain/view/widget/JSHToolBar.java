package com.example.corincoronacheckincustomer.jshCrossDomain.view.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.corincoronacheckincustomer.R;

public class JSHToolBar extends LinearLayout {

    // Associate
        // View
        private ImageView button;
        private TextView title;

    // Constructor
    public JSHToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Get Attributes
        TypedArray attributeArray = context.getTheme().obtainStyledAttributes(attrs, R.styleable.jshToolBar, 0, 0);
        String title = attributeArray.getString(R.styleable.jshToolBar_jshToolBar_title);
        int buttonImageSource = attributeArray.getResourceId(R.styleable.jshToolBar_jshToolBar_button_image, 0);

        // Inflate View
        LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View jshToolbar = layoutInflater.inflate(R.layout.widget_jsh_toolbar, this, false);
        this.addView(jshToolbar);

        // Associate View
        this.button = jshToolbar.findViewById(R.id.jshToolBar_buttonImageView);
        this.title = jshToolbar.findViewById(R.id.jshToolbar_titleTextView);

        // Initialize View
        this.button.setImageResource(buttonImageSource);
        this.title.setText(title);
    }

    public void setToolBarOnClickListener(OnClickListener listener){
        this.button.setOnClickListener(listener);
    }
}
