package angqin.myapplication.t;

/**
* 作者：${lixuebin} on 2018/1/30 14:21
* 邮箱：2072301410@qq.com
*  tip:泛型类   --->>指定上限，只能是数字类型
 *  */
public class InfoTwo<T extends Number> {
    private T var;

    public T getVar() {
        return var;
    }

    public void setVar(T var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return this.var.toString();
    }
}
