package com.khaledodat.assessment.view.custom;

import android.content.Context;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.EditText;

public class HideHintEditText extends EditText {

    CharSequence hint;

    public HideHintEditText(Context context) {
        super(context);
        hint = this.getHint();
    }

    public HideHintEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        hint = this.getHint();
    }

    public HideHintEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        hint = this.getHint();
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        super.onFocusChanged(focused, direction, previouslyFocusedRect);

        this.setHint(focused ? "" : hint);
    }

}