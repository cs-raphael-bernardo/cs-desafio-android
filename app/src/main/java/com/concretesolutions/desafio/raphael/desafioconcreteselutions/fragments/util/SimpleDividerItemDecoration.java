package com.concretesolutions.desafio.raphael.desafioconcreteselutions.fragments.util;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.concretesolutions.desafio.raphael.desafioconcreteselutions.R;

/**
 * Created by Raphael on 11/10/2016.
 */
public class SimpleDividerItemDecoration extends RecyclerView.ItemDecoration{
    private Drawable mDivider;

    public SimpleDividerItemDecoration(Resources resources) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            mDivider = resources.getDrawable(R.drawable.line_divider, null);
        } else {
            mDivider = resources.getDrawable(R.drawable.line_divider);
        }
    }

    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int left = parent.getPaddingLeft();
        int right = parent.getWidth() - parent.getPaddingRight();

        int childCount = parent.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();

            int top = child.getBottom() + params.bottomMargin;
            int bottom = top + mDivider.getIntrinsicHeight();

            mDivider.setBounds(left, top, right, bottom);
            mDivider.draw(c);
        }
    }
}
