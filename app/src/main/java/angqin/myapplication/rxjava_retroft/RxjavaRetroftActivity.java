package angqin.myapplication.rxjava_retroft;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Observable;

import angqin.myapplication.R;
import angqin.myapplication.rxjava_retroft.config.HttpConfig;
import angqin.myapplication.rxjava_retroft.entity.MoviceEntity;
import angqin.myapplication.rxjava_retroft.service.MoviceService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 作者：${lixuebin} on 2018/1/24 16:09
 * 邮箱：2072301410@qq.com
 * TIP:简单Retrofit和Rxjava的结合，请求网络
 */
public class RxjavaRetroftActivity extends AppCompatActivity implements View.OnClickListener {

    private final String TAG = this.getClass().getSimpleName();
    private Button btn_request;
    private TextView tv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retroft_rxjava);
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
    public void onClick(View view) {//请求数据
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(HttpConfig.BASEURL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        final MoviceService moviceService = retrofit.create(MoviceService.class);
        moviceService.getTopMovie(0, 10)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<MoviceEntity>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(RxjavaRetroftActivity.this, "Get Top Movie Completed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        tv_result.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(MoviceEntity movieEntity) {
                        Log.e(TAG, "MoviceEntity:" + movieEntity.toString()
                                + "Count()" +  movieEntity.getCount()
                                + "getSubjects()" +  movieEntity.getSubjects()
                                + "getTitle()" +  movieEntity.getTitle());

                        tv_result.setText(movieEntity.toString());
                    }
                });

    }
}
