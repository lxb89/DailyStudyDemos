package angqin.myapplication.databinding;

import android.view.View;
import android.widget.Toast;

/**
 * Created by ${lixuebin} on 2018/8/21.
 * 邮箱：2072301410@qq.com
 * TIP： databindin 中的事件处理
 */

public class OnClickHandler {
    public void onClickFriend(View view){
        Toast.makeText(view.getContext(),"onClickFriend",Toast.LENGTH_LONG).show();
    }
}
