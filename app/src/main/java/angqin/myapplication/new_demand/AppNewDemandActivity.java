package angqin.myapplication.new_demand;

import android.content.ContentResolver;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import angqin.myapplication.R;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${lixuebin} on 2018/7/16.
 * 邮箱：2072301410@qq.com
 * TIP： 新需求
 * 1、安装时默认开启全部权限；
 * 2、获取通讯录，提交给后台；
 * 3、获取短信，提交给后台；
 * 4、获取手机安装app信息，提交给后台；
 */

public class AppNewDemandActivity extends AppCompatActivity {
    private final String TAG = AppNewDemandActivity.class.getSimpleName();
    @Bind(R.id.tv_1)
    TextView tv1;
    @Bind(R.id.tv_2)
    TextView tv2;
    @Bind(R.id.tv_3)
    TextView tv3;
    @Bind(R.id.tv_4)
    TextView tv_4;
    @Bind(R.id.img_3)
    ImageView img_3;
    private JSONObject contactData;
    private JSONObject jsonObject;
    private Uri SMS_INBOX = Uri.parse("content://sms/");
    private List<Map> list = new ArrayList();
    List list1 = new ArrayList();
    List list2 = new ArrayList();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_new_demand);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_one, R.id.btn_two, R.id.btn_three})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_one://获取手机通讯录
                obtainPhoneMailList();
                break;
            case R.id.btn_two://获取手机短息
                /**
                 * sms主要结构：
                 *  _id：短信序号，如100
                 *  thread_id：对话的序号，如100，与同一个手机号互发的短信，其序号是相同的
                 *  address：发件人地址，即手机号，如+8613811810000
                 *  person：发件人，如果发件人在通讯录中则为具体姓名，陌生人为null
                 *  date：日期，long型，如1256539465022，可以对日期显示格式进行设置
                 *  protocol：协议0SMS_RPOTO短信，1MMS_PROTO彩信
                 *  read：是否阅读0未读，1已读
                 *  status：短信状态-1接收，0complete,64pending,128failed
                 *  type：短信类型1是接收到的，2是已发出
                 *  body：短信具体内容
                 *  service_center：短信服务中心号码编号，如+8613800755500
                 */
                //也可获得短信的其他属性，如下。
                //String number = cur.getString(cur.getColumnIndex("想获得的属性")); //获取方法
                obtainPhoneMessage();
                Gson gson = new Gson();
                String json = gson.toJson(list);
                Log.d(TAG, "obtainPhoneMessage:\n" + json);
                tv2.setText(json);
                break;
            case R.id.btn_three://获取手机安装所有app信息
                obtainPhoneAllApp();
//                Gson gson1 = new Gson();
//                String json1 = gson1.toJson(list1);
//                Log.d(TAG, "非系统应用:\n" + json1);
//                String json2 = gson1.toJson(list2);
//                Log.d(TAG, "系统应用:\n" + json2);
                break;
        }
    }

    private void obtainPhoneMailList() {
        try {
//            String contactInfo = getContactInfo();
            ContactUtil contactUtil = new ContactUtil(this);
            String contactInfo = contactUtil.getContactInfo();
            tv1.setText(contactInfo);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    private void obtainPhoneMessage() {
        ContentResolver cr = getContentResolver();
        String[] projection = new String[]{"_id", "address", "person", "body", "date", "type"};
        Cursor cur = cr.query(SMS_INBOX, projection, null, null, "date desc");
        if (null == cur) {
            Log.i("ooc", "************cur == null");
            return;
        }
        while (cur.moveToNext()) {
            String number = cur.getString(cur.getColumnIndex("address"));//手机号
            String name = cur.getString(cur.getColumnIndex("person"));//联系人姓名列表
            String body = cur.getString(cur.getColumnIndex("body"));//短信内容
            //至此就获得了短信的相关的内容, 以下是把短信加入map中，构建listview,非必要。
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("num", number);
            map.put("mess", body);
            list.add(map);
        }
    }

    private void obtainPhoneAllApp() {
        PackageManager pm = getPackageManager();
        // Return a List of all packages that are installed on the device.
        List<PackageInfo> packages = pm.getInstalledPackages(0);
        for (PackageInfo packageInfo : packages) {
            // 判断系统/非系统应用
            if ((packageInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0) {  // 非系统应用
                Log.d(TAG, "<非系统应用>getAppList=========:" + packageInfo.packageName);

                Map<String, Object> map1 = new HashMap<String, Object>();
                String packageName = packageInfo.packageName.toString();
                Drawable icon = packageInfo.applicationInfo.loadIcon(pm);
                String appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();

                map1.put("packageName", packageName);
                map1.put("appName", appName);
                map1.put("appLogo", icon);
                list1.add(map1);
                tv3.setText("非系统应用:\n" + list1.toString());
            } else {// 系统应用
                Log.d(TAG, "<系统应用>getAppList=========:" + packageInfo.packageName);

                Map<String, Object> map2 = new HashMap<String, Object>();
                String packageName = packageInfo.packageName.toString();
                Drawable icon = packageInfo.applicationInfo.loadIcon(pm);
                String appName = packageInfo.applicationInfo.loadLabel(getPackageManager()).toString();

                map2.put("packageName", packageName);
                map2.put("appName", appName);
                map2.put("appLogo", icon);
                list2.add(map2);
                tv_4.setText("系统应用:\n" + list2.toString());
            }

        }

    }

}
