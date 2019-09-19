package com.example.administrator.swipelayoutdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/9/19.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.FullDelDemoVH> {
    private Context mContext;
    private LayoutInflater mInfalter;
    private List<String> mDatas;

    public MyAdapter(Context context, List<String> mDatas) {
        mContext = context;
        mInfalter = LayoutInflater.from(context);
        this.mDatas = mDatas;
    }

    @Override
    public FullDelDemoVH onCreateViewHolder(ViewGroup parent, int viewType) {



        return new FullDelDemoVH(mInfalter.inflate(R.layout.item,parent,false));
    }

    @Override
    public void onBindViewHolder(final FullDelDemoVH holder, int position) {

        holder.content.setText(mDatas.get(position));
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mOnSwipeListener) {
                    //如果删除时，不使用mAdapter.notifyItemRemoved(pos)，则删除没有动画效果，
                    //且如果想让侧滑菜单同时关闭，需要同时调用 ((CstSwipeDelMenu) holder.itemView).quickClose();
                    //((CstSwipeDelMenu) holder.itemView).quickClose();
                    mOnSwipeListener.onDel(holder.getAdapterPosition());
                }
            }
        });
        //置顶：
        holder.btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null!=mOnSwipeListener){
                    mOnSwipeListener.onTop(holder.getAdapterPosition());
                }

            }
        });

    }




//        //注意事项，设置item点击，不能对整个holder.itemView设置咯，只能对第一个子View，即原来的content设置，这算是局限性吧。
//        (holder.content).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "onClick:" + mDatas.get(holder.getAdapterPosition()).name, Toast.LENGTH_SHORT).show();
//                Log.d("TAG", "onClick() called with: v = [" + v + "]");
//            }
//        });

   // }

    @Override
    public int getItemCount() {
        return null != mDatas ? mDatas.size() : 0;
    }

    /**
     * 和Activity通信的接口
     */
    public interface onSwipeListener {
        void onDel(int pos);

        void onTop(int pos);
    }

    private onSwipeListener mOnSwipeListener;

    public onSwipeListener getOnDelListener() {
        return mOnSwipeListener;
    }

    public void setOnDelListener(onSwipeListener mOnDelListener) {
        this.mOnSwipeListener = mOnDelListener;
    }

    class FullDelDemoVH extends RecyclerView.ViewHolder {
        TextView content;
        Button btnDelete;
        Button btnUnRead;
        Button btnTop;

        public FullDelDemoVH(View itemView) {
            super(itemView);
            content = (TextView) itemView.findViewById(R.id.content);
            btnDelete = (Button) itemView.findViewById(R.id.btnDelete);
            //btnUnRead = (Button) itemView.findViewById(R.id.btnUnRead);
            btnTop = (Button) itemView.findViewById(R.id.btnTop);
        }
    }
}
