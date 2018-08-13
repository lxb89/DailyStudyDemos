package angqin.myapplication.enum_study;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import angqin.myapplication.R;

/**
 * Created by ${lixuebin} on 2018/7/11.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public class TitleLeftAdapter extends BaseAdapter {
    private Context context;
    private String[] titles;
    private int curPos;

    public TitleLeftAdapter(Context context, String[] strings) {
        this.context = context;
        this.titles = strings;
        setCurPos(0);
    }

    public void setCurPos(int curPos) {
        this.curPos = curPos;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public Object getItem(int position) {
        return titles[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.function_left_item, null);
            viewHolder = new ViewHolder();
            viewHolder.tv_fucname = convertView.findViewById(R.id.tv_fucname);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_fucname.setText(titles[position]);
        if (curPos == position) {
            viewHolder.tv_fucname.setSelected(true);
        } else {
            viewHolder.tv_fucname.setSelected(false);
        }
        return convertView;
    }

    static class ViewHolder {
        TextView tv_fucname;
    }
}
