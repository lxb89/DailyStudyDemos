package angqin.myapplication.t;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：${lixuebin} on 2018/1/29 10:54
 * 邮箱：2072301410@qq.com
 * TIP: 泛型使用(继承自BaseAdapter 封装的Adapter)
 */
public abstract class EasyBaseAdapter<T> extends BaseAdapter {
    protected LayoutInflater inflater;
    private int layoutId;
    protected List<T> mList = new ArrayList<>();

    public EasyBaseAdapter(Context context, int layoutId, List<T> list) {
        super();
        this.inflater = LayoutInflater.from(context);
        this.layoutId = layoutId;
        this.mList = list;
    }

    @Override
    public int getCount() {
        return mList.size() == 0 ? 0 : mList.size();
    }

    @Override
    public T getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = ViewHolder.getViewHolder(viewGroup, convertView, inflater, layoutId);
        getConvert(viewHolder, mList.get(position),position);
        return viewHolder.getConverView();
    }

    protected abstract void getConvert(ViewHolder viewHolder, T t,int pos);

}
