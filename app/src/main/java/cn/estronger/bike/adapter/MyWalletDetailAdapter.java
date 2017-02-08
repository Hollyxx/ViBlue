package cn.estronger.bike.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.bean.WalletDetail;
import cn.estronger.bike.utils.DateFormaterUtils;

import java.util.ArrayList;

/**
 * Created by MrLv on 2016/12/21.
 */

public class MyWalletDetailAdapter extends RecyclerView.Adapter<MyWalletDetailAdapter.ViewHolder> {
    public ArrayList<WalletDetail.DataBean.ItemsBean> datas = null;
    public MyWalletDetailAdapter(ArrayList<WalletDetail.DataBean.ItemsBean> datas) {
        this.datas = datas;
    }
    @Override
    public MyWalletDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_wallet_detail,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyWalletDetailAdapter.ViewHolder holder, int position) {
        holder.tv_type.setText(datas.get(position).getDeposit_type());
        holder.tv_time.setText(DateFormaterUtils.dateTime(datas.get(position).getPdl_add_time()));
        holder.tv_price.setText(datas.get(position).getPdl_available_amount());
        holder.tv_pay_type.setText(datas.get(position).getPdl_payment_name());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_type,tv_time,tv_price,tv_pay_type;
        public ViewHolder(View view){
            super(view);
            tv_type = (TextView) view.findViewById(R.id.tv_type);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_price = (TextView) view.findViewById(R.id.tv_price);
            tv_pay_type = (TextView) view.findViewById(R.id.tv_pay_type);
        }
    }
}
