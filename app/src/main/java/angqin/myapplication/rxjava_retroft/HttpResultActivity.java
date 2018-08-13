package angqin.myapplication.rxjava_retroft;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.TextView;

import com.socks.library.KLog;

import java.util.List;

import angqin.myapplication.R;
import angqin.myapplication.rxjava_retroft.entity.Subject;
import angqin.myapplication.rxjava_retroft.request.HttpMethods;
import angqin.myapplication.rxjava_retroft.subcribers.ProgressSubscriber;
import angqin.myapplication.rxjava_retroft.subcribers.SubscriberOnNextListener;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 作者：${lixuebin} on 2018/1/25 11:45
 * 邮箱：2072301410@qq.com
 */
public class HttpResultActivity extends AppCompatActivity {
    @Bind(R.id.btn_request)
    Button btnRequest;
    @Bind(R.id.tv_result)
    TextView tvResult;
    private SubscriberOnNextListener subscriberOnNextListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_httpresult);
        ButterKnife.bind(this);
        subscriberOnNextListener = new SubscriberOnNextListener<List<Subject>>() {
            @Override
            public void onNext(List<Subject> subjects) {
                tvResult.setText(subjects.toString());
            }
        };

    }

    @OnClick(R.id.btn_request)
    public void onViewClicked() {
        getMovie();//请求网络数据
    }

    private void getMovie() {
        HttpMethods.getInstance().getTopMovie(new ProgressSubscriber(subscriberOnNextListener, HttpResultActivity.this), 0, 3);
    }


}
