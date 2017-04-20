package com.lxw.stacksliderecyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRv;
    private SwipeAdapter mSwipeAdapter;
    private List<SwipeCardBean> mSwipeCardBeanList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRv = (RecyclerView) findViewById(R.id.rv);
        CardConfig.initConfig(this);
        mRv.setLayoutManager(new SwipeCardLayout());
        mSwipeCardBeanList=SwipeCardBean.initDatas();
        mSwipeAdapter=new SwipeAdapter(MainActivity.this,mSwipeCardBeanList);
        mRv.setAdapter(mSwipeAdapter);
        ItemTouchHelper itemTouchHelper=new ItemTouchHelper(new SwipeCardCallback(mRv,mSwipeCardBeanList,mSwipeAdapter));
        itemTouchHelper.attachToRecyclerView(mRv);
    }
}
