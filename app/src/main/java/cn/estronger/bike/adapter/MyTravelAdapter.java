package cn.estronger.bike.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.bean.MyTravel;
import cn.estronger.bike.utils.DateFormaterUtils;

import java.util.ArrayList;

/**
 * Created by MrLv on 2016/12/21.
 */

public class MyTravelAdapter extends RecyclerView.Adapter<MyTravelAdapter.ViewHolder> {
    public ArrayList<MyTravel.DataBean.ItemsBean> datas = null;
    public View.OnClickListener listener ;
    public MyTravelAdapter(ArrayList<MyTravel.DataBean.ItemsBean> datas,View.OnClickListener listener) {
        this.datas = datas;
        this.listener = listener;
    }
    @Override
    public MyTravelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_travel_detail,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyTravelAdapter.ViewHolder holder, int position) {
        holder.tv_time.setText(DateFormaterUtils.times2(datas.get(position).getAdd_time()));
        holder.tv_bike_sn.setText(datas.get(position).getBicycle_sn());
        holder.tv_ride_time.setText(datas.get(position).getDuration()+"分钟");
        holder.tv_ride_price.setText(datas.get(position).getOrder_amount()+"元");
        holder.ll_item.setOnClickListener(listener);
        holder.ll_item.setId(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_time,tv_bike_sn,tv_ride_time,tv_ride_price;
        public LinearLayout ll_item;
        public ViewHolder(View view){
            super(view);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            tv_bike_sn = (TextView) view.findViewById(R.id.tv_bike_sn);
            tv_ride_time = (TextView) view.findViewById(R.id.tv_ride_time);
            tv_ride_price = (TextView) view.findViewById(R.id.tv_ride_price);
            ll_item = (LinearLayout) view.findViewById(R.id.ll_item);
        }
    }
}
