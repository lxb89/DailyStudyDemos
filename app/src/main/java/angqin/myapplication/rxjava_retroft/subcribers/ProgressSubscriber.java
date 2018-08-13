package angqin.myapplication.rxjava_retroft.subcribers;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.socks.library.KLog;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import angqin.myapplication.rxjava_retroft.progress.ProgressCancelListener;
import angqin.myapplication.rxjava_retroft.progress.ProgressDialogHandler;
import rx.Subscriber;

/**
 * 作者：${lixuebin} on 2018/1/25 15:35
 * 邮箱：2072301410@qq.com
 * tip: 网络加载进度框
 */
public class ProgressSubscriber<T> extends Subscriber<T> implements ProgressCancelListener {
    private static final String TAG = "ProgressSubscriber";
    private SubscriberOnNextListener subscriberOnNextListener;
    private Context context;
    private ProgressDialogHandler progressDialogHandler;

    public ProgressSubscriber(SubscriberOnNextListener subscriberOnNextListener, Context context) {
        this.subscriberOnNextListener = subscriberOnNextListener;
        this.context = context;
        progressDialogHandler = new ProgressDialogHandler(context,this,true);
    }

    @Override
    public void onStart() {
        showProgressDialog();
    }

    private void showProgressDialog() {
        if (progressDialogHandler!=null){
            progressDialogHandler.obtainMessage(ProgressDialogHandler.SHOW_PROGRESS_DIALOG).sendToTarget();
        }
    }

    @Override
    public void onCompleted() {
        dismissProgressDialog();
        Log.d(TAG, "onCompleted-->" + "请求完成");

    }

    @Override
    public void onError(Throwable e) {
        Log.d(TAG,"onError-->" + e.getMessage());
        if (e instanceof SocketTimeoutException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else if (e instanceof ConnectException) {
            Toast.makeText(context, "网络中断，请检查您的网络状态", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "error:" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        dismissProgressDialog();
    }

    private void dismissProgressDialog() {
        if (progressDialogHandler!=null){
            progressDialogHandler.obtainMessage(ProgressDialogHandler.DISMISS_PROGRESS_DIALOG).sendToTarget();
            progressDialogHandler = null;
        }
    }

    @Override
    public void onNext(T t) {
        Log.i(TAG,"onNext-->请求成功");
        if (subscriberOnNextListener !=null){
            subscriberOnNextListener.onNext(t);
        }

    }

    /**
     * 取消ProgressDialog的时候，取消对observable的订阅，同时也取消了http请求
     */
    @Override
    public void onCancelProgress() {
        if (!this.isUnsubscribed()){
            this.unsubscribe();
        }
    }
}
