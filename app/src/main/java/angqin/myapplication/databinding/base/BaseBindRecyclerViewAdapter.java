package angqin.myapplication.databinding.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import angqin.myapplication.databinding.ItemGrilsBinding;
import angqin.myapplication.databinding.ItemTitleBinding;

/**
 * Created by ${lixuebin} on 2018/8/22.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public abstract class BaseBindRecyclerViewAdapter<T> extends RecyclerView.Adapter {
    public List<T> mList;
    public final LayoutInflater inflater;

    public BaseBindRecyclerViewAdapter(Context context,List<T> mList) {
        this.mList = mList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return onCreateMyViewHolder(parent,viewType);
    }

    protected abstract RecyclerView.ViewHolder onCreateMyViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        onBindMyViewHolder(holder, position);

    }

    protected abstract void onBindMyViewHolder(RecyclerView.ViewHolder holder, int position);

    @Override
    public int getItemCount() {
        return mList.size();
    }

}
