package angqin.myapplication.mvvm.model;

import android.os.Handler;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import angqin.myapplication.mvvm.base.BaseLoadListener;
import angqin.myapplication.mvvm.bean.NewsBean;
import angqin.myapplication.mvvm.bean.SimpleNewsBean;
import angqin.myapplication.mvvm.http.HttpUtils;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by ${lixuebin} on 2018/8/24.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public class NewsModelImpl implements INewsModel {
    private static final String TAG = "NewsModelImpl";
    private List<SimpleNewsBean> simpleNewsBeanList = new ArrayList<>();

    @Override
    public void loadNewsData(final int page, final BaseLoadListener<SimpleNewsBean> loadListener) {
        HttpUtils.getNewsData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NewsBean>() {
                    @Override
                    public void onNext(NewsBean newsBean) {
                        Log.i(TAG, "onNext：");
                        List<NewsBean.OthersBean> others = newsBean.getOthers();
                        simpleNewsBeanList.clear();
                        if (others != null && others.size() > 0) {
                            for (NewsBean.OthersBean othersBean : others) {
                                String name = othersBean.getName();
                                String description = othersBean.getDescription();
                                String thumbnail = othersBean.getThumbnail();
                                //重新组建Adapter需要的数据
                                SimpleNewsBean simpleNewsBean = new SimpleNewsBean();
                                simpleNewsBean.name.set(name);
                                simpleNewsBean.description.set(description);
                                simpleNewsBean.thumbnail.set(thumbnail);
                                simpleNewsBeanList.add(simpleNewsBean);

                                //此处暂时没实现分页数据，全部拿拿第一页数据
                                if (page > 1) {
                                    break;
                                }
                            }

                        }

                    }

                    @Override
                    protected void onStart() {
                        super.onStart();
                        Log.i(TAG, "onStart");
                        loadListener.loadStart();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i(TAG, "onError：");
                        loadListener.loadFailure(e.getMessage());


                    }

                    @Override
                    public void onComplete() {
                        Log.i(TAG, "onComplete:");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                loadListener.loadSuccess(simpleNewsBeanList);
                                loadListener.loadComplete();
                            }
                        }, 1000);

                    }
                });

    }
}
