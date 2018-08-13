package angqin.myapplication.t;

import android.graphics.drawable.Drawable;

/**
 * 作者：${lixuebin} on 2018/1/29 16:10
 * 邮箱：2072301410@qq.com
 */
public class TBean {
    private String name;
    private int drawable;

    public TBean(String name, int drawable) {
        this.name = name;
        this.drawable = drawable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }
}
