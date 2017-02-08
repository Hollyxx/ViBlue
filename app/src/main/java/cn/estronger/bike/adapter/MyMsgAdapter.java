package cn.estronger.bike.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import cn.estronger.bike.R;
import cn.estronger.bike.bean.MsgBean;
import cn.estronger.bike.utils.DateFormaterUtils;

import org.xutils.image.ImageOptions;
import org.xutils.x;

import java.util.ArrayList;

/**
 * Created by MrLv on 2016/12/21.
 */

public class MyMsgAdapter extends RecyclerView.Adapter<MyMsgAdapter.ViewHolder> {
    public ArrayList<MsgBean.DataBean.ItemsBean> datas = null;
    public View.OnClickListener listener ;
    ImageOptions imageOptions;
    public MyMsgAdapter(ArrayList<MsgBean.DataBean.ItemsBean> datas,View.OnClickListener listener) {
        this.datas = datas;
        this.listener = listener;
        imageOptions = new ImageOptions.Builder()
				.setLoadingDrawableId(R.mipmap.msg_empty)//加载中默认显示图片
				.setFailureDrawableId(R.mipmap.msg_empty)//加载失败后默认显示图片
				.build();
    }
    @Override
    public MyMsgAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_msg,parent,false);
        ViewHolder vh = new ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyMsgAdapter.ViewHolder holder, int position) {
        holder.tv_time.setText(DateFormaterUtils.times2(datas.get(position).getMsg_time()));
        x.image().bind(holder.iv_img,datas.get(position).getMsg_image(),imageOptions);
        holder.tv_title.setText(datas.get(position).getMsg_title());
        holder.tv_content.setText(datas.get(position).getMsg_abstract());
        holder.ll_item.setOnClickListener(listener);
        holder.ll_item.setId(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    //自定义的ViewHolder，持有每个Item的的所有界面元素
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_time,tv_title,tv_content;
        public ImageView iv_img;
        public LinearLayout ll_item;
        public ViewHolder(View view){
            super(view);
            tv_time = (TextView) view.findViewById(R.id.tv_time);
            iv_img = (ImageView) view.findViewById(R.id.iv_img);
            tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_content = (TextView) view.findViewById(R.id.tv_content);
            ll_item = (LinearLayout) view.findViewById(R.id.ll_item);
        }
    }
}
