package cn.estronger.bike.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.bean.CreditCountBean;
import cn.estronger.bike.utils.DateFormaterUtils;

import java.util.ArrayList;

/**
 * Created by MrLv on 2016/12/21.
 */

public class CreditAdapter extends RecyclerView.Adapter<CreditAdapter.ViewHolder> {
    public ArrayList<CreditCountBean.DataBean.ItemsBean> datas = null;
    public CreditAdapter(ArrayList<CreditCountBean.DataBean.ItemsBean> datas) {
        this.datas = datas;
    }
    @Override
    public CreditAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_credit_count,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(CreditAdapter.ViewHolder holder, int position) {
        holder.tv_count_type.setText(datas.get(position).getPoint_desc());
        holder.tv_time.setText(DateFormaterUtils.dateTime(datas.get(position).getAdd_time()));
        holder.tv_count.setText(datas.get(position).getPoints());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_count_type;
        public TextView tv_time;
        public TextView tv_count;
        public ViewHolder(View view){
            super(view);
            tv_count_type = (TextView) view.findViewById(R.id.tv_count_type);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_count = (TextView) view.findViewById(R.id.tv_count);
        }
    }
}
