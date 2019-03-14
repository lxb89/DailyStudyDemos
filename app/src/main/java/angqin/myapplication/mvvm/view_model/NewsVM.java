package angqin.myapplication.mvvm.view_model;

import java.util.List;

import angqin.myapplication.mvvm.adapter.NewsAdapter;
import angqin.myapplication.mvvm.base.BaseLoadListener;
import angqin.myapplication.mvvm.bean.SimpleNewsBean;
import angqin.myapplication.mvvm.model.NewsModelImpl;
import angqin.myapplication.mvvm.utils.Constants;
import angqin.myapplication.mvvm.view.INewsView;

/**
 * Created by ${lixuebin} on 2018/8/23.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public class NewsVM implements BaseLoadListener<SimpleNewsBean> {
    private static final String TAG = NewsVM.class.getSimpleName();
    private int currentPage = 1;
    private int loadType;
    private INewsView iNewsView;
    private NewsAdapter newsAdapter;
    private final NewsModelImpl newsModel;

    public NewsVM(INewsView iNewsView, NewsAdapter newsAdapter) {
        this.iNewsView = iNewsView;
        this.newsAdapter = newsAdapter;
        newsModel = new NewsModelImpl();
        getNewData();
    }

    /**
     * 第一次获取新闻数据
     */
    private void getNewData() {
        loadType = Constants.LoadData.FIRST_LOAD;
        newsModel.loadNewsData(currentPage, this);

    }

    /**
     * 获取下拉刷新数据
     */
    public void loadRefreshData() {
        loadType = Constants.LoadData.REFRESH;
        currentPage = 1;
        newsModel.loadNewsData(currentPage, this);
    }

    /**
     * 获取上拉加载数据
     */
    public void loadMoreData() {
        loadType = Constants.LoadData.LOAD_MORE;
        currentPage++;
        newsModel.loadNewsData(currentPage, this);

    }

    @Override
    public void loadSuccess(List<SimpleNewsBean> list) {
        if (currentPage > 1) {
            //是上拉数据
            newsAdapter.loadMoreData(list);
        } else {
            newsAdapter.refreshData(list);
        }
    }

    @Override
    public void loadFailure(String msg) {
        if (currentPage > 1) {
            currentPage--;
        }
        iNewsView.loadFailure(msg);


    }

    @Override
    public void loadStart() {
        iNewsView.loadStart(loadType);

    }

    @Override
    public void loadComplete() {
        iNewsView.loadComplete();

    }
}
