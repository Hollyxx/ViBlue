package cn.estronger.bike.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.bean.SearchHistorysBean;

import java.util.ArrayList;

/**
 * Created by MrLv on 2016/12/21.
 */

public class AddressHistoryAdapter extends RecyclerView.Adapter<AddressHistoryAdapter.ViewHolder> implements View.OnClickListener{
    public ArrayList<SearchHistorysBean> datas;
    private Context context;

    private OnRecyclerViewItemClickListener mOnItemClickListener = null;

    //define interface
    public static interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, SearchHistorysBean data);
    }

    public AddressHistoryAdapter(Context context, ArrayList<SearchHistorysBean> datas) {
        this.datas = datas;
        this.context = context;
    }

    @Override
    public AddressHistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_seach_history, parent, false);
        ViewHolder vh = new ViewHolder(view);
        //将创建的View注册点击事件
        view.setOnClickListener(this);
        return vh;
    }

    @Override
    public void onBindViewHolder(AddressHistoryAdapter.ViewHolder holder, int position) {
        holder.tv_poi.setText(datas.get(position).getName());
        holder.tv_addr.setText(datas.get(position).getAddress());
        //将数据保存在itemView的Tag中，以便点击时进行获取
        holder.itemView.setTag(datas.get(position));
    }

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取数据
            mOnItemClickListener.onItemClick(v,(SearchHistorysBean)v.getTag());
        }
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


        @Override
    public int getItemCount() {
        return datas==null?0:datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_poi;
        public TextView tv_addr;

        public ViewHolder(View view) {
            super(view);
            tv_poi = (TextView) view.findViewById(R.id.tv_poi);
            tv_addr = (TextView) view.findViewById(R.id.tv_addr);
        }
    }
}
