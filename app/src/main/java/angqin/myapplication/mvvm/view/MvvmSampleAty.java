package angqin.myapplication.mvvm.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import angqin.myapplication.R;
import angqin.myapplication.databinding.ActivityMvvmSampleBinding;
import angqin.myapplication.mvvm.view_model.NewsVM;
import angqin.myapplication.mvvm.adapter.NewsAdapter;
import angqin.myapplication.mvvm.utils.Constants;
import angqin.myapplication.mvvm.utils.DialogHelper;

/**
 * Created by ${lixuebin} on 2018/8/23.
 * 邮箱：2072301410@qq.com
 * TIP： MVVM
 */

public class MvvmSampleAty extends AppCompatActivity implements INewsView,XRecyclerView.LoadingListener {

    private ActivityMvvmSampleBinding binding;
    private NewsAdapter newsAdapter;
    private NewsVM newsVM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_mvvm_sample);
        initRecyclerView();
        newsVM = new NewsVM(this,newsAdapter);

    }

    private void initRecyclerView() {
        binding.recyclerView.setRefreshProgressStyle(ProgressStyle.BallClipRotate);//设置下拉刷新的样式
        binding.recyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallClipRotate);//设置上拉加载的样式
        binding.recyclerView.setArrowImageView(R.mipmap.ic_launcher);
        binding.recyclerView.setLoadingListener(this);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(layoutManager);
        newsAdapter = new NewsAdapter(this);
        binding.recyclerView.setAdapter(newsAdapter);

    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        newsVM.loadRefreshData();

    }

    /**
     * 加载更多
     */
    @Override
    public void onLoadMore() {
        newsVM.loadMoreData();

    }

    @Override
    public void loadStart(int loadType) {
        if (loadType == Constants.LoadData.FIRST_LOAD){
            //显示正在加载中。。。
            DialogHelper.getInstance().show(this,"加载中...");
        }

    }

    @Override
    public void loadComplete() {
        DialogHelper.getInstance().close();
        binding.recyclerView.loadMoreComplete();
        binding.recyclerView.refreshComplete();

    }

    @Override
    public void loadFailure(String msg) {
        DialogHelper.getInstance().close();
        binding.recyclerView.loadMoreComplete();
        binding.recyclerView.refreshComplete();
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

}
