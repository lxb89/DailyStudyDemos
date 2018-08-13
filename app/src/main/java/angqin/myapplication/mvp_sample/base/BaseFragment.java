package angqin.myapplication.mvp_sample.base;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import angqin.myapplication.mvp_sample.MvpSampleActivity;

/**
 * Created by ${lixuebin} on 2018/8/1.
 * 邮箱：2072301410@qq.com
 * TIP： MVP 架构在 Fragment 中的实现与 Activity 类似
 */

public abstract class BaseFragment extends Fragment implements BaseView {
    private View mRootView;
    private Context mContext;

    protected abstract void initAllChildView(Bundle savedInstanceState);

    public abstract int getContentViewId();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(getContentViewId(), container, false);
        this.mContext = getActivity();
        initAllChildView(savedInstanceState);
        return mRootView;

    }

    @Override
    public void showLoading() {
        checkActivityAttached();
        ((MvpSampleActivity) mContext).showLoading();
    }

    public void checkActivityAttached() {
        if (mContext == null) {
            throw new ActivityNotAttachedException();
        }
    }

    public static class ActivityNotAttachedException extends RuntimeException {
        public ActivityNotAttachedException() {
            super("Fragment has disconnected from Activity!");
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

}
