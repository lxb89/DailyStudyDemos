package angqin.myapplication.databinding;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import angqin.myapplication.R;

/**
 * Created by ${lixuebin} on 2018/8/22.
 * 邮箱：2072301410@qq.com
 * TIP： databinding 加载图片，无需手动设置图片
 *   1、使用 @BindingAdapter 注解，设置自定义属性的名称，imageUrl 就是属性的名称
 *   2、当ImageView 使用该属性时，会自动调用 LoadImage() 方法
 *   3、@BindingAdapter  允许支持多个属性
 */

public class ImageHelper {
    /**
     *  动态绑定图片数据
     * @param imageView
     * @param url
     */
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView imageView,String url){
        Glide.with(imageView.getContext()).load(url)
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(imageView);
    }

    /**
     *   动态绑定图片数据  自定义多个属性
     * @param imageView
     * @param url
     * @param holderDrawable
     * @param errorDrawable
     */
    @BindingAdapter({"imageUrl","placeHolder","error"})
    public static void loadImage(ImageView imageView, String url, Drawable holderDrawable,Drawable errorDrawable){
        Glide.with(imageView.getContext()).load(url)
                .placeholder(holderDrawable)
                .error(errorDrawable)
                .into(imageView);

    }

}
