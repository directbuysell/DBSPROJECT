package com.rto.vehicle.info.challan.fuel.olxproject.comman;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import com.rto.vehicle.info.challan.fuel.olxproject.R;

public class AspectRatioLayout extends FrameLayout {
    private static final String TAG = "AspectRatioLayout";
    private float heightRatio;
    private float widthRatio;

    public AspectRatioLayout(Context context) {
        super(context);
        init(context, (AttributeSet) null, 0, 0);
    }

    public AspectRatioLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        init(context, attributeSet, 0, 0);
    }

    public AspectRatioLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        init(context, attributeSet, i, 0);
    }

    @SuppressLint("NewApi")
    public AspectRatioLayout(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        init(context, attributeSet, i, i2);
    }

    @SuppressLint("ResourceType")
    private void init(Context context, AttributeSet attributeSet, int i, int i2) {
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.AspectRatioLayout, i, i2);
        this.widthRatio = obtainStyledAttributes.getFloat(1, 1.0f);
        this.heightRatio = obtainStyledAttributes.getFloat(0, 1.0f);
        obtainStyledAttributes.recycle();
    }


    public void onMeasure(int i, int i2) {
        int size = MeasureSpec.getSize(i);
        int size2 = MeasureSpec.getSize(i2);
        int mode = MeasureSpec.getMode(i);
        int mode2 = MeasureSpec.getMode(i2);
        if (mode == 1073741824) {
            size2 = Math.round((this.heightRatio / this.widthRatio) * ((float) size));
            mode2 = 1073741824;
        } else if (mode2 == 1073741824) {
            size = Math.round((this.widthRatio / this.heightRatio) * ((float) size2));
            mode = 1073741824;
        } else {
            Log.w(TAG, "Width or height are not exact, so do nothing.");
        }
        super.onMeasure(MeasureSpec.makeMeasureSpec(size, mode), MeasureSpec.makeMeasureSpec(size2, mode2));
    }

    public float getWidthRatio() {
        return this.widthRatio;
    }

    public float getHeightRatio() {
        return this.heightRatio;
    }

    public float getAspectRatio() {
        return this.widthRatio / this.heightRatio;
    }

    public void setAspectRatio(float f, float f2) {
        this.widthRatio = f;
        this.heightRatio = f2;
        requestLayout();
    }
}