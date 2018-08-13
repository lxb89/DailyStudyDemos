package angqin.myapplication.rxjava_retroft;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import angqin.myapplication.R;
import angqin.myapplication.rxjava_retroft.config.HttpConfig;
import angqin.myapplication.rxjava_retroft.entity.MoviceEntity;
import angqin.myapplication.rxjava_retroft.service.MoviceService;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 作者：${lixuebin} on 2018/1/24 15:51
 * 邮箱：2072301410@qq.com
 * TIP: 只用retroft请求网络(未封装)
 */
public class SingleRetroftActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    private Button btn_request;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retroft_single);
        initView();
        setListener();
    }

    private void initView() {
        btn_request = (Button) findViewById(R.id.btn_request);
        tv_result = (TextView) findViewById(R.id.tv_result);

    }

    private void setListener() {
        btn_request.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        requestData();
    }

    /**
     * 进行网络请求
     */
    private void requestData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MoviceService moviceService = retrofit.create(MoviceService.class);
//        Call<MoviceEntity> movice = moviceService.getMovice(0, 10);
//        movice.enqueue(new Callback<MoviceEntity>() {
//            @Override
//            public void onResponse(Call<MoviceEntity> call, Response<MoviceEntity> response) {
//                Log.d(TAG, "message:" + response.message() + ",code:" + response.code() + ",raw:" + response.raw()
//                        + ",isSuccess:" + response.isSuccess());
//                tv_result.setText("onResponse:" + response.body().toString());
//            }
//            @Override
//            public void onFailure(Call<MoviceEntity> call, Throwable t) {
//                Log.e(TAG, t.getMessage() + ",getCause"+ t.getCause());
//                tv_result.setText("onFailure:" + t.getMessage());
//            }
//        });
    }
}
