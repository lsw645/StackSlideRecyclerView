package com.lxw.stacksliderecyclerview;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;
import android.view.ViewGroup;

;
/**
 * <pre>
 *     author : lxw
 *     e-mail : lsw@tairunmh.com
 *     time   : 2017/04/18
 *     desc   :
 * </pre>
 */

public class SwipeCardLayout extends LayoutManager {
//    @Override
//    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
//        return (RecyclerView.LayoutParams) new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
//                ViewGroup.LayoutParams.WRAP_CONTENT);
//    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //将View移除，放到scarp中缓存
        detachAndScrapAttachedViews(recycler);
        int bottomPosition=0;
        int childCount=getItemCount();
        if(childCount<1){
            return;
        }else if(childCount<CardConfig.MAX_SHOW_COUNT){
            bottomPosition=0;
        }else {
            bottomPosition=childCount-CardConfig.MAX_SHOW_COUNT;
        }
        for(int i=bottomPosition;i<childCount;i++){
            View child=recycler.getViewForPosition(i);
            addView(child);
            measureChildWithMargins(child,0,0);
            int width=getWidth();
            int height=getHeight();
            int widthSpace=width-getDecoratedMeasuredWidth(child);
            int heightSpace=height-getDecoratedMeasuredHeight(child);
            layoutDecorated(child,
                    widthSpace/2,
                    heightSpace/2,
                    widthSpace/2+getDecoratedMeasuredWidth(child),
                    heightSpace/2+getDecoratedMeasuredHeight(child));
                int level=childCount-i-1;

            if(level>0){
                child.setScaleX(1-level*CardConfig.SCALE_GAP);
                //页面只展现了三张图片，所以第四张图片要被第三张图片盖着
                if(level<CardConfig.MAX_SHOW_COUNT-1){
                    child.setTranslationY(CardConfig.TRANS_Y_GAP*level);
                    child.setScaleY(1-level*CardConfig.SCALE_GAP);
                }else {
                    //第四张跟上一张在Y方向位移一样，因此可以被遮盖住
                   child.setTranslationY(CardConfig.TRANS_Y_GAP*(level-1));
                    child.setScaleY(1-(level-1)*CardConfig.SCALE_GAP);
                }

            }

        }

        //super.onLayoutChildren(recycler, state);
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return generateLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT));
    }

    //    @Override
//    public .ViewGroup.LayoutParams generateDefaultLayoutParams() {
//        return  new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//    }
}
