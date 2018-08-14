package angqin.myapplication;


import org.litepal.LitePal;

import java.util.Locale;

import angqin.myapplication.album.Album;
import angqin.myapplication.album.AlbumConfig;
import angqin.myapplication.album.MediaLoader;

/**
 * Created by ${lixuebin} on 2018/4/27.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public class Application extends android.app.Application {
    private static Application instance;

    @Override
    public void onCreate() {
        super.onCreate();
        mGalobalConfig();
        if (instance == null) {
            instance = this;
            Album.initialize(AlbumConfig.newBuilder(this)
                    .setAlbumLoader(new MediaLoader())
                    .setLocale(Locale.getDefault())
                    .build()
            );
        }
    }

    /**
     * 全局配置
     */
    private void mGalobalConfig() {
        LitePal.initialize(this);

    }

    public static Application getInstance() {
        return instance;
    }
}
