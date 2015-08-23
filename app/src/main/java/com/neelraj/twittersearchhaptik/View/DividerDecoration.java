package com.neelraj.twittersearchhaptik.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Neel Raj on 24-08-2015.
 */
public class DividerDecoration  extends RecyclerView.ItemDecoration {

    private static final int DIVIDER_HEIGHT_DP = 1;

    private Drawable dividerDrawable;
    private static int measuredDividerHeight;
    private int[] excludedIDs;
    private boolean enablePadding = true;

    /**
     * Create a new ItemDecorator for use with a RecyclerView
     * @param context A context held temporarily to get colors and display metrics
     */
    public DividerDecoration(Context context) {
        this(context, null);
    }

    /**
     * Create an ItemDecorator for use with a RecyclerView
     * @param context A context held temporarily to get colors and display metrics
     * @param excludedLayoutIDs an array of layoutIDs to exclude adding a divider to
     *                          null to add a divider to each entry in the RecyclerView
     */
    public DividerDecoration(Context context, int[] excludedLayoutIDs){
        dividerDrawable = new ColorDrawable(Color.argb(10,0,0,0));
        measuredDividerHeight = (int) Math.ceil(DIVIDER_HEIGHT_DP * context.getResources().getDisplayMetrics().density);
        excludedIDs = excludedLayoutIDs;
    }

    /**
     * Disable adding padding between separators. This is the behavior specified in the Material Design
     * Guidelines, but touch effects can overlap the separators.
     * @return This object, for chain building
     */
    public DividerDecoration disableExtraPadding(){
        enablePadding = false;
        return this;
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        final int left = parent.getPaddingLeft();
        final int right = parent.getWidth() - parent.getPaddingRight();

        final int endIndex = parent.getChildCount();
        for (int i = 0; i < endIndex - 1; i++) { // Exclude the last item in the list
            final View child = parent.getChildAt(i);
            if (excludedIDs == null || includeView(child.getId())) {

                final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
                final int top = child.getBottom() + params.bottomMargin;
                final int bottom = top + measuredDividerHeight;

                dividerDrawable.setBounds(left, top, right, bottom);

                // Don't draw separators under the last item in a section
                if (excludedIDs == null || includeView(parent.getChildAt(i + 1).getId())) dividerDrawable.draw(c);
            }
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        if (enablePadding && (excludedIDs == null || includeView(view.getId()))){
            outRect.bottom = measuredDividerHeight;
        }
    }

    private boolean includeView(int viewId){
        for (int i : excludedIDs){
            if (viewId == i) return false;
        }
        return true;
    }
}
