package angqin.myapplication.databinding;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;

import java.util.ArrayList;
import java.util.List;

import angqin.myapplication.R;
import angqin.myapplication.databinding.adapter.MultiItemAdapter;
import angqin.myapplication.databinding.bean.GirlsItem;
import angqin.myapplication.databinding.bean.TitleItem;
import angqin.myapplication.databinding.interfaze.IBaseBindingAdapterItem;

/**
 * Created by ${lixuebin} on 2018/8/22.
 * 邮箱：2072301410@qq.com
 * TIP： RecyclerView + DataBinding 使用
 */

public class DataBindingTestThreeAty extends AppCompatActivity {
    private static final String URL_USER_PIC = "http://ww1.sinaimg.cn/large/0065oQSqly1fszxi9lmmzj30f00jdadv.jpg";
    private static final String URL_USER_PIC1 = "http://ww1.sinaimg.cn/large/0065oQSqly1fsysqszneoj30hi0pvqb7.jpg";
    private static final String URL_USER_PIC2 = "http://ww1.sinaimg.cn/large/0065oQSqly1fsq9iq8ttrj30k80q9wi4.jpg";
    private static final String URL_USER_PIC3 = "http://ww1.sinaimg.cn/large/0065oQSqly1fsvb1xduvaj30u013175p.jpg";
    private static final String URL_USER_PIC4 = "http://ww1.sinaimg.cn/large/0065oQSqly1fswhaqvnobj30sg14hka0.jpg";
    private static final String URL_USER_PIC5 = "http://ww1.sinaimg.cn/large/0065oQSqly1ft3fna1ef9j30s210skgd.jpg";
    private static final String URL_USER_PIC6 = "https://ww1.sinaimg.cn/large/0065oQSqgy1ft4kqrmb9bj30sg10fdzq.jpg";
    private List<IBaseBindingAdapterItem> mList = new ArrayList<>();
    private List<GirlsItem> mList2 = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        angqin.myapplication.databinding.ActivityDatabindingTestThreeBinding binding =
                DataBindingUtil.setContentView(this, R.layout.activity_databinding_test_three);
        //模拟数据
        initData();
        MultiItemAdapter multiItemAdapter = new MultiItemAdapter(this,mList);
//        SingleAdapter singleAdapter= new SingleAdapter(this,mList2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        binding.recyclerView.setLayoutManager(layoutManager);
        binding.recyclerView.setAdapter(multiItemAdapter);

    }

    private void initData() {
        //mList
        mList.add(new TitleItem("福利1"));
        mList.add(new GirlsItem(URL_USER_PIC, "妹子1"));
        mList.add(new GirlsItem(URL_USER_PIC1, "妹子1"));
        mList.add(new TitleItem("福利2"));
        mList.add(new TitleItem("福利3"));
        mList.add(new GirlsItem(URL_USER_PIC2, "妹子3"));
        mList.add(new TitleItem("福利4"));
        mList.add(new GirlsItem(URL_USER_PIC3, "妹子4"));
        mList.add(new TitleItem("福利5"));
        mList.add(new GirlsItem(URL_USER_PIC4, "妹子5"));
        mList.add(new TitleItem("福利6"));
        mList.add(new GirlsItem(URL_USER_PIC5, "妹子6"));
        mList.add(new TitleItem("福利7"));
        mList.add(new GirlsItem(URL_USER_PIC6, "妹子7"));
        //mList2
        mList2.add(new GirlsItem(URL_USER_PIC3, "妹子1"));
        mList2.add(new GirlsItem(URL_USER_PIC4, "妹子2"));
        mList2.add(new GirlsItem(URL_USER_PIC5, "妹子3"));
        mList2.add(new GirlsItem(URL_USER_PIC6, "妹子4"));
    }
}
