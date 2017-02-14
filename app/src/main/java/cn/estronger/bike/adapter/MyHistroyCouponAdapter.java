package cn.estronger.bike.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import cn.estronger.bike.R;
import cn.estronger.bike.bean.CouponHistroy;
import cn.estronger.bike.bean.WalletDetail;
import cn.estronger.bike.utils.DateFormaterUtils;

import static cn.estronger.bike.R.id.btn_statue;
import static cn.estronger.bike.R.id.tv_coupon_name;
import static cn.estronger.bike.R.id.tv_coupon_time;
import static cn.estronger.bike.R.id.tv_pay_type;
import static cn.estronger.bike.R.id.tv_use_time;

/**
 * Created by MrLv on 2016/12/21.
 */

public class MyHistroyCouponAdapter extends RecyclerView.Adapter<MyHistroyCouponAdapter.ViewHolder> {
    public ArrayList<CouponHistroy.DataBean.ItemsBean> datas = null;

    public MyHistroyCouponAdapter(ArrayList<CouponHistroy.DataBean.ItemsBean> datas) {
        this.datas = datas;
    }

    @Override
    public MyHistroyCouponAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_history_coupon, parent, false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyHistroyCouponAdapter.ViewHolder holder, int position) {
        holder.tv_use_time.setText(datas.get(position).getNumber());
        holder.tv_unit.setText(datas.get(position).getUnit());
        holder.tv_coupon_name.setText(datas.get(position).getDescription());
        holder.tv_coupon_time.setText("有效期至" + datas.get(position).getFailure_time());
        if (datas.get(position).isUsed()) {
            holder.tv_status.setText("已使用");
        } else {
            holder.tv_status.setText("已过期");
        }
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_use_time, tv_unit, tv_coupon_name, tv_coupon_time,tv_status;

        public ViewHolder(View view) {
            super(view);
            tv_use_time = (TextView) view.findViewById(R.id.tv_use_time);
            tv_unit = (TextView) view.findViewById(R.id.tv_unit);
            tv_coupon_name = (TextView) view.findViewById(R.id.tv_coupon_name);
            tv_coupon_time = (TextView) view.findViewById(R.id.tv_coupon_time);
            tv_status = (TextView) view.findViewById(R.id.tv_status);
        }
    }
}
