package angqin.myapplication.databinding;

/**
 * Created by ${lixuebin} on 2018/8/21.
 * 邮箱：2072301410@qq.com
 * TIP：绑定数据的实体
 */

public class UserBean {

    private String picUrl;//用户头像url
    private String lastName;
    private String displayName;
    private int age;

    public UserBean( String picUrl,String lastName, String displayName, int age) {
        this.picUrl = picUrl;
        this.lastName = lastName;
        this.displayName = displayName;
        this.age = age;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }


    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
