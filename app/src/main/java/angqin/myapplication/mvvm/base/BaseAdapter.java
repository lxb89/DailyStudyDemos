package angqin.myapplication.mvvm.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ${lixuebin} on 2018/8/23.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public abstract class BaseAdapter<T, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {
    public Context mContext;
    public List<T> mList;
    public LayoutInflater inflater;

    public BaseAdapter(Context mContext) {
        this.mContext = mContext;
        this.mList = new ArrayList<>();
        inflater = (LayoutInflater) mContext.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * 创建 ViewHolder
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onCreateVH(parent, viewType);
    }

    protected abstract VH onCreateVH(ViewGroup parent, int viewType);

    /**
     * 绑定 ViewHolder
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull VH holder, int position) {
        onBindVH(holder, position);

    }

    protected abstract void onBindVH(VH holder, int position);

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    /**
     * 刷新数据
     * @param data
     */
    public void refreshData(List<T> data){
        mList.clear();
        mList.addAll(data);
        notifyDataSetChanged();
    }

    /**
     *  加载更多
     * @param data
     */
    public void loadMoreData(List<T> data){
        mList.addAll(data);
        notifyDataSetChanged();
    }
}
