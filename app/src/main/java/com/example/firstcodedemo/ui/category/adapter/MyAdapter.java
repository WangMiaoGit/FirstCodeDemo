package com.example.firstcodedemo.ui.category.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.firstcodedemo.R;
import com.example.firstcodedemo.ui.category.CategoryBean;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    private View mHeaderView;
    private List<CategoryBean> mData;
    private Context mContext;
    Category_right_item_adapter childItemAdapter;


    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
    }

    public MyAdapter(Context context, List<CategoryBean> data) {
        mContext = context;
        mData = data;
    }

    //根据pos返回不同的ItemViewType
    @Override
    public int getItemViewType(int position) {
        if (mHeaderView == null) return TYPE_NORMAL;
        if (position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    //在此根据ItemViewType来决定返回何种ViewHolder
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mHeaderView != null && viewType == TYPE_HEADER)
            return new MyViewHolder(mHeaderView);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_right, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_HEADER) return;
        final int pos = getRealPosition(holder);
        CategoryBean categoryBean = mData.get(pos);
        childItemAdapter=new Category_right_item_adapter(categoryBean.categoryList);
        childItemAdapter.setOnItemChildLongClickListener(new BaseQuickAdapter.OnItemChildLongClickListener() {
            @Override
            public boolean onItemChildLongClick(BaseQuickAdapter adapter, View view, int position) {
                CategoryBean.Category item = (CategoryBean.Category)adapter.getItem(position);
                System.out.println("点击到了"+item.title);
                return false;
            }
        });

        final String data = categoryBean.type;
        if(holder instanceof MyViewHolder) {
            ((MyViewHolder) holder).mTvItem.setText(data);
            ((MyViewHolder) holder).mRecycleView.setAdapter(childItemAdapter);
        }
    }

    private int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    //返回正确的item个数
    @Override
    public int getItemCount() {
        return mHeaderView == null ? mData.size() : mData.size() + 1;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {


        TextView mTvItem;
        RecyclerView mRecycleView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mTvItem = itemView.findViewById(R.id.category_list_title);
            mRecycleView = itemView.findViewById(R.id.category_child_item_rv);
            if(itemView == mHeaderView)
                return;
        }
    }
}
