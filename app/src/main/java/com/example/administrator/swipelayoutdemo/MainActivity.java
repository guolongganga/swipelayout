package com.example.administrator.swipelayoutdemo;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends Activity {

    private static final String TAG ="MainActivity";
    private RecyclerView recyclerView;
    private ArrayList<String> list=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView=findViewById(R.id.recycler_view);
        initData();
        final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        final MyAdapter myAdapter=new MyAdapter(MainActivity.this,list);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(linearLayoutManager);
        myAdapter.setOnDelListener(new MyAdapter.onSwipeListener() {
            @Override
            public void onDel(int pos) {
                if(pos>=0&&pos<list.size())
                {
                    list.remove(pos);
                    myAdapter.notifyItemRemoved(pos);
                }
            }

            @Override
            public void onTop(int pos) {
                if(pos>0&&pos<list.size())
                {
                    String str = list.get(pos);
                    Log.e(TAG, "onTop: "+str );
                    list.remove(str);
                    myAdapter.notifyItemInserted(0);
                    list.add(0, str);
                    myAdapter.notifyItemRemoved(pos + 1);
                    if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
                        recyclerView.scrollToPosition(0);
                    }

                }

            }
        });
    }

    private void initData() {
        for (int i = 0; i <30 ; i++) {
            list.add("item"+i);

        }
    }
}
