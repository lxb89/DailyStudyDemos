package angqin.myapplication.t;

/**
 * 作者：${lixuebin} on 2018/1/29 17:36
 * 邮箱：2072301410@qq.com
 */
public class Info<T> {
    private T var;//定义泛型变量

    public T getVar() {
        return var;
    }

    public void setVar(T var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return "Info{" +
                "var=" + var +
                '}';
    }
}
