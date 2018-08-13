package angqin.myapplication.album;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.yanzhenjie.album.Action;
import com.yanzhenjie.album.Album;
import com.yanzhenjie.album.AlbumFile;
import com.yanzhenjie.album.api.widget.Widget;


import java.util.ArrayList;
import java.util.Locale;

import angqin.myapplication.R;

/**
 * Created by ${lixuebin} on 2018/4/27.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public class AlbumActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    private Button btn_operation;
    private ImageView img_pictrue;
    private angqin.myapplication.album.Adapter mAdapter;
    private ArrayList<AlbumFile> mAlbumFiles;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        btn_operation = (Button) findViewById(R.id.btn_operation);
        img_pictrue = (ImageView) findViewById(R.id.img_pictrue);
        setListerner();
    }

    private void setListerner() {
        btn_operation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Album.image(AlbumActivity.this)
                        .multipleChoice()
                        .camera(true)
                        .columnCount(2)
                        .selectCount(6)
                        .checkedList(mAlbumFiles)
                        .widget(Widget.newDarkBuilder(AlbumActivity.this)
                                .title(mToolbar.getTitle().toString())
                                .build()
                        )
                        .onResult(new Action<ArrayList<AlbumFile>>() {
                            @Override
                            public void onAction(@NonNull ArrayList<AlbumFile> result) {
                                mAlbumFiles = result;
                                AlbumFile albumFile = mAlbumFiles.get(0);
                                Glide.with(img_pictrue.getContext())
                                        .load(albumFile.getPath())
                                        .error(R.drawable.ic_launcher)
                                        .placeholder(R.drawable.ic_launcher)
                                        .crossFade()
                                        .into(img_pictrue);

                            }
                        })
                        .onCancel(new Action<String>() {
                            @Override
                            public void onAction(@NonNull String result) {
                                Toast.makeText(AlbumActivity.this, "取消", Toast.LENGTH_LONG).show();
                            }
                        })
                        .start();
            }
        });
    }

    private void previewImage(int position) {
        if (mAlbumFiles == null || mAlbumFiles.size() == 0) {
            Toast.makeText(this, "未选择图片", Toast.LENGTH_LONG).show();
        } else {
            Album.galleryAlbum(this)
                    .checkable(true)
                    .checkedList(mAlbumFiles)
                    .currentPosition(position)
                    .widget(
                            Widget.newDarkBuilder(this)
                                    .title(mToolbar.getTitle().toString())
                                    .build()
                    )
                    .onResult(new Action<ArrayList<AlbumFile>>() {
                        @Override
                        public void onAction(@NonNull ArrayList<AlbumFile> result) {
                            mAlbumFiles = result;
                            mAdapter.notifyDataSetChanged(mAlbumFiles);
                        }
                    })
                    .start();
        }
    }
}
