package fpt.edu.layout_exercise;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

public class CustomSubmitButton extends LinearLayout {

    private String text;
    Button btnSubmit;

    public CustomSubmitButton(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        setAttributes(attrs, context);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
        requestLayout();
    }

    private void setAttributes(AttributeSet attrs, Context context) {
        inflate(context, R.layout.custom_button_submit, this);

        TypedArray typedArray = getContext().getTheme().obtainStyledAttributes(attrs, R.styleable.CustomSubmitButton, 0, 0);
        try {
            text = typedArray.getString(R.styleable.CustomSubmitButton_text);
            btnSubmit = findViewById(R.id.btnSubmit);

            if (text != null) {
                btnSubmit.setText(text);
            }
        } finally {
            typedArray.recycle();
        }
    }
}
