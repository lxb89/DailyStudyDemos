package angqin.myapplication.litepal;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;

import java.util.List;

import angqin.myapplication.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${lixuebin} on 2018/8/13.
 * 邮箱：2072301410@qq.com
 * TIP： 三方数据库  LitePal
 */

public class LitePalStudyActivity extends AppCompatActivity {
    private final String TAG = "LitePalStudyActivity";
    @Bind(R.id.act_show_db)
    AppCompatTextView actShowDb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_litepal_study);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.acb_create, R.id.acb_add, R.id.acb_delete, R.id.acb_change, R.id.acb_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.acb_create:
//                Connector.getDatabase();   //创建数据库
                Connector.getDatabase();
                break;
            case R.id.acb_add://添加数据
                addData();
                break;
            case R.id.acb_delete://删除数据
//                deleteData();
                deleteDataByCondition();
                break;
            case R.id.acb_change://更新数据
//                updateData();
                updateDataByCondition();
                break;
            case R.id.acb_search://查询数据
                queryData();
                break;
        }
    }


    private void queryData() {
        //查询 PhoneBean 表中的所有数据
        List<PhoneBean> phoneBeans = LitePal.findAll(PhoneBean.class);
        Log.i(TAG, "查询全部数据:\n" + phoneBeans.toString());
        //查询第一条数据
        PhoneBean first = LitePal.findFirst(PhoneBean.class);
        Log.e(TAG, "查询第一条数据:\n" + first.toString());
//        //查询最后一条数据
        PhoneBean last = LitePal.findLast(PhoneBean.class);
        Log.e(TAG, "查询最后一条数据:\n" + last.toString());
//        //通过select()方法查询数据，只查name / price 两列数据
//        List<PhoneBean> phoneBeans1 = LitePal.select("name", "price").find(PhoneBean.class);
//        //通过where 指定约束条件查询，价格大于2800 的手机
//        List<PhoneBean> phoneBeans2 = LitePal.where("price > ? ", "2800").find(PhoneBean.class);
//        //使用order()方法，将查询的结果按照价格由低到高排序默认是asc ,desc 则是相反
//        List<PhoneBean> phoneBeans3 = LitePal.order("order asc").find(PhoneBean.class);
//        //只查询前4 条数据
//        List<PhoneBean> phoneBeans4 = LitePal.limit(5).find(PhoneBean.class);
//        //还可通过offset() 来指定偏移量，表示查到了3/4/5 条数据
//        List<PhoneBean> phoneBeans5 = LitePal.limit(5).offset(2).find(PhoneBean.class);
//        //以上任意组合，实现复杂查询
//        LitePal.select("name","price","discount")
//                .where("select > ? ","2800")
//                .order("id")
//                .limit(5)
//                .offset(5)
//                .find(PhoneBean.class);

    }

    private void updateData() {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setId(2);
        phoneBean.setName("小米6");
        phoneBean.setPrice(2799);
        phoneBean.setBrand("小米");
        //我们先添加了一条数据，在此给对象赋值后调用save方法，
        //此时litepal会发现student对象已存储，因此不会添加一条新数据，而会更新当前的数据
        phoneBean.save();
        Log.e(TAG, "updateData:" + phoneBean.toString());

    }

    /**
     * 通过判断条件，更新数据
     */
    private void updateDataByCondition() {
        PhoneBean phoneBean = new PhoneBean();
        phoneBean.setId(2);
        phoneBean.setPrice(3000);
        phoneBean.setName("哈哈哈哈");
        phoneBean.setBrand("三星");
        phoneBean.setPrice(2000);
        //更换名字为 李四 且 手机价格为 3500
        // TODO: 2018/8/14
        phoneBean.updateAll("name = ?","李四");
//        phoneBean.updateAll();
        //异步更新数据
//        phoneBean.updateAllAsync("name= ? and price = ?","小米mix", "3999")
//                .listen(new UpdateOrDeleteCallback() {
//                    @Override
//                    public void onFinish(int rowsAffected) {
//                        Toast.makeText(LitePalStudyActivity.this,
//                                "更新操作影响了" + rowsAffected + "行数据", Toast.LENGTH_LONG).show();
//                    }
//                });
        Log.i(TAG, "updateDataByCondition:\n" + phoneBean.toString());
    }

    private void deleteData() {
        LitePal.delete(PhoneBean.class, 6);
        List<PhoneBean> phoneBeans = LitePal.findAll(PhoneBean.class);
        Log.i(TAG, "  deleteData id  查询数据:\n" + phoneBeans.toString());
    }

    /**
     * 通过判断条件，删除数据
     */
    private void deleteDataByCondition() {
        LitePal.deleteAll(PhoneBean.class, "price < ?", "2800");
        //异步删除数据
//       LitePal.deleteAllAsync(PhoneBean.class, "price < ?", "2800")
//                .listen(new UpdateOrDeleteCallback() {
//                    @Override
//                    public void onFinish(int rowsAffected) {
//                        Toast.makeText(LitePalStudyActivity.this,
//                                "删除操作影响了" + rowsAffected + "行数据", Toast.LENGTH_LONG).show();
//                    }
//                });
        List<PhoneBean> phoneBeans = LitePal.findAll(PhoneBean.class);
        Log.i(TAG, "  deleteData price < 2800 查询数据:\n" + phoneBeans.toString());
    }

    private void addData() {
        PhoneBean phone = new PhoneBean();
        phone.setId(1);
        phone.setName("小李子");
        phone.setBrand("华为");
        phone.setPrice(2799);
        phone.save();//在主线程中插入数据
        //判断数据是否存储成功
        if (phone.save()) {
            Toast.makeText(LitePalStudyActivity.this, " phone 存储成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(LitePalStudyActivity.this, " phone 存储失败", Toast.LENGTH_SHORT).show();
        }
        Log.i(TAG, "addData:\n" + phone.toString());

//        /**
//         *  当一次插入几万条数据，可能会阻塞线程，故此方法可异步操作，最后在回调中回到主线程操作UI
//         */
//        phone.saveAsync().listen(new SaveCallback() {
//            @Override
//            public void onFinish(boolean success) {
//                Toast.makeText(LitePalStudyActivity.this, "插入数据成功了！", Toast.LENGTH_LONG).show();
//            }
//        });
//
//        /**
//         *  已存在数据库就更新，没有就创建
//         */
//        phone.saveOrUpdateAsync().listen(new SaveCallback() {
//            @Override
//            public void onFinish(boolean success) {
//                Toast.makeText(LitePalStudyActivity.this, "插入数据成功了！", Toast.LENGTH_LONG).show();
//            }
//        });
//        phone.saveOrUpdate("name=?", phone.getName());
    }
}
