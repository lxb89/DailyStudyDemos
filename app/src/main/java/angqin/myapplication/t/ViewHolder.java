package angqin.myapplication.t;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 作者：${lixuebin} on 2018/1/29 14:37
 * 邮箱：2072301410@qq.com
 * tip: 适配器 ViewHolder
 */
public class ViewHolder {
    private SparseArray<View> views;
    private View convertView;

    public ViewHolder(ViewGroup parent, LayoutInflater layoutInflater, int layoutId) {
        this.views = new SparseArray<View>();
        this.convertView = layoutInflater.inflate(layoutId, parent, false);
        this.convertView.setTag(this);
    }

    /**
     * 获取viewholder
     *
     * @param parent
     * @param convertView
     * @param layoutInflater
     * @param layoutId
     * @return
     */
    public static ViewHolder getViewHolder(ViewGroup parent, View convertView, LayoutInflater layoutInflater, int layoutId) {
        if (convertView == null) {
            return new ViewHolder(parent, layoutInflater, layoutId);
        }
        return (ViewHolder) convertView.getTag();
    }

    /**
     * 得到ConvertView
     *
     * @return
     */
    public View getConverView() {
        return convertView;
    }

    /**
     * 根据id 得到view
     */
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = convertView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 根据id得到TextView
     */
    public TextView getTextView(int viewId) {
        return getView(viewId);
    }

    /**
     * 根据id得到Imagview
     */
    public ImageView getImageView(int viewId) {
        return getView(viewId);
    }

}
