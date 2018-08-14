package angqin.myapplication.litepal;

import org.litepal.crud.DataSupport;
import org.litepal.crud.LitePalSupport;

/**
 * Created by ${lixuebin} on 2018/8/13.
 * 邮箱：2072301410@qq.com
 * TIP：DataSupport  2.0版本 这个类过期，
 * 对于模型继承，使用{@ Link LIPeApple }代替。
 * 对于静态CRUD OP，使用{@ Link ListPal}代替
 */

public class PhoneBean extends LitePalSupport {
    private int id;

    private String name; //手机名

    private float price;//价格

    private String brand;//品牌


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "PhoneBean{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", brand='" + brand + '\'' +
                '}';
    }
}
