package angqin.myapplication.rxjava_retroft.progress;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;

/**
 * 作者：${lixuebin} on 2018/1/25 16:09
 * 邮箱：2072301410@qq.com
 */
public class ProgressDialogHandler extends Handler {
    public static final int SHOW_PROGRESS_DIALOG = 1;
    public static final int DISMISS_PROGRESS_DIALOG = 2;
    private ProgressDialog pd;
    private Context context;
    private ProgressCancelListener progressCancelListener;
    private boolean cancelable;

    public ProgressDialogHandler(Context context, ProgressCancelListener progressCancelListener, boolean cancelable) {
        super();
        this.context = context;
        this.progressCancelListener = progressCancelListener;
        this.cancelable = cancelable;
    }

    @Override
    public void handleMessage(Message msg) {
        switch (msg.what) {
            case SHOW_PROGRESS_DIALOG:
                initProgressDialog();
                break;
            case DISMISS_PROGRESS_DIALOG:
                dismissProgressDialog();
                break;
        }
    }

    private void dismissProgressDialog() {
        if (pd !=null){
            pd.dismiss();
            pd=null;
        }

    }

    private void initProgressDialog() {
        if (pd == null) {
            pd = new ProgressDialog(context);
            pd.setCancelable(cancelable);
            if (cancelable) {
                pd.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        progressCancelListener.onCancelProgress();
                    }
                });
            }

            if (!pd.isShowing()) {
                pd.show();
            }
        }

    }
}
