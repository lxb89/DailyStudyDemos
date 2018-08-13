package angqin.myapplication.t;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import angqin.myapplication.R;

/**
 * 作者：${lixuebin} on 2018/1/29 15:58
 * 邮箱：2072301410@qq.com
 *  TIP: 样板代码
 */
public class TDemoAdapter extends EasyBaseAdapter<TBean> {

    public TDemoAdapter(Context context, int layoutId, List list) {
        super(context, layoutId, list);
    }

    @Override
    protected void getConvert(ViewHolder viewHolder, TBean o,int position) {
        TextView textView = viewHolder.getTextView(R.id.tv_name);
        textView.setText(o.getName()+ position);
        ImageView imageView = viewHolder.getImageView(R.id.img);
        imageView.setBackgroundResource(o.getDrawable());
    }
}
