package cn.estronger.bike.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.services.core.AMapException;
import com.amap.api.services.help.Inputtips;
import com.amap.api.services.help.Inputtips.InputtipsListener;
import com.amap.api.services.help.Tip;

import cn.estronger.bike.R;
import cn.estronger.bike.adapter.AddressHistoryAdapter;
import cn.estronger.bike.adapter.AddressSelectAdapter;
import cn.estronger.bike.application.SysApplication;
import cn.estronger.bike.bean.SearchHistorysBean;
import cn.estronger.bike.db.SearchHistorysDao;
import cn.estronger.bike.utils.ToastUtils;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.tools.ViewTools;

import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MrLv on 2016/12/17.
 */

public class SeachActivity extends BaseActivity implements TextWatcher, InputtipsListener {
    @ViewInject(R.id.tv_location)
    TextView tv_location;
    @ViewInject(R.id.et_addr)
    EditText et_addr;
    @ViewInject(R.id.xrv_list)
    XRecyclerView xrv_list;
    @ViewInject(R.id.xrv_list_history)
    XRecyclerView xrv_list_history;
    private Inputtips inputTips;
    private ArrayList<SearchHistorysBean> list;
    private AddressSelectAdapter adapter;
    private AddressHistoryAdapter hAdapter;
    private SearchHistorysDao dao;
    private ArrayList<SearchHistorysBean> historywordsList = new ArrayList<SearchHistorysBean>();
    @ViewInject(R.id.view_header)
    LinearLayout view_header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seach);
        x.view().inject(this);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {//判断Android版本是否大于4.4
            setHeightRelativeLayout(view_header);
        }
        init();
    }

    private void init() {
        SysApplication.getInstance().addActivity(this);
        dao = new SearchHistorysDao(this);
        historywordsList = dao.findAll();//查询所有的数据
        tv_location.setText(this.getIntent().getStringExtra("addr"));
        et_addr.addTextChangedListener(this);
        inputTips = new Inputtips(this, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this);
        layoutManager2.setOrientation(LinearLayoutManager.VERTICAL);
        xrv_list.setLayoutManager(layoutManager);
        xrv_list_history.setLayoutManager(layoutManager2);
        xrv_list.setLoadingMoreEnabled(false);
        xrv_list.setPullRefreshEnabled(false);
        //判断要不要显示 footer
        xrv_list_history.setLoadingMoreEnabled(historywordsList.size()!=0);
        xrv_list_history.setPullRefreshEnabled(false);
        View foot =   LayoutInflater.from(this).inflate(R.layout.listview_foot,null);
        xrv_list_history.setFootView(foot);
        foot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dao.deleteAll();
                xrv_list_history.setLoadingMoreEnabled(false);
                historywordsList.clear();
                hAdapter.notifyDataSetChanged();
            }
        });
        hAdapter = new AddressHistoryAdapter(this, historywordsList);
        xrv_list_history.setAdapter(hAdapter);



        hAdapter.setOnItemClickListener(new AddressHistoryAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, SearchHistorysBean data) {
                // 存储数据
                dao.addOrUpdate(data.getName(), data.getAddress(), data.getLng(), data.getLat());
                //返回到上一页
                Intent intent = new Intent();
                intent.putExtra("entity", data);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Event(value = {R.id.tv_cancel, R.id.rl_clear})
    private void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.rl_clear:
                dao.deleteAll();
                historywordsList.clear();
                hAdapter.notifyDataSetChanged();
                break;
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (!TextUtils.isEmpty(et_addr.getText().toString().trim())) {
            ViewTools.setVisible(this, R.id.xrv_list);
            ViewTools.setGone(this, R.id.ll_history);
            String tip = et_addr.getText().toString().trim();
            try {
                inputTips.requestInputtips(tip, null);
            } catch (AMapException e) {
                e.printStackTrace();
            }
        } else {
            ViewTools.setVisible(this, R.id.ll_history);
            ViewTools.setGone(this, R.id.xrv_list);
        }
    }

    @Override
    public void onGetInputtips(List<Tip> l, int i) {
        if (i == 1000) {//1000为成功
            list = new ArrayList<SearchHistorysBean>();
            for (int j = 0; j < l.size(); j++) {
                Tip tip = l.get(j);
                String name = tip.getName();
                String address = tip.getDistrict();
                double lat = tip.getPoint().getLatitude();
                double lng = tip.getPoint().getLongitude();
                SearchHistorysBean entity = new SearchHistorysBean();
                entity.setAddress(address);
                entity.setName(name);
                entity.setLat(lat);
                entity.setLng(lng);
                list.add(entity);
            }
        } else {
            ToastUtils.showShort(SeachActivity.this, "未搜到任何结果，请重新输入");
        }
        adapter = new AddressSelectAdapter(this, list);
        xrv_list.setAdapter(adapter);
        adapter.setOnItemClickListener(new AddressSelectAdapter.OnRecyclerViewItemClickListener() {
            @Override
            public void onItemClick(View view, SearchHistorysBean data) {
                if (historywordsList.size()==20||historywordsList.size()>20){//数据库只存20条
                    dao.deleteOne();
                }
                //  1  存入数据库
                // 存储数据
                dao.addOrUpdate(data.getName(), data.getAddress(), data.getLng(), data.getLat());
                //  2  返回到上一页
                Intent intent = new Intent();
                intent.putExtra("entity", data);
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        adapter.notifyDataSetChanged();
    }
}
