package com.lxw.stacksliderecyclerview;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;

import java.util.List;

/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2017/04/20
 *     desc   :
 * </pre>
 */

public class SwipeCardCallback extends ItemTouchHelper.SimpleCallback {
    private RecyclerView mRv;
    private List<SwipeCardBean> mDatas;
    private SwipeAdapter mSwipeAdapter;

    public SwipeCardCallback(RecyclerView rv, List<SwipeCardBean> datas, SwipeAdapter swipeAdapter) {
        super(0, ItemTouchHelper.DOWN | ItemTouchHelper.UP | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT);
        mRv = rv;
        mDatas = datas;
        mSwipeAdapter = swipeAdapter;
    }


    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        return false;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        SwipeCardBean cardBean = mDatas.remove(viewHolder.getLayoutPosition());
        mDatas.add(0, cardBean);
        mSwipeAdapter.notifyDataSetChanged();
    }

    public float getThreashold() {
        return mRv.getWidth() * 0.5f;
    }

    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
        //监听到child的变化--控制层叠View的动画效果
        double distance =Math.sqrt(dX*dX+dY*dY);
        double fraction =distance/getThreashold();
        if(fraction>1){
            fraction=1;
        }

        int childCount =recyclerView.getChildCount();
        for(int i=0;i<childCount;i++){
            View child =recyclerView.getChildAt(i);
            int level= childCount-i-1;
            if(level>0){
                child.setScaleX((float) (1-CardConfig.SCALE_GAP*level+fraction*CardConfig.SCALE_GAP));
                if(level<CardConfig.MAX_SHOW_COUNT-1){
                    child.setTranslationY((float) (CardConfig.TRANS_Y_GAP*level-fraction*CardConfig.TRANS_Y_GAP));
                    child.setScaleY((float) (1-CardConfig.SCALE_GAP*level+fraction*CardConfig.SCALE_GAP));
                }
            }

        }

    }
}
