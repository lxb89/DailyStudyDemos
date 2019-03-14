package angqin.myapplication.databinding.bean;

/**
 * Created by ${lixuebin} on 2018/8/21.
 * 邮箱：2072301410@qq.com
 * TIP：
 */

public class UserBean {

    private String lastName;
    private String displayName;
    private int age;

    public UserBean(String lastName, String displayName, int age) {
        this.lastName = lastName;
        this.displayName = displayName;
        this.age = age;
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
