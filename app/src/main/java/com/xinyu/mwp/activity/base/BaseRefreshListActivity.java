package com.xinyu.mwp.activity.base;

import android.view.View;

import com.xinyu.mwp.adapter.IListAdapter;
import com.xinyu.mwp.listener.OnRefreshPageListener;

/**
 * 下拉与加载更多列表Activity
 * Created by yaowang on 16/3/31.
 */
public abstract  class BaseRefreshListActivity<TView extends View,TModel > extends BaseRefreshActivity {


    @Override
    public RefreshListController<TView,TModel> getRefreshController() {
        return (RefreshListController<TView,TModel>)super.getRefreshController();
    }

    /**
     * 设置分页刷新监听 自动启用分页模式
     * @param onRefreshPageListener
     */
    public void setOnRefreshPageListener(OnRefreshPageListener onRefreshPageListener) {
        getRefreshController().setOnRefreshPageListener(onRefreshPageListener);
    }

    /**
     * 创建适配器
     * @return
     */
    protected abstract IListAdapter<TModel> createAdapter();
}
